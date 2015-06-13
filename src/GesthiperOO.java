import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
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
        int option, lines;

        /* Read clients file */
        c.start();
        lines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("FichClientes.txt"));
            while ((line = br.readLine()) != null) {
                lines++;
                menu.addClient(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        elapsed = c.stop();
        System.out.println("'FichClientes.txt' read.");
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
        System.out.format("%d Clients added.\n\n", lines);

        /* Read products file */
        c.start();
        lines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("FichProdutos.txt"));
            while ((line = br.readLine()) != null) {
                menu.addProduct(line);
                lines++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        elapsed = c.stop();
        System.out.println("'FichProdutos.txt' read.");
        System.out.format("Time elapsed: %1.6f seconds\n", elapsed);
        System.out.format("%d Products added.\n\n", lines);

        /* Read sales file */
        System.out.print("Do you wish to save invalid sales? (y/n)");
        boolean write_invalid = (sc.nextLine().trim().equals("y")) ? true : false;
        String file;
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

        c.start();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Compras.txt"));
            StringTokenizer st;
            int invalid_clients = 0, invalid_products = 0, invalid_lines = 0;
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
                type    = st.nextToken().equals("P");
                client  = st.nextToken();
                month   = Integer.parseInt(st.nextToken());

                try {
                    sale.setProduct(product);
                    sale.setPrice(price);
                    sale.setUnits(units);
                    sale.setType(type);

                    if (!menu.clientExists(client)) { invalid = true; invalid_clients++; invalid_lines++; }
                    else if (!menu.productExists(product)) { invalid = true; invalid_products++; invalid_lines++; }
                    else menu.registerSale(client, month, sale);
                } catch (Exception e) {
                }

                if (invalid && write_invalid) {
                    pw.printf("%7s %7.2f %7d %7s %7s %7d\n", product, price, units, type ? "P" : "N", client, month);
                }
            }

            pw.flush();
            pw.close();
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
                case 3: menu.unusedProducts();      break;
                case 4: menu.cheapClients();        break;
                case 5: menu.querie3();             break;
                case 6: menu.clientAnualSales();    break;
                case 7: menu.querie5();             break;
                case 8: menu.query6();              break;
                case 9: menu.querie7();             break;
                case 10: menu.querie8();            break;
                case 11: menu.querie9();            break;
                case 12: menu.querie10();           break;
                case 13: menu.saveState();          break;
                case 14: menu.loadState();          break;
                case 15: finished = true;           break;
                default:                            break;
            }
        }
    }
}
