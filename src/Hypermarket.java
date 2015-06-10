import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author jfc
 * @version 07/06/2015
 */
public class Hypermarket {
    private ProductsCatalog p_cat;  /* The products catalog */
    private ClientsCatalog  c_cat;  /* The clients catalog */
    private Sales sales;            /* The sales module */

    /**
     * Unparameterized Constructor, will initialize instance variables
     */
    public Hypermarket () {
        this.p_cat = new ProductsCatalog();
        this.c_cat = new ClientsCatalog();
        this.sales = new Sales();
    }

    /**
     * Add a client to the clients catalog
     * @param code The client code
     */
    public void addClient (String code) {
        c_cat.addClient(code);
    }

    /**
     * Add a product to the products catalog
     * @param code The product code
     */
    public void addProduct (String code) {
        p_cat.addProduct(code);
    }

    /**
     * Get List of clients whose client code begins with a given initial
     * @param initial The initial letter
     * @return ArrayList<String> with the client codes
     */
    public ArrayList<String> getClientsByInitial (String initial) {
        return c_cat.getClientsByInitial(initial);
    }

    /**
     * Get List of products whose code begins with a given initial
     * @param initial The initial letter
     * @return ArrayList<String> with the product codes
     */
    public ArrayList<String> getProductsByInitial (String initial) {
        return p_cat.getProductsByInitial(initial);
    }

    /**
     * Get List of clients that did not buy any product
     * @return ArrayList<String> with all the client codes
     */
    public ArrayList<String> getCheapClients () {
        return c_cat.getUnusedClients();
    }

    /**
     * Get List of products that no one bought
     * @return ArrayList<String> with all the product codes
     */
    public ArrayList<String> getUnusedProducts () {
        return p_cat.getUnusedProducts();
    }

    /**
     * Register a sale to the sales module
     */
    public void registerSale (String client, int month, Sale sale) {
        p_cat.markAsBought(sale.getProduct());
        c_cat.removeSpendingClient(client);
        sales.addSale(client, month, sale);
    }
}
