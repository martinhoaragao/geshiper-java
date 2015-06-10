import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Store all information of what client bought what products for every month
 *
 * @author jfc
 * @version 10/06/2015
 */
public class Sales {
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
}
