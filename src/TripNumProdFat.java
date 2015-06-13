import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Triple to store information about number of sales,
 * number of different proudcts bought and the total spent
 *
 * @author jfc
 * @version 10/06/2015
 */
public class TripNumProdFat implements Serializable {
    private int num_sales;          /* Number of sales */
    private Set<String> products;   /* Different bought products */
    private float total;            /* Total spent money */

    /**
     * Unparameterized constructor
     */
    public TripNumProdFat () {
        this.num_sales = 0;
        this.products = new TreeSet<String>();
        this.total = 0;
    }

    /**
     * Add a sale information to the triple
     * @param product The product code
     * @param price The money spent on the sale
     */
    public void addInfo (String product, float price)
            throws NullPointerException, IllegalArgumentException {
        if (product == null)
            throw new NullPointerException("product can't be null.");
        else if (price < 0)
            throw new IllegalArgumentException("price has to be >= 0.");
        else {
            this.num_sales++;
            this.products.add(product);
            this.total += price;
        }
    }

    /**
     * Get total money spent
     * @return The total money spent
     */
    public float getTotalSpent () {
        return this.total;
    }

    /**
     * Get number of sales
     * @return Total number of sales
     */
    public int getNumOfSales () {
        return this.num_sales;
    }

    /**
     * Get number of different products bought
     * @return Number of different products
     */
    public int getNumOfProducts () {
        return this.products.size();
    }

    /**
     * Get information abou this triple
     * @return Information about this triple
     */
    public String toString () {
        StringBuilder sb = new StringBuilder();

        sb.append("Sales: " + this.getNumOfSales() + "\n");
        sb.append("Different products: " + this.getNumOfProducts() + "\n");
        sb.append("Invoiced: " + this.getTotalSpent() + "\n");

        return sb.toString();
    }
}
