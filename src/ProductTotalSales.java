import java.io.Serializable;

/**
 * Save information about a product total sales
 *
 * What produc, how many units and how much has sold in promotion or normal
 *
 * @author ma
 * @version 11/06/2015
 */
public class ProductTotalSales implements Serializable {
    private String product;         /* The product code */
    private int normalUnits;        /* How many units were sold with normal price */
    private int promoUnits;         /* How many units were sold during promotion */
    private double normalRevenue;   /* How much was sold as normal */
    private double promoRevenue;    /* How much was sold in promotion */

    /**
     * Unparameterized constructor
     */
    public ProductTotalSales () {
        this.product = new String();
        this.normalUnits = 0;
        this.promoUnits = 0;
        this.normalRevenue = 0.0;
        this.promoRevenue = 0.0;
    }

    /**
     * Parameterized constructor
     * @param product           The product code
     * @param normalUnits       Number of units bought
     * @param normalUnits       Number of units bought
     * @param normalRevenue     How much was sold as normal
     * @param promoRevenue      How much was sold in promotion
     */
    public ProductTotalSales (String product, int normalUnits, int promoUnits, float normalRevenue, float promoRevenue) {
        this.setProduct(product);
        this.setNormalUnits(normalUnits);
        this.setPromoUnits(promoUnits);
        this.setNormalRevenue(normalRevenue);
        this.setPromoRevenue(promoRevenue);
    }

    /**
     * Constructor by copy
     * @param sale ProductTotalSales instance from which to copy the information
     */
    public ProductTotalSales (ProductTotalSales totalSales) {
        this.setProduct(totalSales.getProduct());
        this.setNormalUnits(totalSales.getNormalUnits());
        this.setPromoUnits(totalSales.getPromoUnits());
        this.setNormalRevenue(totalSales.getNormalRevenue());
        this.setPromoRevenue(totalSales.getPromoRevenue());
    }

    /* Getters */

    /**
     * Get the product code
     * @return code, product code
     */
    public String getProduct () {
        return this.product;
    }

    /**
     * Get the normal units sold
     * @return number of units sold
     */
    public int getNormalUnits () {
        return this.normalUnits;
    }

    /**
     * Get the units sold during promotion
     * @return number of units sold
     */
    public int getPromoUnits () {
        return this.promoUnits;
    }
    /**
     * Get the revenue from normal sales
     * @return total revenue from normal sales
     */
    public double getNormalRevenue () {
        return this.normalRevenue;
    }

    /**
     * Get the revenue from sales in promotion
     * @return total revenue from sales during promotion
     */
    public double getPromoRevenue () {
        return this.promoRevenue;
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
     * Set how many normal units were sold
     * @param units Number of units
     */
    public void setNormalUnits (int units) throws IllegalArgumentException {
        if (units <= 0)
            throw new IllegalArgumentException("units must be > 0");
        else this.normalUnits = units;
    }

    /**
     * Set how many units were sold during promotion
     * @param units Number of units
     */
    public void setPromoUnits (int units) throws IllegalArgumentException {
        if (units <= 0)
            throw new IllegalArgumentException("units must be > 0");
        else this.promoUnits = units;
    }

    /**
     * Set the total normal revenue
     * @param revenue Money acquired from normal sales
     */
    public void setNormalRevenue (double revenue) throws IllegalArgumentException {
        if (revenue < 0)
            throw new IllegalArgumentException("revenue must be >= 0");
        else this.normalRevenue = revenue;
    }

    /**
     * Set the total promotion revenue
     * @param revenue Money acquired from promotion sales
     */
    public void setPromoRevenue (double revenue) throws IllegalArgumentException {
        if (revenue < 0)
            throw new IllegalArgumentException("revenue must be >= 0");
        else this.promoRevenue = revenue;
    }

    /* equals, toString and clone */

    /**
     * Compare ProductTotalSales instances to check if they are equal
     * @param o Object with which to compare
     * @return equal, true if they're equal
     */
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;

        if ((o == null) || (this.getClass() != o.getClass())) return false;

        ProductTotalSales s = (ProductTotalSales) o;
        boolean equal;
        equal = this.getProduct().equals(s.getProduct());
        equal = equal && (this.getNormalUnits() == s.getNormalUnits());
        equal = equal && (this.getPromoUnits() == s.getPromoUnits());
        equal = equal && (this.getNormalRevenue() == s.getNormalRevenue());
        equal = equal && (this.getPromoRevenue() == s.getPromoRevenue());

        return equal;
    }

    /**
     * Get information about the ProductTotalSales
     */
    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        sb.append("Product: " + this.getProduct() + "\n");
        sb.append("Normal Units: " + this.getNormalUnits() + "\n");
        sb.append("Promotion Units: " + this.getPromoUnits() + "\n");
        sb.append("Normal Revenue: " + this.getNormalRevenue() + "\n");
        sb.append("Promotion Revenue: " + this.getPromoRevenue() + "\n");

        return sb.toString();
    }

    /**
     * Create a clone of the ProductTotalSales
     */
    @Override
    public ProductTotalSales clone () {
        return new ProductTotalSales(this);
    }

    /* Other methods */

    /**
     * Update product information based on a sale
     * @param sale, sale information
     */
    public void updateTotalSales (Sale sale) {
        if (sale.getType()) {
            this.setNormalUnits(sale.getUnits());
            this.setNormalRevenue(sale.getUnits() * sale.getPrice());
        }
        else {
            this.setPromoUnits(sale.getUnits());
            this.setPromoRevenue(sale.getUnits() * sale.getPrice());
        }
    }

}
