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
     * Parameterized Constructor, the code must be composed by two uppercase
     * letters and four numbers
     * @param code The Product code
     */
    public Product (String code) {
        boolean initials, numbers;

        if (code.trim().length() != 6)
            throw new IllegalArgumentException("code can only have 6 characters.");

        /* Check if the two initials area uppercase letters */
        initials    = Character.isUpperCase(code.charAt(0)) && Character.isUpperCase(code.charAt(1));
        numbers     = Character.isDigit(code.charAt(2)) && Character.isDigit(code.charAt(3)) &&
                Character.isDigit(code.charAt(4)) && Character.isDigit(code.charAt(5));

        if (initials && numbers) this.code = code;
        else throw new IllegalArgumentException("Invalid code.");
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
