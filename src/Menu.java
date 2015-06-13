import Exceptions.InvalidMonthException;

import java.io.*;
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
    public void registerSale (String client, int month, Sale s) throws InvalidMonthException {
        market.registerSale(client, month, s);
    }

    /**
     * Show client monthly sales and the total spent on each month
     * @param client The client code
     */
    public void getClientMonthySales (String client) {
        Crono c = new Crono();      /* Cronometer */
        double elapsed;             /* Time elapsed */
        int total_sales = 0, total_products = 0;
        float total_spent = 0;

        c.start();
        List<TripNumProdFat> infos = market.getClientMonthlySales(client);
        elapsed = c.stop();

        clean();
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
        System.out.format("%7s | %10s | %10s\n", "Sales", "Products", "Invoiced");
        for (TripNumProdFat trip : infos) {
            System.out.format("%7d | %10d | %10.2f\n", trip.getNumOfSales(), trip.getNumOfProducts(), trip.getTotalSpent());
            total_sales += trip.getNumOfSales();
            total_products += trip.getNumOfProducts();
            total_spent += trip.getTotalSpent();
        }
        System.out.println("---------------------------------------------");
        System.out.format("%7d | %10d | %10.2f\n", total_sales, total_products, total_spent);


        if (System.console() != null)
            System.console().readLine();
    }

    /**
     * Querie 3
     * Given a valid month, display the number of sales and the number
     * of different clients that purchased in that month
     */
    public void querie3 () {
        Scanner sc = new Scanner(System.in);
        ParSaleClient psc;

        clean();
        System.out.print("Month: ");
        try {
            psc = market.getMonthInfo(sc.nextInt());
            System.out.println("Num of Sales: " + psc.getSales());
            System.out.println("Num of clients: " + psc.getClients());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (System.console() != null)
            System.console().readLine();
    }

    /**
     * Querie 7
     * Given a valid client code, get the list of
     * the most bought products in decreasing order
     * of quantity
     */
    public void querie7 () {
        Scanner sc = new Scanner(System.in);
        List<ParProductUnits> result;

        clean();
        System.out.print("Client: ");
        try {
            result = market.getProductsMostBought(sc.nextLine().replaceAll("[\n\r]", ""));
            for (ParProductUnits par : result)
                System.out.println(par.getProductCode() + " - " + par.getUnitsSold());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (System.console() != null)
            System.console().readLine();
    }

    public void querie9 () {
        Scanner sc = new Scanner(System.in);
        List<ParClientQuant> result;

        clean();
        System.out.print("How many?");
        result = market.getTopClients(sc.nextInt());
        for(ParClientQuant par : result){
            System.out.println(par.getClientCode() + " - " + par.getProducts());
        }
        if(System.console() != null)
            System.console().readLine();
    }

    public void querie10 () {
        Scanner sc = new Scanner(System.in);
        List<ParClientQuant> result;

        clean();
        System.out.print("Product: ");
        String s = sc.nextLine().replaceAll("[\n\r]", "");
        System.out.print("How many?");
        result = market.getTopClients(s, sc.nextInt());
        for(ParClientQuant par : result){
            System.out.format("%s - %d - %.2f\n", par.getClientCode(), par.getProducts(), par.getInvoice());
        }
        if(System.console() != null)
            System.console().readLine();
    }

    /**
     * Querie 5
     * Given a valid product code, get the list
     * of diferent clients that bought the product
     * and the total invoiced each month
     */
    public void querie5 () {
        Scanner sc = new Scanner(System.in);
        List<ParClientFat> list;
        int i;

        clean();
        System.out.print("Product: ");
        try {
            list = market.getProductClientsSales(sc.nextLine().replaceAll("[\n\r]", ""));
            System.out.printf("%7s | %7s | %7s\n", "Month", "Clients", "Invoiced");
            for (i = 0; i < 12; i++) {
                int clients = list.get(i).getNumOfClients();
                float invoiced = list.get(i).getInvoiced();
                System.out.format("%7d | %7d | %7.2f\n", i + 1, clients, invoiced);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (System.console() != null)
            System.console().readLine();
    }

    public void querie8 () {
        Scanner sc = new Scanner(System.in);

        clean();
        System.out.print("Number of products: ");
        ArrayList<ParClientUnits> list;

        try {
            list = market.getMostBoughtProducts(sc.nextInt());

            System.out.format("%7s | %7s | %7s\n", "Product", "Clients", "Units");
            System.out.println("---------------------------");
            for (ParClientUnits par : list)
                System.out.format("%7s | %7d | %7d\n", par.getProduct(), par.getNumOfClients(), par.getUnits());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (System.console() != null)
            System.console().readLine();
    }

    /**
     * Save application state to an object file 'hipermercado.obj'
     */
    public void saveState () {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hipermercado.obj"));

            oos.writeObject(market);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Load a application state from the file 'hipermercado.obj'
     */
    public void loadState () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hipermercado.obj"));

            market = (Hypermarket) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        System.out.println("6: (3) Number of sales and clients in a month");
        System.out.println("7: (7) List of a client most bought products");
        System.out.println("8: Querie 5");
        System.out.println("9: Querie 8");
        System.out.println("10: Querie 9");
        System.out.println("11: Querie 10");
        System.out.println("12: Save application state");
        System.out.println("13: Load application state");
        System.out.println("14: Exit");
    }

    /**
     * Check if a client exists
     */
    public boolean clientExists (String client) throws NullPointerException {
        return market.existsClient(client);
    }

    /**
     * Check if a product exists
     */
    public boolean productExists (String product) throws NullPointerException {
        return market.existsProduct(product);
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
