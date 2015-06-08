import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to represent the menu of Gesthiper application
 *
 * Created by joaocosta on 07/06/15.
 */
public class Menu {
    private Hypermarket market;

    /**
     * Unparameterized constructor
     */
    public Menu () {
        market = new Hypermarket();
    }

    /**
     * Add a client in gesthiper
     * @param client Client to be added
     */
    public void addClient (Client client) {
        market.addClient(client);
    }

    /**
     * Add a product in gesthiper
     * @param product Product to be added
     */
    public void addProduct (Product product) {
        market.addProduct(product);
    }

    /**
     * Get the initial from the user and display the clients
     * list whose code starts with the given initial
     */
    public void getClientsByInitial () {
        Scanner sc = new Scanner(System.in);
        clean();
        System.out.print("Initial: ");
        ArrayList<String> clients = market.getClientsByInitial(sc.nextLine().trim().replaceAll("[\n\r]", ""));
        paginate(clients, "Clients");
    }

    /**
     * Get the initial from the user and display the clients
     * list whose code starts with the given initial
     */
    public void getProductsByInitial () {
        Scanner sc = new Scanner(System.in);

        clean();
        System.out.print("Initial: ");
        ArrayList<String> products = market.getProductsByInitial(sc.nextLine().trim().replaceAll("[\n\r]", ""));
        paginate(products, "Products");
    }

    /**
     * Show list of clients that did not buy any product
     */
    public void cheapClients () {
        ArrayList<String> clients;

        try {
            clients = market.getCheapClients();
            paginate(clients, "Clients");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Show list of products that no one bought
     */
    public void unusedProducts () {
        ArrayList<String> products;

        try {
            products = market.getCheapClients();
            paginate(products, "Products");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Register a sale
     */
    public void registerSale (ArrayList<String> args) {
        market.registerSale(args);
    }

    /**
     * Display the menu to the user
     */
    public void showMenu () {
        clean();

        System.out.println("1: List of Clients by initial");
        System.out.println("2: List of Products by initial");
        System.out.println("3: List of clients that bough nothing");
        System.out.println("4: List of products not sold");
        System.out.println("5: Exit");
    }

    /**
     * Paginate a ArrayList of strings
     * @param list List to be displayed
     * @param type What is being displayed
     */
    public void paginate (ArrayList<String> list, String type) {
        Scanner sc = new Scanner(System.in);
        String choice;      /* The user choice */
        boolean finished;   /* Control if user has finished */
        int pages;          /* Nmber of pages */
        int codes;          /* Number of codes to be displayed */
        int actual_page;    /* Number of the actual page */
        int index;          /* Auxiliar index */
        int i_index;        /* Initial index */
        int e_index;        /* Ending index */
        codes = 10;
        pages = list.size() / codes;
        actual_page = 0;
        finished = false;
        index = 0;

        while (!finished) {
            if (actual_page > pages) actual_page = pages;
            else if (actual_page < 0) actual_page = 0;
            index = (codes * actual_page);

            clean();
            i_index = index + 1;
            e_index = index + 11;
            if (e_index > list.size()) e_index = list.size();

            System.out.println("Displaying clients from " + i_index + " to " + e_index + " | " + list.size() + " " + type);
            System.out.println("Page " + (actual_page + 1) + " of " + (pages + 1));
            for (; (index < e_index); index++) {
                System.out.println(list.get(index));
            }
            System.out.println("N: Next | B: Back | P: page | E: Exit");

            choice = sc.nextLine().trim().replaceAll("[\n\r]", "");

            if (choice.equals("N")) actual_page++;
            else if (choice.equals("B")) actual_page--;
            else if (choice.equals("E")) finished = true;
            else if (choice.equals("P")) {
                System.out.print("Page number: ");
                actual_page = sc.nextInt() - 1;
            }
        }
    }

    /**
     * Clean the terminal view
     */
    public void clean () {
     if (System.console() != null)
         System.out.print("\u001b[2J" + "\u001b[H");
    }
}
