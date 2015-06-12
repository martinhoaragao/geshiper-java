/**
 * Class to define a pair that will store the number of different products
 * a client bought, and the code of said client
 *
 * @author ls
 * @version 12/06/2015
 */
public class ParClientQuant {
    private String client_code; /* client code */
    private int num_products; /* number of products the client bought */
    private double fat; /* how much the client spent with the products */

    /**
     * Unparameterized constructor, sets the client code to an
     * empty string and the number of different products bought
     * by the client to 0
     */
    public ParClientQuant () {
        this.setClient("");
        this.num_products = 0;
        this.fat = 0;
    }

    /**
     * Parameterized contructor, sets the client code to
     * the string passed as argument and the number of different products bought
     * by the client to 0
     * @param code The product code
     */
    public ParClientQuant(String code){
        this.setClient(code);
        this.num_products = 0;
        this.fat = 0;
    }

    /**
     * Set the client code
     * @param code The client code
     */
    private void setClient(String code){
        this.client_code = code;
    }

    /**
     * Increment the number of products bought
     * by the client
     * @param num How much to increment
     */
    public void addProduct (int num) {
        num_products += num;
    }

    /**
     * Increment the invoice spent by the client
     * @param num How much to increment
     */
    public void addInvoice (double num){
        this.fat += num;
    }


    /**
     * Get client code
     */
    public String getClientCode () {
        return client_code;
    }

    /**
     * Get number of products
     */
    public int getProducts () {
        return num_products;
    }

    /**
     * Get how much the client spent with the products
     */
    public double getInvoice () {
        return this.fat;
    }


    /* equals, toString and clone */

    /**
     * Check if two ParClientQuant are equal
     * @param pcq Object with which to compare
     */
    public boolean equals (Object pcq) {
        if (pcq == this) return true;

        if ((pcq == null) || (pcq.getClass() != this.getClass())) return false;

        ParClientQuant aux = (ParClientQuant) pcq;
        return ((this.getProducts() == aux.getProducts()) &&
                (this.getClientCode().equals(aux.getClientCode())));
    }


    /**
     * Compares the ParClientQuant object that invoked the method to
     * the argument.
     * @param pcq ParClientQuant with which to compare
     * @return Returns 1 if the ParClientQuant unitsSold is greater than the argument.
     * Returns -1 if the ParClientQuant unitsSold is less than the argument.
     * If they are equal, it returns the comparison between their product codes.
     */
    public int compareTo(ParClientQuant pcq){
        if(this.getProducts() != pcq.getProducts()){
            if(this.getProducts() > pcq.getProducts()) return 1;
            else return -1;
        }else{
            if(this.getClientCode().compareTo(pcq.getClientCode()) > 0){
                return -1;
            }else if(this.getClientCode().compareTo(pcq.getClientCode()) < 0){
                return 1;
            }else return 0;
        }
    }
}
