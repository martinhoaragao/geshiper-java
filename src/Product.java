/**
 * Class to represent a Product with a given product code
 *
 * @author jfc
 * @version 05/06/2015
 */
public class Product {
    private String code;    /* The Product Code */

    /**
     * Unparameterized Constructor
     */
    public Product () {
        this.code = new String();
    }

    /**
     * Parameterized Constructor
     * @param code The Product code
     */
    public Product (String code) {
        this.code = code;
    }

    /**
     * Constructor by copy
     * @param product product which will be cloned
     */
    public Product (Product product) {
        this.code = product.getCode();
    }

    /* Getters */

    /**
     * @return The Product Code
     */
    public String getCode () {
        return this.code;
    }

    /* Setters */

    /**
     * Change Product code
     * @param code The new Product code
     */
    public void setCode (String code) {
        this.code = code;
    }

    /* equals, toString and clone */

    /**
     * Compares two Product instances
     * @param product Product whith which to compare
     * @return true if the products are equal, false otherwise
     */
    public boolean equals (Product product) {
        if (product == this) return true;

        if ((product == null) || (this.getClass() != product.getClass())) return false;

        return this.code.equals(product.getCode());
    }

    /**
     * @return clone of the Product
     */
    public Product clone () {
        return new Product(this);
    }
}
