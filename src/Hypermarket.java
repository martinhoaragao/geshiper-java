import Exceptions.InvalidMonthException;
import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.tools.corba.se.idl.InvalidArgument;

import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jfc
 * @version 07/06/2015
 */
public class Hypermarket implements Serializable {
    private ProductsCatalog p_cat;  /* The products catalog */
    private ClientsCatalog  c_cat;  /* The clients catalog */
    private Sales sales;            /* The sales module */

    /**
     * Unparameterized Constructor, will initialize instance variables
     */
    public Hypermarket () {
        this.p_cat = new ProductsCatalog();
        this.c_cat = new ClientsCatalog();
        this.sales = new Sales();
    }

    /**
     * Add a client to the clients catalog
     * @param code The client code
     */
    public void addClient (String code) {
        c_cat.addClient(code);
    }

    /**
     * Add a product to the products catalog
     * @param code The product code
     */
    public void addProduct (String code) {
        p_cat.addProduct(code);
    }

    /**
     * Get List of clients whose client code begins with a given initial
     * @param initial The initial letter
     * @return ArrayList<String> with the client codes
     */
    public ArrayList<String> getClientsByInitial (String initial) {
        return c_cat.getClientsByInitial(initial);
    }

    /**
     * Get List of products whose code begins with a given initial
     * @param initial The initial letter
     * @return ArrayList<String> with the product codes
     */
    public ArrayList<String> getProductsByInitial (String initial) {
        return p_cat.getProductsByInitial(initial);
    }

    /**
     * Get List of clients that did not buy any product
     * @return ArrayList<String> with all the client codes
     */
    public ArrayList<String> getCheapClients () {
        return c_cat.getUnusedClients();
    }

    /**
     * Get List of products that no one bought
     * @return ArrayList<String> with all the product codes
     */
    public ArrayList<String> getUnusedProducts () {
        return p_cat.getUnusedProducts();
    }

    /**
     * Get list of number of sales, different products bought and the total spent
     * on every month given a client code
     * @param client The client code
     * @return ArrayList<TripNumProdFat> with info for all months, if in a month
     * there were no sales the value will be null
     */
    public List<TripNumProdFat> getClientMonthlySales (String client) {
        List<Sale> month_sales = null;
        List<TripNumProdFat> total_sales = new ArrayList<TripNumProdFat>(12);

        for (int month = 1; month < 13; month++) {
            try {
                month_sales = sales.getClientSalesMonth(client, month);
            }catch(Exception e){}
            TripNumProdFat trip = new TripNumProdFat();

            if (month_sales != null) {
                for (Sale s : month_sales)
                    trip.addInfo(s.getProduct(), s.getPrice());
            }

            total_sales.add(month - 1, trip);
        }

        return total_sales;
    }

    /**
     * Get the list of the most bought products by a given client
     * @param client The client code
     * @return List of product codes, ordered from most bought to least bought.
     * In case of equal amounts, the products are ordered lexicographically.
     */
    public List<ParProductUnits> getProductsMostBought (String client)
        throws IllegalArgumentException, NullPointerException {
        return sales.getProductsMostBought(client);
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
        return sales.getTopClients(x);
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
    public List<ParClientQuant> getTopClients(String product, int x)
            throws NullPointerException, IllegalArgumentException{
        return sales.getTopClients(product, x);
    }

    /**
     * Get the list of different clients and the invoiced
     * for a given month every month
     * @param product The product code
     */
    public List<ParClientFat> getProductClientsSales (String product) {
        return sales.getProductClientsSales(product);
    }

    /**
     * Check if a given client is present in the Clients Catalog
     * @param client The client code
     */
    public boolean existsClient (String client) throws NullPointerException {
        return c_cat.exists(client);
    }

    /**
     * Given a valid month create pair with the number of sales
     * and the number of different clients that purchased that month
     * @param month
     */
    public ParSaleClient getMonthInfo (int month) throws InvalidMonthException {
        return sales.getMonthInfo(month);
    }

    /**
     * Check if a given product is present in the Products Catalog
     * @param product The Product code
     */
    public boolean existsProduct (String product) throws NullPointerException {
        return p_cat.exists(product);
    }

    /**
     * Register a sale to the sales module
     */
    public void registerSale (String client, int month, Sale sale) throws InvalidMonthException {
        p_cat.markAsBought(sale.getProduct());
        c_cat.removeSpendingClient(client);
        sales.addSale(client, month, sale);
    }
}
