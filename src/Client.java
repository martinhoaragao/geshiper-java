/**
 * Class to represent a client that has a code associated
 *
 * @author jfc
 * @version 05/06/2015
 */
public class Client {
    private String code;    /* The client code */

    /**
     * Unparameterized constructor
     */
    public Client () {
        this.code = new String();
    }

    /**
     * Parameterized constructor
     * @param code The client code
     */
    public Client (String code) {
        this.code = code;
    }

    /**
     * Constructor by copy
     * @param client Client which should be cloned
     */
    public Client (Client client) {
        this.code = client.getCode();
    }

    /* Getters */

    /**
     * @return The client code
     */
    public String getCode () {
        return this.code;
    }

    /* Setters */

    /**
     * Change the client code
     * @param code The new client code
     */
    public void setCode (String code) throws NullPointerException, IllegalArgumentException {
        if (code == null)
            throw new NullPointerException("code can't be null.");
        if (code.trim().equals(""))
            throw new IllegalArgumentException("code can't be empty");
        this.code = code;
    }

    /* equals, toString and clone */

    /**
     * Compare to another client
     * @param client Client with which to compare
     * @return true if the clients are equals, false otherwise
     */
    public boolean equals (Client client) {
        if (client == this) return true;

        if ((client == null) || (this.getClass() != client.getClass())) return false;

        return this.code.equals(client.getCode());
    }

    /**
     * @return String with info about the Client
     */
    public String toString () {
        StringBuilder sb = new StringBuilder();

        sb.append(this.code);
        return sb.toString();
    }

    /**
     * @return Clone of this Client instance
     */
    public Client clone () {
        return new Client(this);
    }
}
