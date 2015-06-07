import java.util.ArrayList;
import java.util.TreeMap;


/**
 * Class to Represent a Products Catalog
 *
 * @author jfc
 * @version 07/06/2015
 */

import java.util.ArrayList;
        import java.util.TreeMap;

public class ProductsCatalog {
    private TreeMap<String, Product> products;    /* All clients Code -> Product */

    /**
     * Unparameterized Constructor
     */
    public ProductsCatalog () {
        this.products = new TreeMap<String, Product>();
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
        else products.put(prod.getCode(), prod);
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
