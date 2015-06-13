import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Classe para testar leitura dos ficheiros com BufferedReader
 * e com Scanner e com Parsing e Sem Parsing
 */
public class Testing {

    public static void main (String[] args) {
        Crono c = new Crono();                      /* Cronometer */
        double elapsed;                             /* Total time elapsed */
        String line;                                /* Actual line */
        BufferedReader br = null;                   /* Reader */
        Scanner sc = null;
        StringTokenizer st;

        System.out.println();
        System.out.println(">>>>>>>>>>>>>> BUFFEREDREADER <<<<<<<<<<<<<<<<");
        System.out.println();

        /* Read client file without parsing */
        System.out.println("----- SALES 500K (NO PARSING) ---------");
        try {
            c.start();
            br = new BufferedReader(new FileReader("Compras.txt"));

            while ((line = br.readLine()) != null) {

            }
            elapsed = c.stop();
            System.out.format("Time elapsed: %2.6f seconds\n", elapsed);
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* Read products file without parsing */
        System.out.println("----- SALES 1M (NO PARSING) ---------");
        try {
            c.start();
            br = new BufferedReader(new FileReader("Compras1.txt"));

            while ((line = br.readLine()) != null) {

            }
            elapsed = c.stop();
            System.out.format("Time elapsed: %2.6f seconds\n", elapsed);
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* Read Sales file without parsing */
        System.out.println("----- SALES 3M (NO PARSING) ---------");
        try {
            c.start();
            br = new BufferedReader(new FileReader("Compras3.txt"));

            while ((line = br.readLine()) != null) {

            }
            elapsed = c.stop();
            System.out.format("Time elapsed: %2.6f seconds\n", elapsed);
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* Read client file with parsing */
        System.out.println("----- SALES 500K (PARSING) ---------");
        c.start();
        try {
            br = new BufferedReader(new FileReader("Compras.txt"));

            while ((line = br.readLine()) != null) {
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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        try { br.close(); }
        catch (Exception e) { System.out.println(e.getMessage()); }

        /* Read client file with parsing */
        System.out.println("----- SALES 1M (PARSING) ---------");
        c.start();
        try {
            br = new BufferedReader(new FileReader("Compras1.txt"));

            while ((line = br.readLine()) != null) {
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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        try { br.close(); }
        catch (Exception e) { System.out.println(e.getMessage()); }

        /* Read client file with parsing */
        System.out.println("----- SALES 3M (PARSING) ---------");
        c.start();
        try {
            br = new BufferedReader(new FileReader("Compras3.txt"));

            while ((line = br.readLine()) != null) {
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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        try { br.close(); }
        catch (Exception e) { System.out.println(e.getMessage()); }

        /* -------------------------------------- SCANNER ------------------------- */

        System.out.println();
        System.out.println(">>>>>>>>>>>>>> SCANNER <<<<<<<<<<<<<<<<");
        System.out.println();
        /* Read client file without parsing */
        System.out.println("----- SALES 500K (NO PARSING) ---------");
        try {
            c.start();
            sc = new Scanner(new File("Compras.txt"));

            while (sc.hasNext()) {
                line = sc.next();
            }
            elapsed = c.stop();
            System.out.format("Time elapsed: %2.6f seconds\n", elapsed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sc.close();

        /* Read products file without parsing */
        System.out.println("----- SALES 1M (NO PARSING) ---------");
        try {
            c.start();
            sc = new Scanner(new File("Compras1.txt"));

            while (sc.hasNext()) {
                line = sc.next();
            }
            elapsed = c.stop();
            System.out.format("Time elapsed: %2.6f seconds\n", elapsed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sc.close();

        /* Read Sales file without parsing */
        System.out.println("----- SALES 3M (NO PARSING) ---------");
        try {
            c.start();
            sc = new Scanner(new File("Compras3.txt"));

            while (sc.hasNext()) {
                line = sc.next();
            }
            elapsed = c.stop();
            System.out.format("Time elapsed: %2.6f seconds\n", elapsed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sc.close();

        /* Read client file with parsing */
        System.out.println("----- SALES 500K (PARSING) ---------");
        c.start();
        try {
            sc = new Scanner(new File("Compras.txt"));

            while (sc.hasNext()) {
                line = sc.nextLine();
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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        sc.close();

        /* Read client file with parsing */
        System.out.println("----- SALES 1M (PARSING) ---------");
        c.start();
        try {
            sc = new Scanner(new File("Compras1.txt"));

            while (sc.hasNext()) {
                line = sc.nextLine();
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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        sc.close();

        /* Read client file with parsing */
        System.out.println("----- SALES 3M (PARSING) ---------");
        c.start();
        try {
            sc = new Scanner(new File("Compras3.txt"));

            while (sc.hasNext()) {
                line = sc.nextLine();
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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.format("Time elapsed: %1.6f seconds\n", c.stop());
        sc.close();
    }
}
