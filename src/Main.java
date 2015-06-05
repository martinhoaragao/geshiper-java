import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
	    String line;
        ClientsCatalog CCat = new ClientsCatalog();

        try {
            BufferedReader br = new BufferedReader(new FileReader("FichClientes.txt"));
            while ((line = br.readLine()) != null) {
                CCat.addClient(new Client(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
