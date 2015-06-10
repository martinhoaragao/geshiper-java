import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GesthiperOO {
    private static Crono c = new Crono();
    private static double elapsed;

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        String line;
        int option;

        /* Read clients file */
        c.start();
        try {
            BufferedReader br = new BufferedReader(new FileReader("FichClientes.txt"));
            while ((line = br.readLine()) != null) {
                menu.addClient(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        elapsed = c.stop();
        System.out.println("'FichClientes.txt' read.");
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);

        /* Read products file */
        c.start();
        try {
            BufferedReader br = new BufferedReader(new FileReader("FichProdutos.txt"));
            while ((line = br.readLine()) != null) {
                menu.addProduct(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        elapsed = c.stop();
        System.out.println("'FichProdutos.txt' read.");
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);

        /* Read sales file */
        c.start();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Compras.txt"));
            StringTokenizer st;
            String token;
            ArrayList<String> tokens;

            while ((line = br.readLine()) != null) {
                int i = 1;
                st = new StringTokenizer(line, " ");
                Sale sale = new Sale();
                String client;
                int month;

                sale.setProduct(st.nextToken());
                sale.setPrice(Float.parseFloat(st.nextToken()));
                sale.setUnits(Integer.parseInt(st.nextToken()));
                sale.setType(st.nextToken().equals("P"));
                client = st.nextToken();
                month = Integer.parseInt(st.nextToken());

                menu.registerSale(client, month, sale);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        elapsed = c.stop();
        System.out.println("'Compras.txt' read.");
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);

        if (System.console() != null)
            System.console().readLine();

        /* Show menu to user */
        while (!finished) {
            menu.showMenu();

            option = sc.nextInt();
            switch (option) {
                case 1: menu.getClientsByInitial(); break;
                case 2: menu.getProductsByInitial();break;
                case 3: menu.cheapClients();        break;
                case 4: menu.unusedProducts();      break;
                case 5:
                    sc.nextLine();
                    menu.getClientMonthySales(sc.nextLine());
                    break;
                case 6: finished = true;            break;
                default:                            break;
            }
        }
    }
}