/**
 * Class to Represent a Clients Catalog
 *
 * @author jfc
 * @version 05/06/2015
 */

import java.util.ArrayList;
import java.util.TreeMap;

public class ClientsCatalog {
    private TreeMap<String, Client> clients;    /* All clients Code -> Client */

    /**
     * Unparameterized constructor
     */
    public ClientsCatalog () {
        this.clients = new TreeMap<String, Client>();
    }

    /**
     * Get a client if it is inserted in the catalog
     * @param code The client code
     */
    public Client getClient (String code) throws NullPointerException {
        Client c;
        if (code == null)
            throw new NullPointerException("code can't be null.");
        c = clients.get(code);

        if (c != null) return c.clone();
        else return c;
    }

    /**
     * Add a client to the catalog
     * @param client Client to be added
     */
    public void addClient (Client client) throws IllegalArgumentException {
        Client c;
        c = clients.get(client.getCode());

        if (c != null)
            throw new IllegalArgumentException("There is already a client with that code.");
        else clients.put(client.getCode(), client);
    }

    /**
     * Get a copy of all the clients in the catalog
     * @return ArrayList<Client> with all the clients
     */
    public ArrayList<Client> getClients () {
        ArrayList<Client> clients_copy = new ArrayList<Client>();

        for (Client c : clients.values()) {
            clients_copy.add(c);
        }

        return clients_copy;
    }

    /* equals, toString and clone */

    /**
     * Check if two client catalogues are equal, two catalogues are equal
     * if the clients they have are equal
     * @param catalog Catalog which with to compare
     * @return true if they are equal, false otherwise
     */
    public boolean equals (ClientsCatalog catalog) {
        ArrayList<Client> cat_clients;

        if (catalog == this) return true;

        if ((catalog == null) || (catalog.getClass() != this.getClass())) return false;

        cat_clients = catalog.getClients();

        if (cat_clients.size() != clients.size()) return false;

        for (Client c : cat_clients) {
            Client aux = this.getClient(c.getCode());
            if ((aux == null) || (!aux.equals(c))) return false;
        }
        return true;
    }
}
