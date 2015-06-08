import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * Get the initial from the user and display the clients
     * list whose code starts with the given initial
     */
    public void getClientsByInitial () {
        Scanner sc = new Scanner(System.in);

        clean();
        System.out.print("Initial: ");
        ArrayList<String> clients = gesthiper.getClientsByInitial(sc.nextLine().trim().replaceAll("[\n\r]", ""));

        for (String code : clients)
            System.out.println(code);
    }

    /**
     * Get the initial from the user and display the clients
     * list whose code starts with the given initial
     */
    public void getProductsByInitial () {
        Scanner sc = new Scanner(System.in);

        clean();
        System.out.print("Initial: ");
        ArrayList<String> clients = gesthiper.getProductsByInitial(sc.nextLine().trim().replaceAll("[\n\r]", ""));

        for (String code : clients)
            System.out.println(code);
        System.out.println(clients.size());
    }

    /**
     * Display the menu to the user
     */
    public void showMenu () {
        clean();

        System.out.println("1: List of Clients by initial");
        System.out.println("2: List of Products by initial");
        System.out.println("3: Exit");
    }

    /**
     * Clean the terminal view
     */
    public void clean () {
     if (System.console() != null)
         System.out.print("\u001b[2J" + "\u001b[H");
    }
}
