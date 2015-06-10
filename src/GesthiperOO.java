import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GesthiperOO {
    private static long init    = 0L;
    private static long end     = 0L;

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        String line;
        int option;

        /* Read clients file */
        init = System.nanoTime();
        try {
            BufferedReader br = new BufferedReader(new FileReader("FichClientes.txt"));
            while ((line = br.readLine()) != null) {
                menu.addClient(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        end = System.nanoTime();
        System.out.println((end - init) / 1.0E09);

        /* Read products file */
        init = System.nanoTime();
        try {
            BufferedReader br = new BufferedReader(new FileReader("FichProdutos.txt"));
            while ((line = br.readLine()) != null) {
                menu.addProduct(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        end = System.nanoTime();
        System.out.println((end - init) / 1.0E09);

        /* Read sales file */
        init = System.nanoTime();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Compras.txt"));
            StringTokenizer st;
            String token;
            ArrayList<String> tokens;

            while ((line = br.readLine()) != null) {
                int i = 1;
                st = new StringTokenizer(line, " ");
                tokens = new ArrayList<String>();

                for (i = 1; i <= 6; i++) {
                    token = st.nextToken();
                    if (i == 1) tokens.add(token);
                    else if (i == 5) tokens.add(token);
                }

                menu.registerSale(tokens);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        end = System.nanoTime();
        System.out.println((end - init) / 1.0E09);

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