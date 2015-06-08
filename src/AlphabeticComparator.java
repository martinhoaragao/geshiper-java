import java.util.Comparator;

/**
 * Created by joaocosta on 08/06/15.
 */
public class AlphabeticComparator implements Comparator<String> {
    public int compare (String a, String b) {
        return a.compareTo(b);
    }
}
