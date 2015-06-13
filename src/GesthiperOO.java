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
        menu.loadClients(true);

        /* Read products file */
        menu.loadProducts(true);

        /* Read sales file */
        menu.loadSales(true);

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
