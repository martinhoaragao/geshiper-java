import Exceptions.InvalidMonthException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * Class to represent the menu of Gesthiper application
 *
 * Created by joaocosta on 07/06/15.
 */
public class Menu {
    private Hypermarket market;
    private String fileClients;
    private String fileProducts;
    private String fileSales;

    int invalid_lines = 0, clines = 0, plines = 0, zerovalueSales = 0;


    /**
     * Unparameterized constructor
     */
    public Menu () {
        market = new Hypermarket();
        fileClients = "FichClientes.txt";
        fileProducts = "FichProdutos.txt";
        fileSales = "Compras.txt";

    }

    /**
     * Load a file with all the client codes
     * @param file The filename, if the file is null
     *             the method will request a new file
     */
    public void loadClientsFile (String file) {
        Crono crono = new Crono();  /* Cronometer */
        double elapsed;             /* Time elapsed */
        BufferedReader br;          /* To read the file */
        String line;                /* Store actual line */
        int lines = 0;              /* Number of lines read */

        if (file == null) {         /* Ask for the filename */
            Scanner sc = new Scanner(System.in);
            System.out.print("File name: ");
            file = sc.nextLine().trim().replaceAll("[\n\r]", "");
        }

        try {
            crono.start();
            br = new BufferedReader(new FileReader(file)); /* Open file */

            while ((line = br.readLine()) != null) {
                lines++;
                addClient(line);
            }
            elapsed = crono.stop();

            System.out.println(file + " was read.");
            System.out.println(lines + " clients were stored.");
            System.out.format("Elapsed time: %2.6f\n", elapsed);
        }
        catch (Exception e) {
            System.out.println("The file does not exist.");
        }
    }

