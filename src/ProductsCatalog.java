import java.io.Serializable;
import java.util.*;


/**
 * Class to Represent a Products Catalog
 *
 * @author jfc
 * @version 07/06/2015
 */

import java.util.ArrayList;
import java.util.TreeSet;

public class ProductsCatalog implements Serializable {
    private TreeSet<String> products;           /* All product codes */
    private TreeSet<String> unused_products;    /* The products that no one bought */

    /**
     * Unparameterized Constructor
     */
    public ProductsCatalog () {
        this.products = new TreeSet<String>();
        this.unused_products = new TreeSet<String>(new AlphabeticComparator());
    }

    /* Getters */

    /**
     * Get all the products in the catalog
     * @return ArrayList with clones of all the products
     */
    public ArrayList<String> getProducts () {
        ArrayList<String> prods = new ArrayList<String>();

        for (String code : this.products)
            prods.add(code);

        return prods;
    }

    /* Setters */

    /**
     * Add a product to the products catalog
     * @param code The product code
     */
    public void addProduct (String code) throws NullPointerException {
        if (code == null)
            throw new NullPointerException("code can't be null.");
        else {
            this.products.add(code.trim().replaceAll("[\n\r]",""));
            this.unused_products.add(code.trim().replaceAll("[\n\r]",""));
        }
    }

    /**
     * Create ArrayList with all the products whose code is initiated
     * by a specified character
     * @param initial The code initial letter
     */
    public ArrayList<String> getProductsByInitial (String initial) throws IllegalArgumentException {
        NavigableSet<String> set;
        ArrayList<String> codes;

        if (initial.trim().length() != 1)
            throw new IllegalArgumentException("initial must only contain a character.");
        if (Character.isDigit(initial.charAt(0)))
            throw new IllegalArgumentException("initial can't be a digit.");

        codes = new ArrayList<String>();

        String init = Character.toUpperCase(initial.charAt(0)) + "A0000";
        String end  = Character.toUpperCase(initial.charAt(0)) + "Z9999";

        set = products.subSet(init, true, end, true);
        for (String code : set)
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

    /**
     * Check if a given product code exists
     * @param code The Product code
     */
    public boolean exists (String code) throws NullPointerException {
        if (code == null)
            throw new NullPointerException("code cant' be null.");
        return this.products.contains(code);
    }

    /* toString, equals and clone */

    /**
     * Check if two products catalogues are equal, two catalogues are equal
     * if the products they have are equal
     * @param catalog Catalog which with to compare
     * @return true if they are equal, false otherwise
     */
    public boolean equals (ProductsCatalog catalog) {
        ArrayList<String> cat_prods;

        if (catalog == this) return true;

        if ((catalog == null) || (catalog.getClass() != this.getClass())) return false;

        cat_prods = catalog.getProducts();

        if (cat_prods.size() != this.products.size()) return false;

        for (String p : cat_prods) {
            if (!this.products.contains(p)) return false;
        }
        return true;
    }
}
