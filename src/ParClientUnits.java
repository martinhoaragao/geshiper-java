import java.util.Set;
import java.util.TreeSet;

/**
 * Class to store information about the clients that bought a
 * product and the number of units sold
 *
 * @author jfc
 * @version 12/06/2015
 */
public class ParClientUnits {
    /* The product associated */
    private String product;
    /* The list of clients */
    private Set<String> clients;
    /* Number of units sold */
    private int units;

    /**
     * Unparameterized Constructor
     */
    public ParClientUnits () {
        this.product = new String();
        this.clients = new TreeSet<String>();
        this.units = 0;
    }

    /**
     * Parameterized constructor
     * @param product   The product code associated
     * @param client    The initial client to be added
     * @param units     The initial number of units
     */
    public ParClientUnits (String product, String client, int units) {
        this.setProduct(product);
        this.clients = new TreeSet<String>();
        this.addClient(client);
        this.units = 0;
        this.addUnits(units);
    }

    /**
     * Add a client to the pair
     * @param client The client code
     */
    public void addClient (String client) throws NullPointerException {
        if (client == null)
            throw new NullPointerException("client can't be null");
        this.clients.add(client);
    }

    /**
     * Increase de number of units sold
     * @param units Amount to increase
     */
    public void addUnits (int units) {
        this.units += units;
    }

    /**
     * Set the product
     * @param product The product code
     */
    public void setProduct (String product) throws NullPointerException {
        if (product == null)
            throw new NullPointerException("product can't be null");
        else this.product = product;
    }

    /**
     * Get the total number of clients
     * that bought the product
     */
    public int getNumOfClients () {
        return this.clients.size();
    }

    /**
     * Get the number of units sold
     */
    public int getUnits () {
        return this.units;
    }

    /**
     * Get product associated with this pair
     */
    public String getProduct () {
        return this.product;
    }

}

