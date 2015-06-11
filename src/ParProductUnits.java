/**
 * Class to define a pair that will store the number of units a
 * product was bought by a given client and
 * the code of the said product
 *
 * @author ls
 * @version 11/06/2015
 */
public class ParProductUnits {
    private String product_code; /* product code */
    private int num_sold; /* number of times the product was bought */

    /**
     * Unparameterized constructor, sets the product code to an
     * empty string and the number of times it was bought to 0
     */
    public ParProductUnits () {
        this.setProduct("");
        this.num_sold = 0;
    }

    /**
     * Parameterized contructor, sets the product code to
     * the string passed as argument and the number of times
     * it was bought ot 0
     * @param code The product code
     */
    public ParProductUnits(String code){
        this.setProduct(code);
        this.num_sold = 0;
    }

    /**
     * Set the product code
     * @param code The product code
     */
    private void setProduct(String code){
        this.product_code = code;
    }

    /**
     * Increment the number of times the product was
     * bought
     * @param num How much to increment
     */
    public void addUnitsSold (int num) {
        num_sold += num;
    }

    /**
     * Get number of clients
     */
    public String getProductCode () {
        return product_code;
    }

    /**
     * Get number of sales
     */
    public int getUnitsSold () {
        return num_sold;
    }

    /* equals, toString and clone */

    /**
     * Check if two ParProductUnits are equal
     * @param ppu Object with which to compare
     */
    public boolean equals (Object ppu) {
        if (ppu == this) return true;

        if ((ppu == null) || (ppu.getClass() != this.getClass())) return false;

        ParProductUnits aux = (ParProductUnits) ppu;
        return ((this.getUnitsSold() == aux.getUnitsSold()) && (this.getProductCode().equals(aux.getProductCode())));
    }


    /**
     * Compares the ParProductUnits object that invoked the method to
     * the argument.
     * @param ppu ParProductUnits with which to compare
     * @return Returns 1 if the ParProductUnits unitsSold is greater than the argument.
     * Returns -1 if the ParProductUnits unitsSold is less than the argument.
     * If they are equal, it returns the comparison between their product codes.
     */
    public int compareTo(ParProductUnits ppu){
        if(this.getUnitsSold() != ppu.getUnitsSold()){
            if(this.getUnitsSold() > ppu.getUnitsSold()) return 1;
            else return -1;
        }else{
            return this.getProductCode().compareTo(ppu.getProductCode());
        }
    }
}
