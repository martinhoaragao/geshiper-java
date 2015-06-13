/**
 * Class to define a pair that will store the number of sales in a given month
 * and the number of different clients that bought in that month
 *
 * @author jfc
 * @version 11/06/2015
 */
public class ParSaleClient {
    private int num_sales;      /* Total sales */
    private int num_clients;    /* Total clients */

    /**
     * Unparameterized constructor, sets number of sales and number
     * of clients to 0
     */
    public ParSaleClient () {
        num_sales = 0;
        num_clients = 0;
    }

    /**
     * Increment the number of clients
     * @param num How much to increment
     */
    public void addClient (int num) {
        num_clients += num;
    }

    /**
     * Increment the number of sales
     * @param num How much to increment
     */
    public void addSale (int num) {
        num_sales += num;
    }

    /**
     * Get number of clients
     * @return The number of clients
     */
    public int getClients () {
        return num_clients;
    }

    /**
     * Get number of sales
     * @return The number of sales
     */
    public int getSales () {
        return num_sales;
    }

    /* equals, toString and clone */

    /**
     * Check if two ParSaleClient are equal
     * @param psc Object with which to compare
     */
    public boolean equals (Object psc) {
        if (psc == this) return true;

        if ((psc == null) || (psc.getClass() != this.getClass())) return false;

        ParSaleClient aux = (ParSaleClient) psc;
        return ((this.num_sales == aux.getSales()) && (this.num_clients == aux.getClients()));
    }
}