    /**
     * Load a file with all the product codes
     * @param file The filename, if the file is null
     *             the method will request a new file
     */
    public void loadProductsFile (String file) {
        Crono crono = new Crono();  /* Cronometer */
        double elapsed;             /* Time elapsed */
        BufferedReader br;          /* To read the file */
        String line;                /* Store actual line */
        int lines = 0;              /* Number of lines read */

        if (file == null) {         /* Ask for the filename */
            Scanner sc = new Scanner(System.in);
            System.out.print("File name: ");
            file = sc.nextLine().trim().replaceAll("[\n\r]", "");
        }

        try {
            crono.start();
            br = new BufferedReader(new FileReader(file)); /* Open file */

            while ((line = br.readLine()) != null) {
                lines++;
                addProduct(line);
            }
            elapsed = crono.stop();

            System.out.println(file + " was read.");
            System.out.println(lines + " products were stored.");
            System.out.format("Elapsed time: %2.6f\n", elapsed);
        }
        catch (Exception e) {
            System.out.println("The file does not exist.");
        }
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
        String client = sc.nextLine().trim().replaceAll("[\n\r]", "");
        c.start();
        ArrayList<String> clients = market.getClientsByInitial(client);
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
        String product = sc.nextLine().trim().replaceAll("[\n\r]","");
        c.start();
        ArrayList<String> products = market.getProductsByInitial(product);
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
     * @param client    The client that bought the product
     * @param month     The month in which the sale ocurred
     * @param s         The instance of sale to be added
     */
    public void registerSale (String client, int month, Sale s) throws InvalidMonthException {
        market.registerSale(client, month, s);
    }

    /**
     * Show client monthly sales and the total spent on each month
     */
    public void clientAnualSales () {
        Scanner sc = new Scanner(System.in);
        Crono c = new Crono();      /* Cronometer */
        double elapsed;             /* Time elapsed */
        int total_sales = 0, total_products = 0;
        float total_spent = 0;

        clean();
        System.out.print("Client: ");
        String client = sc.nextLine().trim().replaceAll("[\n\r]", "");

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
     * Query 3
     * Given a valid month, display the number of sales and the number
     * of different clients that purchased in that month
     */
    public void query3 () {
        Scanner sc = new Scanner(System.in);
        ParSaleClient psc;
        Crono c = new Crono();
        double elapsed;

        clean();
        System.out.print("Month: ");
        try {
            int month = sc.nextInt();
            c.start();
            psc = market.getMonthInfo(month);
            elapsed = c.stop();

            System.out.format("Time elapsed: %1.6f seconds\n\n", elapsed);

            System.out.println("Num of Sales: " + psc.getSales());
            System.out.println("Num of clients: " + psc.getClients());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (System.console() != null)
            System.console().readLine();
    }

    /**
     * Query 6
     * Given a product code, display information regarding its sale
     * across month and type (promotion and normal)
     */
    public void query6 () {
        Scanner sc = new Scanner(System.in);
        clean();
        String product;
        Crono c = new Crono();

        System.out.print("Product to get monthly sales: ");
        product = sc.nextLine().replaceAll("[\n\r]", "");

        c.start();

        String result = market.productSalesByMonth(product);
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        System.out.println(result);


        if(System.console() != null)
            System.console().readLine();
    }

    /**
     * Query 7
     * Given a valid client code, get the list of
     * the most bought products in decreasing order
     * of quantity
     */
    public void query7 () {
        Scanner sc = new Scanner(System.in);
        List<ParProductUnits> result;
        Crono c = new Crono();
        double elapsed;

        clean();
        System.out.print("Client: ");
        try {
            String product = sc.nextLine().replaceAll("[\n\r]", "");
            c.start();
            result = market.getProductsMostBought(product);
            elapsed = c.stop();

            System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
            System.out.format("%8s | %8s\n", "Product", "Units");
            for (ParProductUnits par : result)
                System.out.format("%8s | %8d\n", par.getProductCode(), par.getUnitsSold());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (System.console() != null)
            System.console().readLine();
    }

    public void query9 () {
        Scanner sc = new Scanner(System.in);
        List<ParClientQuant> result;
        Crono c = new Crono();
        double elapsed;

        clean();
        System.out.print("How many?");

        c.start();
        result = market.getTopClients(sc.nextInt());
        elapsed = c.stop();

        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
        System.out.format("%8s | %8s\n", "Clients", "Products");
        for(ParClientQuant par : result){
            System.out.format("%8s | %8d\n", par.getClientCode(), par.getProducts());
        }
        if(System.console() != null)
            System.console().readLine();
    }

    public void query10 () {
        Scanner sc = new Scanner(System.in);
        List<ParClientQuant> result;
        Crono c = new Crono();
        double elapsed;

        clean();
        System.out.print("Product: ");
        String s = sc.nextLine().replaceAll("[\n\r]", "");
        System.out.print("How many?");

        int number = sc.nextInt();
        c.start();
        result = market.getTopClients(s, number);
        elapsed = c.stop();


        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
        System.out.format("%8s | %8s | %8s\n", "Clients", "Units", "Invoiced");
        for(ParClientQuant par : result){
            System.out.format("%8s | %8d | %8.2f\n", par.getClientCode(), par.getProducts(), par.getInvoice());
        }
        if(System.console() != null)
            System.console().readLine();
    }

    /**
     * Query 5
     * Given a valid product code, get the list
     * of diferent clients that bought the product
     * and the total invoiced each month
     */
    public void query5 () {
        Scanner sc = new Scanner(System.in);
        List<ParClientFat> list;
        int i;
        Crono c = new Crono();
        double elapsed;
        String product;

        clean();
        System.out.print("Product: ");
        try {
            product = sc.nextLine().replaceAll("[\n\r]", "");
            c.start();
            list = market.getProductClientsSales(product);
            elapsed = c.stop();

            System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
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

    public void query8 () {
        Scanner sc = new Scanner(System.in);
        Crono c = new Crono();
        double elapsed;

        clean();
        System.out.print("Number of products: ");
        ArrayList<TripProdCliUnits> list;

        try {

            int number = sc.nextInt();
            c.start();
            list = market.getMostBoughtProducts(number);
            elapsed = c.stop();

            System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
            System.out.format("%7s | %7s | %7s\n", "Product", "Clients", "Units");
            System.out.println("---------------------------");
            for (TripProdCliUnits aux : list)
                System.out.format("%7s | %7d | %7d\n", aux.getProduct(), aux.getNumOfClients(), aux.getUnits());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (System.console() != null)
            System.console().readLine();
    }

    public void queryStat() {
        Crono c = new Crono();
        double elapsed;

        clean();
        c.start();
        String s = fileSales;
        int num_products = plines;
        int unused_products = market.getUnusedProducts().size();
        int bought_products = num_products - unused_products;
        int num_clients = clines;
        int cheap_clients = market.getCheapClients().size();
        int buyer_clients = num_clients - cheap_clients;
        int num_zeroSales = zerovalueSales;
        double total_invoice = market.getTotalInvoice();
        c.stop();

        System.out.format("File name: %s\n", s);
        System.out.format("Total products: %d\n", num_products);
        System.out.format("Total different products bought: %d\n", bought_products);
        System.out.format("Total products not bought: %d\n", unused_products);
        System.out.format("Total clients: %d\n", num_clients);
        System.out.format("Total clients who purchased products: %d\n", buyer_clients);
        System.out.format("Total clients who didn't purchased: %d\n", cheap_clients);
        System.out.format("Total sales with value 0(zero): %d\n", num_zeroSales);
        System.out.format("Total invoice: %.2f\n", total_invoice);

        if(System.console() != null)
            System.console().readLine();
    }

    public void queryStat2(){
        Crono c = new Crono();
        double elapsed;

        clean();
        c.start();
        List<List<Double>> listlist = market.getStatInfo2();
        List<Double> sales = listlist.get(0);
        List<Double> invoice = listlist.get(1);
        List<Double> clients = listlist.get(2);
        elapsed = c.stop();

        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
        System.out.format("%5s | %5s | %10s | %7s\n", "Month", "Sales", "Invoice", "Different Clients");
        for(int i = 0; i < 12; i++){
            System.out.format("%5d | %5d | %7.2f | %7d\n", i+1, sales.get(i).intValue(), invoice.get(i), clients.get(i).intValue());
        }
        System.out.format("---------------------------\n");
        System.out.format("Total invoice: %f\n", invoice.get(12));
        System.out.format("Total invalid sales: %d\n", invalid_lines);

        if (System.console() != null)
            System.console().readLine();

    }

    /**
     * Save application state to an object file 'hipermercado.obj'
     */
    public void saveState () {
        Crono c = new Crono();
        double elapsed = 0;
        try {
            c.start();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hipermercado.obj"));

            oos.writeObject(market);
            oos.flush();
            oos.close();
            elapsed = c.stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
    }

    /**
     * Load a application state from the file 'hipermercado.obj'
     */
    public void loadState () {
        Crono c = new Crono();
        double elapsed = 0;
        try {
            c.start();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hipermercado.obj"));

            market = (Hypermarket) ois.readObject();
            ois.close();
            elapsed = c.stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
    }

    /**
     * Display the menu to the user
     */
    public void showMenu () {
        clean();
        
        System.out.println(" 1: List of Clients by initial");
        System.out.println(" 2: List of Products by initial");
        System.out.println(" 3: (1)  List of products never bought");
        System.out.println(" 4: (2)  List of clients with no expense");
        System.out.println(" 5: (3)  Number of sales and clients in a month");
        System.out.println(" 6: (4)  Client anual sales");
        System.out.println(" 7: (5)  Product sales");
        System.out.println(" 8: (6)  Product sales by month and type");
        System.out.println(" 9: (7)  List of a client most bought products");
        System.out.println("10: (8)  Top n products");
        System.out.println("11: (9)  Top n clients");
        System.out.println("12: (10) Top clients for a product");
        System.out.println("13: Statistical query");
        System.out.println("14: Statistical query 2");
        System.out.println("15: Reload with another clients file");
        System.out.println("16: Reload with another products file");
        System.out.println("17: Reload with another sale file");
        System.out.println("18: Save application state");
        System.out.println("19: Load application state");
        System.out.println("20: Exit");
    }

    /**
     * Check if a client exists
     * @param client The client code
     * @return true if the client exists, false otherwise
     */
    public boolean clientExists (String client) throws NullPointerException {
        return market.existsClient(client);
    }

    /**
     * Check if a product exists
     * @param product The product code
     * @return true if the product exists, false otherwise
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

    /**
     * Load a clients file
     * @param first if true, will load last known name, if not will ask for new file
     */
    public void loadClients (boolean first) {
        Scanner sc = new Scanner(System.in);
        String file, line;
        Crono c = new Crono();

        if (first)
            file = this.fileClients;
        else {
            clean();
            System.out.print("Type the name of the clients file: ");
            file =  sc.nextLine().trim().replaceAll("[\n\r]", "");
            this.fileClients = file;
        }

        /* Read clients file */
        c.start();
        clines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                clines++;
                this.addClient(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        System.out.println("'" + file + "' read.");
        System.out.format("%d Clients added.\n\n", clines);
        if (!first) this.loadSales(first);
    }

    /**
     * Load a products file
     * @param first if true, will load last known name, if not will ask for new file
     */
    public void loadProducts (boolean first) {
        Scanner sc = new Scanner(System.in);
        String file, line;
        Crono c = new Crono();

        if (first)
            file = this.fileProducts;
        else {
            clean();
            System.out.print("Type the name of the products file: ");
            file =  sc.nextLine().trim().replaceAll("[\n\r]", "");
            this.fileProducts = file;
        }

        c.start();
        plines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                this.addProduct(line);
                plines++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        System.out.println("'" + file + "' read.");
        System.out.format("%d Products added.\n\n", plines);
        if (!first) this.loadSales(first);
    }

    /**
     * Load a sales file
     * @param first if true, will load last known name, if not will ask for new file
     */
    public void loadSales (boolean first) {
        Scanner sc = new Scanner(System.in);
        String file, line;
        int lines;
        Crono c = new Crono();

        System.out.print("Do you wish to save invalid sales? (y/n) ");
        boolean write_invalid = (sc.nextLine().trim().equals("y")) ? true : false;
        PrintWriter pw = null;

        if (write_invalid) {
            System.out.print("File: ");
            file = sc.nextLine().trim().replaceAll("[\n\r]","");
            try {
                pw = new PrintWriter(file);
                pw.printf("%7s %7s %7s %7s %7s %7s\n", "Product", "Price", "Units", "Type", "Client", "Month");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        if (first)
            file = this.fileSales;
        else {
            System.out.print("Type the name of the sales file: ");
            file =  sc.nextLine().trim().replaceAll("[\n\r]", "");
            this.fileSales = file;
        }

        c.start();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringTokenizer st;
            int invalid_clients = 0, invalid_products = 0;
            boolean invalid;        /* Keep control of invalid lines */

            while ((line = br.readLine()) != null) {
                invalid = false;
                int i = 1;
                st = new StringTokenizer(line, " ");
                Sale sale = new Sale();
                String client, product;
                float price;
                int units, month;
                boolean type;

                /* Parse line */
                product = st.nextToken();
                price   = Float.parseFloat(st.nextToken());
                units   = Integer.parseInt(st.nextToken());
                price   = price * units;
                type    = st.nextToken().equals("P");
                client  = st.nextToken();
                month   = Integer.parseInt(st.nextToken());

                try {
                    sale.setProduct(product);
                    sale.setPrice(price);
                    sale.setUnits(units);
                    sale.setType(type);

                    if (!this.clientExists(client)) { invalid = true; invalid_clients++; invalid_lines++; }
                    else if (!this.productExists(product)) { invalid = true; invalid_products++; invalid_lines++; }
                    else {
                        if(price == 0) zerovalueSales++;
                        this.registerSale(client, month, sale);
                    }
                } catch (Exception e) {
                }

                if (invalid && write_invalid) {
                    pw.printf("%7s %7.2f %7d %7s %7s %7d\n", product, price, units, type ? "P" : "N", client, month);
                }
            }

            if (write_invalid) { pw.flush(); pw.close(); }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        System.out.println("'" + file + "' read.");

        if (System.console() != null)
            System.console().readLine();
    }
}
