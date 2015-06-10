/**
 * Class to Represent a Clients Catalog, stores all the client
 * codes and keeps track of what clients
 * did not buy a single product
 *
 * @author jfc
 * @version 05/06/2015
 */

import java.io.Serializable;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.ArrayList;

public class ClientsCatalog implements Serializable {
    private TreeSet<String> clients;        /* All clients */
    private TreeSet<String> unused_clients; /* Clients that bought no product */

    /**
     * Unparameterized constructor
     */
    public ClientsCatalog () {
        this.clients = new TreeSet<String>();
        this.unused_clients = new TreeSet<String>(new AlphabeticComparator());
    }

    /**
     * Add a client to the catalog
     * @param code Client code to be added
     */
    public void addClient (String code) throws NullPointerException {
        String code_aux;

        if (code == null)
            throw new NullPointerException("code can't be null.");

        code_aux = code.trim().replaceAll("[\n\r]", "");
        this.clients.add(code_aux);
        this.unused_clients.add(code_aux);
    }

    /**
     * Get a copy of all the clients in the catalog
     * @return ArrayList<Client> with all the clients
     */
    public ArrayList<String> getClients () {
        ArrayList<String> clients_copy = new ArrayList<String>();

        for (String c : clients) {
            clients_copy.add(c);
        }

        return clients_copy;
    }

    /**
     * Create ArrayList with all the clients whose code is initiated
     * by a specified character
     * @param initial The code initial letter
     */
    public ArrayList<String> getClientsByInitial (String initial) throws IllegalArgumentException {
        NavigableSet<String> set;
        ArrayList<String> codes;

        if (initial.trim().length() != 1)
            throw new IllegalArgumentException("initial must only contain a character.");
        if (Character.isDigit(initial.charAt(0)))
            throw new IllegalArgumentException("initial can't be a digit.");

        codes = new ArrayList<String>();

        String init = Character.toUpperCase(initial.charAt(0)) + "A000";
        String end  = Character.toUpperCase(initial.charAt(0)) + "Z999";

        set = clients.subSet(init, true, end, true);
        for (String code : set)
            codes.add(code);
        return codes;
    }

    /**
     * Remove client from list of clients that didn't bought any product
     * @param code The client code
     */
    public void removeSpendingClient (String code) throws NullPointerException {
        if (code == null)
            throw new NullPointerException("code can't be null.");
        else this.unused_clients.remove(code.trim().replaceAll("[\n\r]", ""));
    }

    /**
     * Get list of clients that didn't buy a single product
     * @return ArrayList with the client codes
     */
    public ArrayList<String> getUnusedClients () {
        ArrayList<String> list = new ArrayList<String>();

        for (String code : this.unused_clients)
            list.add(code);

        return list;
    }

    /* equals, toString and clone */

    /**
     * Check if two client catalogues are equal, two catalogues are equal
     * if the clients they have are equal
     * @param catalog Catalog which with to compare
     * @return true if they are equal, false otherwise
     */
    public boolean equals (ClientsCatalog catalog) {
        ArrayList<String> cat_clients;

        if (catalog == this) return true;

        if ((catalog == null) || (catalog.getClass() != this.getClass())) return false;

        cat_clients = catalog.getClients();

        if (cat_clients.size() != clients.size()) return false;

        for (String c : cat_clients) {
            if (!clients.contains(c)) return false;
        }
        return true;
    }
}
