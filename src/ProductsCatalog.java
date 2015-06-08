import java.lang.reflect.Array;
import java.util.*;


/**
 * Class to Represent a Products Catalog
 *
 * @author jfc
 * @version 07/06/2015
 */

import java.util.ArrayList;
        import java.util.TreeMap;

public class ProductsCatalog {
    private TreeMap<String, Product> products;      /* All clients Code -> Product */
    private TreeSet<String> unused_products;        /* The products that no one bought */

    /**
     * Unparameterized Constructor
     */
    public ProductsCatalog () {
        this.products = new TreeMap<String, Product>();
        this.unused_products = new TreeSet<String>(new AlphabeticComparator());
    }

    /* Getters */

    /**
     * Get a product with a given code
     * @param code The product code
     * @return A clone of the product if it exists, null otherwise
     */
    public Product getProduct (String code) throws NullPointerException {
        if (code == null)
            throw new NullPointerException("code can't be null.");
        else return products.get(code).clone();
    }

    /**
     * Get all the products in the catalog
     * @return ArrayList with clones of all the products
     */
    public ArrayList<Product> getProducts () {
        ArrayList<Product> prods = new ArrayList<Product>();

        for (Product prod : this.products.values())
            prods.add(prod.clone());

        return prods;
    }

    /* Setters */

    /**
     * Add a product to the products catalog
     * @param prod Product to be added
     */
    public void addProduct (Product prod) throws IllegalArgumentException {
        if (products.get(prod.getCode()) != null)
            throw new IllegalArgumentException("There is already a product with that code.");
        else {
            this.products.put(prod.getCode(), prod);
            this.unused_products.add(prod.getCode().trim().replaceAll("[\n\r]",""));
        }
    }

    /**
     * Create ArrayList with all the products whose code is initiated
     * by a specified character
     * @param initial The code initial letter
     */
    public ArrayList<String> getProductsByInitial (String initial) throws IllegalArgumentException {
        NavigableMap<String, Product> map;
        ArrayList<String> codes;

        if (initial.trim().length() != 1)
            throw new IllegalArgumentException("initial must only contain a character.");
        if (Character.isDigit(initial.charAt(0)))
            throw new IllegalArgumentException("initial can't be a digit.");

        codes = new ArrayList<String>();

        String init = Character.toUpperCase(initial.charAt(0)) + "A0000";
        String end  = Character.toUpperCase(initial.charAt(0)) + "Z9999";

        map = products.subMap(init, true, end, true);
        for (String code : map.keySet())
            codes.add(code);
        return codes;
    }

    /**
     * Mark a product as bought
     * @param code The product code
     */
    public void markAsBought (String code) throws NullPointerException {
        if (code == null)
            throw new NullPointerException("code can't be null.");
        else this.unused_products.remove(code.trim().replaceAll("[\n\r]",""));
    }

    /**
     * Create list with all the product codes that no one bought
     * @return ArrayList<String> with all the product codes
     */
    public ArrayList<String> getUnusedProducts () {
        ArrayList<String> list = new ArrayList<String>();

        for (String code : this.unused_products)
            list.add(code);

        return list;
    }

    /* toString, equals and clone */

    /**
     * Check if two products catalogues are equal, two catalogues are equal
     * if the products they have are equal
     * @param catalog Catalog which with to compare
     * @return true if they are equal, false otherwise
     */
    public boolean equals (ProductsCatalog catalog) {
        ArrayList<Product> cat_prods;

        if (catalog == this) return true;

        if ((catalog == null) || (catalog.getClass() != this.getClass())) return false;

        cat_prods = catalog.getProducts();

        if (cat_prods.size() != this.products.size()) return false;

        for (Product p : cat_prods) {
            Product aux = this.getProduct(p.getCode());
            if ((aux == null) || (!aux.equals(p))) return false;
        }
        return true;
    }
}
