import Exceptions.InvalidMonthException;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.*;

/**
 * Store all information of what client bought what products for every month
 *
 * @author jfc
 * @version 10/06/2015
 */
public class Sales implements Serializable {
    private ArrayList<TreeMap<String, ArrayList<Sale>>> sales;  /* Sales information by month */

    /**
     * Unparameterized constructor
     */
    public Sales () {
        this.sales = new ArrayList<TreeMap<String, ArrayList<Sale>>>();
        for (int i = 0; i < 12; i++)
            this.sales.add(i, new TreeMap<String, ArrayList<Sale>>());
    }

    /**
     * Add a sale to a given client in a given month
     * @param client The client who made the sale
     * @param month The month in which the sale was made
     * @param sale The sale to be added
     */
    public void addSale (String client, int month, Sale sale) throws NullPointerException, InvalidMonthException {
        if (sale == null)
            throw new NullPointerException("sale can't be null.");
        else if(month < 1 || month > 12){
            throw new InvalidMonthException("month must be <= 12 and >=1");
        }else {
            TreeMap<String,ArrayList<Sale>> map = this.sales.get(month - 1);
            ArrayList<Sale> sales_month = map.get(client);

            if (sales_month != null) sales_month.add(sale);
            else {
                sales_month = new ArrayList<Sale>();
                sales_month.add(sale);
                map.put(client, sales_month);
            }
        }
    }

    /**
     * Get list of sales associated to a client in a given month
     * @param client The client code
     * @param month The month
     * @return List with clones of all sales associated to the client
     * in the given month, if there were no sales, null will be returned
     */
    public List<Sale> getClientSalesMonth (String client, int month) throws InvalidMonthException, NullPointerException {
        ArrayList<Sale> month_sales = null, client_sales;

        if (client == null)
            throw new NullPointerException("client can't be null.");
        else if ((month < 1) || (month > 12))
            throw new InvalidMonthException("month must be <= 12 and >=1");

        client_sales = this.sales.get(month - 1).get(client);

        if (client_sales != null) {
            month_sales = new ArrayList<Sale>();

            for (Sale s : client_sales)
                month_sales.add(s.clone());
        }
        return month_sales;
    }

    /**
     * Creates a pair with the number of sales and the number of
     * clients that made purchases on a given month
     * @param month The month to be checked
     */
    public ParSaleClient getMonthInfo (int month) throws InvalidMonthException {
        ParSaleClient psc = null;

        if ((month < 1) || (month > 12))
            throw new InvalidMonthException("month must be >= 1 and <= 12");
        else {
            psc = new ParSaleClient();
            TreeMap<String, ArrayList<Sale>> map = this.sales.get(month - 1);

            for (ArrayList<Sale> list : map.values()) {
                psc.addClient(1);
                psc.addSale(list.size());
            }
        }

        return psc;
    }

    /**
     * Get the list of product codes most bought by a given client
     * @param client The client code
     * @return List of product codes, ordered from most bought to least bought.
     * In case of equal amounts, the products are ordered lexicographically.
     */
    public List<ParProductUnits> getProductsMostBought(String client) throws IllegalArgumentException, NullPointerException{
        if(client == null)
            throw new NullPointerException("client can't be null");
        try{
            new Client(client);
        }catch(IllegalArgumentException iae){
            throw new IllegalArgumentException(iae.getMessage());
        }

        List<ParProductUnits> codes = new ArrayList<ParProductUnits>();
        Map<String, Integer> temp = new TreeMap<String, Integer>();
        Map<String, ArrayList<Sale>> monthly_sales;

        for(int i = 0; i < 12; i++){
            monthly_sales = sales.get(i);
            if(monthly_sales.containsKey(client)) {
                for (Sale s : monthly_sales.get(client)) {
                    if (!temp.containsKey(s.getProduct())) {
                        temp.put(s.getProduct(), s.getUnits());
                    } else {
                        temp.put(s.getProduct(), temp.get(s.getProduct()) + s.getUnits());
                    }
                }
            }
        }

        Iterator it = temp.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pairs = (Map.Entry) it.next();
            ParProductUnits ppu = new ParProductUnits((String)pairs.getKey());
            ppu.addUnitsSold((Integer)pairs.getValue());
            codes.add(ppu);
        }

