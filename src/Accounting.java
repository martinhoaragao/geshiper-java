import Exceptions.InvalidMonthException;

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
     * Build a string with information regarding sales by each month and type
     * @param product code of the product to get info from
     * @return string with information already formatted for print
     */
    public String productSalesByMonth (String product) {
        StringBuilder sb = new StringBuilder();

        String topBar = "Months | Normal Units | Normal Revenue | Promo Units | Promo Revenue\n";
        sb.append(topBar);

        for (int i = 0; i<12; i++) {

            ProductTotalSales productInfo = this.accounting.get(i).get(product);
            if (productInfo != null) {
                String monthInfo = String.format("%-6d | %-12d | %-14.5f | %-11d | %-13.5f", i,  productInfo.getNormalUnits(), productInfo.getNormalRevenue(), productInfo.getPromoUnits(), productInfo.getPromoRevenue());

                sb.append(monthInfo + "\n");
            }

        }
        String result = sb.toString();
        if (result.equals(topBar))
            return "Product not valid";
        else
            return sb.toString();
    }

    public List<Double> getInvoiceByMonth(){
        List<Double> res = new ArrayList<Double>();
        double temp = 0;
        double temp2 = 0;

        for(int i = 0; i < 12; i++){
            temp = 0;
            for(ProductTotalSales pts : accounting.get(i).values()){
                temp += (pts.getNormalRevenue() + pts.getPromoRevenue());
            }
            temp2 += temp;
            res.add(temp);
        }
        res.add(temp2);
        return res;
    }

}
