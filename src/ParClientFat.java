import java.util.Set;
import java.util.TreeSet;

/**
 * Class to save pairs of client codes and total
 * invoiced for a given product
 *
 * Created by joaocosta on 11/06/15.
 */
public class ParClientFat {
    private String product;         /* The product to which it is associated */
    private Set<String> clients;    /* Clients that bought the product */
    private float invoiced;         /* Total invoiced */

    /**
     * Unparameterized cosntructor
     */
    public ParClientFat () {
        this.product = new String();
        this.clients = new TreeSet<String>();
        this.invoiced = 0;
    }

    /**
     * Parameterized constructor
     * @param product Product code whith which to associate
     */
    public ParClientFat (String product) {
        this.setProduct(product);
        this.clients = new TreeSet<String>();
        this.invoiced = 0;
    }

    /**
     * Get the product code
     */
    public String getProduct () {
        return this.product;
    }

    /**
     * Get number of clients that bought the product
     */
    public int getNumOfClients () {
        return this.clients.size();
    }

    /**
     * Get the total invoiced
     */
    public float getInvoiced () {
        return this.invoiced;
    }

    /**
     * Change the product code that is associated
     * @param product The product code
     */
    public void setProduct (String product) throws NullPointerException {
        if (product == null)
            throw new NullPointerException("product can't be null.");
        else this.product = product;
    }

    /**
     * Add a client to the list of clients
     * that bought the product
     * @param client The client code
     */
    public void addClient (String client) throws NullPointerException {
        if (client == null)
            throw new NullPointerException("client can't be null.");
        else this.clients.add(client);
    }

    /**
     * Add a invoice value to the
     * product
     * @param amount The invoice amount
     */
    public void addInvoice (float amount) throws IllegalArgumentException {
        if (amount < 0)
            throw new IllegalArgumentException("amount must be > 0");
        else this.invoiced += amount;
    }
}
