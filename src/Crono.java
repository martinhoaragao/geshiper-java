/**
 * Class to serve as a cronometer
 *
 * @author jfc
 * @version 10/06/2015
 */
public class Crono {
    private static long init    = 0L;
    private static long end     = 0L;


    /**
     * Start the cronometer
     */
    public void start () {
        end = 0L; init = System.nanoTime();
    }

    /**
     * Stop the cronometer and get the time elapsed
     * @return The time elapsed
     */
    public double stop () {
        end = System.nanoTime();
        long elapsed = end - init;
        double segs = elapsed / 1.0E09;
        return segs;
    }
}
