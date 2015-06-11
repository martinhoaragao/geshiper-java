import Exceptions.InvalidMonthException;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
     * @param sale The sale to be added
     */
    public void addSale (String client, int month, Sale sale) throws NullPointerException {
        if (sale == null)
            throw new NullPointerException("sale can't be null.");
        else {
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
    public List<Sale> getClientSalesMonth (String client, int month)
            throws IllegalArgumentException, NullPointerException {
        ArrayList<Sale> month_sales = null, client_sales;

        if (client == null)
            throw new NullPointerException("client can't be null.");
        else if ((month < 0) || (month > 12))
            throw new IllegalArgumentException("month must be <= 12 & >=1");

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
}