        Collections.sort(codes, new Comparator<ParProductUnits>(){
            public int compare(ParProductUnits a, ParProductUnits b){
                if (a.compareTo(b) < 0) return 1;
                else if(a.compareTo(b) > 0) return -1;
                else return a.compareTo(b);
            }
        });

        return codes;
    }

    /**
     * Get the list of number of clients and number of units
     * of the n most bought products during the year
     * @param n Number of products
     */
    public ArrayList<ParClientUnits> getMostBoughtProducts (int n) {
        /* Map between product code and units */
        Map<String, Integer> map_units = new TreeMap<String, Integer>();
        /* Map between units sold and the pairs for which
        products sold that number of units */
        TreeMap<Integer, Map<String, ParClientUnits>> map_pair = new TreeMap<Integer, Map<String, ParClientUnits>>();
        int i;

        for (i = 0; i < 12; i++) {
            /* Get sales for the current month */
            TreeMap<String, ArrayList<Sale>> month = sales.get(i);

            /* Iterate over every client */
            for (String client : month.keySet()) {
                /* Get sales of the client */
                ArrayList<Sale> client_sales = month.get(client);

                /* Iterator over sales */
                for (Sale s : client_sales) {
                    /* Get the product */
                    String product = s.getProduct();
                    /* Check if the product is already inserted */
                    Integer units = map_units.get(product);

                    if (units == null) {    /* First time the product appears */
                        units = s.getUnits();
                        ParClientUnits par = new ParClientUnits(product, client, units);
                        /* Put the new product in the units map */
                        map_units.put(product, units);

                        Map<String, ParClientUnits> aux = map_pair.get(units);

                        if (aux == null) {
                            aux = new TreeMap<String, ParClientUnits>();
                            aux.put(product, par);
                            map_pair.put(units, aux);
                        } else aux.put(product, par);
                    } else {                /* The product has already appeared */
                        Map<String, ParClientUnits> map_aux = map_pair.get(units);
                        ParClientUnits par = map_aux.get(product);
                        map_aux.remove(product);

                        /* Update information */
                        par.addClient(client);
                        par.addUnits(s.getUnits());
                        units = par.getUnits();

                        map_units.put(product, units);

                        Map<String, ParClientUnits> aux = map_pair.get(units);

                        if (aux == null) {
                            aux = new TreeMap<String, ParClientUnits>();
                            aux.put(product, par);
                            map_pair.put(units, aux);
                        } else aux.put(product, par);
                    }
                }
            }
        }

        ArrayList<ParClientUnits> list = new ArrayList<ParClientUnits>();
        int index = 0;

        for (Integer x : map_pair.descendingKeySet()) {
            for (ParClientUnits par : map_pair.get(x).values()) {
                list.add(index, par);
                index++;
            }
            if (index >= n)  break;
        }

        return list;
    }

    /**
     * Create list of the different clients and total invoiced
     * for a given product every month of the year
     * @param product The product code
     */
    public List<ParClientFat> getProductClientsSales (String product) {
        List<ParClientFat> list = new ArrayList<ParClientFat>(12);
        TreeMap<String, ArrayList<Sale>> map;
        ParClientFat par;

        for (int i = 0; i < 12; i++) {
            map = sales.get(i);
            par = new ParClientFat(product);
            for (String client : map.keySet()) {
                ArrayList<Sale> sales = map.get(client);
                for (Sale s : sales) {
                    boolean comp = s.getProduct().equals(product);
                    if (s.getProduct().equals(product)) {
                        par.addClient(client);
                        par.addInvoice(s.getPrice());
                    }
                }
            }

            list.add(i, par);
        }
        return list;
    }

    /**
     * Get the top X clients who bought more different products and how many
     * different products they bought
     * @param x The top number of clients who bought more different products
     * @return List of client codes, sorted in descending order relative to
     * how many different products each client bought. In case of equal amounts,
     * the clients are ordered lexicographically.
     */
    public List<ParClientQuant> getTopClients(int x){
        List<ParClientQuant> codes = new ArrayList<ParClientQuant>();
        Map<String, ArrayList<String>> temp = new TreeMap<String, ArrayList<String>>();

        for(int i = 0; i < 12; i++){
            Iterator it = sales.get(i).entrySet().iterator();
            while(it.hasNext()){
                Map.Entry pairs = (Map.Entry) it.next();
                if(!temp.containsKey(pairs.getKey())){
                    temp.put((String)pairs.getKey(), new ArrayList<String>());
                }
                for(Sale s : (ArrayList<Sale>) pairs.getValue()){
                    if(!temp.get(pairs.getKey()).contains(s.getProduct())){
                        temp.get(pairs.getKey()).add(s.getProduct());
                    }
                }
            }
        }

        Iterator it = temp.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pairs = (Map.Entry) it.next();
            ParClientQuant pcq = new ParClientQuant((String)pairs.getKey());
            ArrayList<String> pc = (ArrayList<String>) pairs.getValue();
            pcq.addProduct(pc.size());
            codes.add(pcq);
        }

        Collections.sort(codes, new Comparator<ParClientQuant>(){
            public int compare(ParClientQuant a, ParClientQuant b){
                if (a.compareTo(b) < 0) return 1;
                else if(a.compareTo(b) > 0) return -1;
                else return a.compareTo(b);
            }
        });

        if(x > codes.size()) {
            return codes;
        }else
            return codes.subList(0, x);
    }

    /**
     * Given a product code, get a list with the top X clients who bought
     * said product the most, and much they spent
     * @param product The product code
     * @param x The top number of clients
     * @return List of client codes, sorted in descending order relative to
     * how many different products each client bought. In case of equal amounts,
     * the clients are ordered lexicographically.
     */
    public List<ParClientQuant> getTopClients(String product, int x) throws NullPointerException, IllegalArgumentException{
        if(product == null)
            throw new NullPointerException("client can't be null");
        try{
            new Product(product);
        }catch(IllegalArgumentException iae){
            throw new IllegalArgumentException(iae.getMessage());
        }

        List<ParClientQuant> codes = new ArrayList<ParClientQuant>();
        Map<String, double[]> temp = new TreeMap<String, double[]>();

        for(int i = 0; i < 12; i++){
            Iterator it = sales.get(i).entrySet().iterator();
            while(it.hasNext()){
                Map.Entry pairs = (Map.Entry) it.next();
                for(Sale s : (ArrayList<Sale>) pairs.getValue()){
                    if(s.getProduct().equals(product)){
                        double[] inv;
                        if(!temp.containsKey(pairs.getKey())){
                            inv = new double[2];
                        }else{
                            inv = temp.get(pairs.getKey());
                        }
                        inv[0] += (double)s.getUnits();
                        inv[1] += s.getPrice();
                        temp.put((String)pairs.getKey(), inv);
                    }
                }
            }
        }

        Iterator it = temp.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pairs = (Map.Entry) it.next();
            ParClientQuant pcq = new ParClientQuant((String)pairs.getKey());
            double[] pc = (double[]) pairs.getValue();
            pcq.addProduct((int)pc[0]);
            pcq.addInvoice(pc[1]);
            codes.add(pcq);
        }

        Collections.sort(codes, new Comparator<ParClientQuant>(){
            public int compare(ParClientQuant a, ParClientQuant b){
                if (a.compareTo(b) < 0) return 1;
                else if(a.compareTo(b) > 0) return -1;
                else return a.compareTo(b);
            }
        });

        if(x > codes.size()) {
            return codes;
        }else
            return codes.subList(0, x);
    }
}
