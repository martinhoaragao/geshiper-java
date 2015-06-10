import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
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
     * @param code The client code
     */
    public void addClient (String code) {
        market.addClient(code);
    }

    /**
     * Add a product in gesthiper
     * @param code The product code
     */
    public void addProduct (String code) {
        market.addProduct(code);
    }

    /**
     * Get the initial from the user and display the clients
     * list whose code starts with the given initial
     */
    public void getClientsByInitial () {
        Scanner sc = new Scanner(System.in);
        Crono c = new Crono();

        clean();
        System.out.print("Initial: ");
        c.start();
        ArrayList<String> clients = market.getClientsByInitial(sc.nextLine().trim().replaceAll("[\n\r]", ""));
        paginate(clients, "Clients", c.stop());
    }

    /**
     * Get the initial from the user and display the clients
     * list whose code starts with the given initial
     */
    public void getProductsByInitial () {
        Scanner sc = new Scanner(System.in);
        Crono c = new Crono();

        clean();
        System.out.print("Initial: ");
        c.start();
        ArrayList<String> products = market.getProductsByInitial(sc.nextLine().trim().replaceAll("[\n\r]", ""));
        paginate(products, "Products", c.stop());
    }

    /**
     * Show list of clients that did not buy any product
     */
    public void cheapClients () {
        ArrayList<String> clients;
        Crono c = new Crono();

        try {
            c.start();
            clients = market.getCheapClients();
            paginate(clients, "Clients", c.stop());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Show list of products that no one bought
     */
    public void unusedProducts () {
        ArrayList<String> products;
        Crono c = new Crono();

        try {
            c.start();
            products = market.getUnusedProducts();
            paginate(products, "Products", c.stop());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Register a sale
     */
    public void registerSale (String client, int month, Sale s) {
        market.registerSale(client, month, s);
    }

    /**
     * Show client monthly sales and the total spent on each month
     * @param client The client code
     */
    public void getClientMonthySales (String client) {
        Crono c = new Crono();
        double elapsed;
        c.start();
        List<TripNumProdFat> infos = market.getClientMonthlySales(client);
        elapsed = c.stop();

        clean();
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
        System.out.format("%7s | %10s | %10s\n", "Sales", "Products", "Invoiced");
        for (TripNumProdFat trip : infos)
            System.out.format("%7d | %10d | %10.2f\n", trip.getNumOfSales(), trip.getNumOfProducts(), trip.getTotalSpent());

        if (System.console() != null)
            System.console().readLine();
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
        System.out.println("5: Client anual sales");
        System.out.println("6: Exit");
    }

    /**
     * Paginate a ArrayList of strings
     * @param list List to be displayed
     * @param type What is being displayed
     * @param elapsed Time elapsed
     */
    public void paginate (ArrayList<String> list, String type, double elapsed) {
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
            System.out.printf("(Time elapsed: %1.6f seconds)\n", elapsed);

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
