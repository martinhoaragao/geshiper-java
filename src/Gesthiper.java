import java.util.ArrayList;
import java.util.NavigableMap;

/**
 * @author jfc
 * @version 07/06/2015
 */
public class Gesthiper {
    private ProductsCatalog p_cat;   /* The products catalog */
    private ClientsCatalog  c_cat;   /* The clients catalog */

    /**
     * Unparameterized Constructor, will initialize instance variables
     */
    public Gesthiper () {
        this.p_cat = new ProductsCatalog();
        this.c_cat = new ClientsCatalog();
    }

    /**
     * Add a client to the clients catalog
     * @param client Client to be added
     */
    public void addClient (Client client) {
        c_cat.addClient(client);
    }

    /**
     * Add a product to the products catalog
     * @param product Product to be added
     */
    public void addProduct (Product product) {
        p_cat.addProduct(product);
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
}
