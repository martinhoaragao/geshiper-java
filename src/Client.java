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
     * Parameterized constructor, the code must be composed by two uppercase
     * letters and three numbers
     * @param code The client code
     */
    public Client (String code) throws IllegalArgumentException {
        boolean initials, numbers;

        if (code.trim().length() != 5)
            throw new IllegalArgumentException("code can only have 5 characters.");

        /* Check if the two initials area uppercase letters */
        initials    = Character.isUpperCase(code.charAt(0)) && Character.isUpperCase(code.charAt(1));
        numbers     = Character.isDigit(code.charAt(2)) && Character.isDigit(code.charAt(3)) &&
                Character.isDigit(code.charAt(4));

        if (initials && numbers) this.code = code;
        else throw new IllegalArgumentException("Invalid code.");
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
        if (code.trim().length() != 5)
            throw new IllegalArgumentException("code needs to have 5 characters.");
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
