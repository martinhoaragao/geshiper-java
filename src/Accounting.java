import Exceptions.InvalidMonthException;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.*;

/**
 * Store information regarding product sales by month
 *
 * @author ma
 * @version 11/06/2015
 */
public class Accounting implements Serializable {
    private ArrayList<TreeMap <String,ProductTotalSales>> accounting;  /* Sales information by month */

    /**
     * Unparameterized constructor
     */
    public Accounting () {
        this.accounting = new ArrayList<TreeMap <String,ProductTotalSales>>();
        for (int i = 0; i < 12; i++)
            this.accounting.add(i, new TreeMap<String,ProductTotalSales>());
    }

    /**
     * Add a product information to a given month. Update if already there
     * @param month The month in which the sale was made
     * @param sale The sale to be added
     */
    public void addSale (int month, Sale sale) throws NullPointerException, InvalidMonthException {
        if (sale == null)
            throw new NullPointerException("sale can't be null.");
        else if(month < 1 || month > 12){
            throw new InvalidMonthException("month must be <= 12 and >=1");
        } else {
            TreeMap <String,ProductTotalSales> map = this.accounting.get(month - 1);
            String product = sale.getProduct();

            if (map.containsKey(product))
                map.get(product).updateTotalSales(sale);
            else {
                ProductTotalSales pts = new ProductTotalSales();
                pts.updateTotalSales(sale);
                map.put(product, pts);
            }

        }
    }
    /**
     * Not completed
     */
    public String productSalesByMonth (String product) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<12; i++) {
            ProductTotalSales productInfo = this.accounting.get(i).get(product);
            sb.append(productInfo.toString());
        }
        return sb;
    }

}