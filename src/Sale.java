import java.io.Serializable;

/**
 * Save information about a sale
 *
 * What product was bought, how many units, how much has spent and
 * if it was a promotional or a normal sale
 *
 * @author jfc
 * @version 10/06/2015
 */
public class Sale implements Serializable {
    private String product; /* The product code */
    private int units;      /* How many units were sold */
    private float price;    /* How much was spent */
    private boolean type;   /* true -> promotion, false -> normal */

    /**
     * Unparameterized constructor
     */
    public Sale () {
        this.product = new String();
        this.units = 0;
        this.price = 0;
        this.type = false;
    }

    /**
     * Parameterized constructor
     * @param product   The product code
     * @param units     Number of units bought
     * @param price     How much was spent
     * @param type      The sale type
     */
    public Sale (String product, int units, float price, boolean type) {
        this.setProduct(product);
        this.setUnits(units);
        this.setPrice(price);
        this.setType(type);
    }

    /**
     * Constructor by copy
     * @param sale Sale instance from which to coppy the information
     */
    public Sale (Sale sale) {
        this.setProduct(sale.getProduct());
        this.setUnits(sale.getUnits());
        this.setPrice(sale.getPrice());
        this.setType(sale.getType());
    }

    /* Getters */

    /**
     * Get the product code
     */
    public String getProduct () {
        return this.product;
    }


    /**
     * Get the units sold
     */
    public int getUnits () {
        return this.units;
    }

    /**
     * Get the money spent
     */
    public float getPrice () {
        return this.price;
    }

    /**
     * Get the sale type, false means a normal sale
     * true means a promotional sale
     */
    public boolean getType () {
        return this.type;
    }

    /* Setters */

    /**
     * Set what product was bought
     * @param product The product code
     */
    public void setProduct (String product) throws NullPointerException {
        if (product == null)
            throw new NullPointerException("code can't be null.");
        else this.product = product;
    }

    /**
     * Set how much units were sold
     * @param units Number of units
     */
    public void setUnits (int units) throws IllegalArgumentException {
        if (units < 0)
            throw new IllegalArgumentException("units msut be >= 0");
        else this.units = units;
    }

    /**
     * Set the money spent
     * @param price Money spent
     */
    public void setPrice (float price) throws IllegalArgumentException {
        if (price < 0)
            throw new IllegalArgumentException("price must be >= 0");
        else this.price = price;
    }

    /**
     * Set the sale type, true -> promotion sale, false -> normal sale
     * @param type The sale type
     */
    public void setType (boolean type) {
        this.type = type;
    }

    /* equals, toString and clone */

    /**
     * Compare Sale instances to check if they are equal
     * @param o Object whit which to compare
     */
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;

        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Sale s = (Sale) o;
        boolean equal;
        equal = this.getProduct().equals(s.getProduct());
        equal = equal && (this.getUnits() == s.getUnits());
        equal = equal && (this.getPrice() == s.getPrice());
        equal = equal && (this.getType() == s.getType());
        return equal;
    }

    /**
     * Get information about the Sale
     */
    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        sb.append("Product: " + this.getProduct() + "\n");
        sb.append("Units: " + this.getUnits() + "\n");
        sb.append("Price: " + this.getPrice() + "\n");
        sb.append("Type " + (this.getType() ? "Promotion" : "Normal") + "\n");
        return sb.toString();
    }

    /**
     * Create a clone of the Sale
     */
    @Override
    public Sale clone () {
        return new Sale(this);
    }
}
