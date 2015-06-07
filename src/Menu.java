import java.util.NavigableMap;
import java.util.SortedMap;

/**
 * Class to represent the menu of Gesthiper application
 *
 * Created by joaocosta on 07/06/15.
 */
public class Menu {
    private Gesthiper gesthiper;

    /**
     * Unparameterized constructor
     */
    public Menu () {
        gesthiper = new Gesthiper();
    }

    /**
     * Add a client in gesthiper
     * @param client Client to be added
     */
    public void addClient (Client client) {
        gesthiper.addClient(client);
    }

    /**
     * Add a product in gesthiper
     * @param product Product to be added
     */
    public void addProduct (Product product) {
        gesthiper.addProduct(product);
    }

    public void getClientsByInitial (String initial) {
        NavigableMap<String, Client> map = gesthiper.getClientsByInitial(initial);

        for (String code : map.keySet())
            System.out.println(code);
        System.out.println(map.size());
    }
}
