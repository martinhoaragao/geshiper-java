import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GesthiperOO {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        String line;
        int option;

        /* Read clients file */
        try {
            BufferedReader br = new BufferedReader(new FileReader("FichClientes.txt"));
            while ((line = br.readLine()) != null) {
                Client c = new Client(line.trim());
                menu.addClient(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* Read products file */
        try {
            BufferedReader br = new BufferedReader(new FileReader("FichProdutos.txt"));
            while ((line = br.readLine()) != null) {
                Product p = new Product(line.trim());
                menu.addProduct(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* Read sales file */
        try {
            BufferedReader br = new BufferedReader(new FileReader("Compras.txt"));
            StringTokenizer st;
            String token;
            ArrayList<String> tokens = new ArrayList<String>();

            while ((line = br.readLine()) != null) {
                int i = 1;
                st = new StringTokenizer(line, " ");

                while (st.hasMoreTokens()) {
                    token = st.nextToken();
                    if (i == 1) tokens.add(token);
                    if (i == 5) tokens.add(token);
                    i++;
                }
                menu.registerSale(tokens);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* Show menu to user */
        while (!finished) {
            menu.showMenu();

            option = sc.nextInt();
            switch (option) {
                case 1: menu.getClientsByInitial(); break;
                case 2: menu.getProductsByInitial();break;
                case 3: menu.cheapClients();        break;
                case 4: menu.unusedProducts();      break;
                case 5: finished = true;            break;
                default:                            break;
            }
        }
    }
}