import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

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
            for (int i = 0; i < 5; i++) {
                line = br.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens())
                    System.out.println(st.nextToken());
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
                case 3: finished = true;            break;
                default:                            break;
            }
        }
    }
}
