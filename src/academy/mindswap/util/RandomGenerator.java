package academy.mindswap.util;

public class RandomGenerator {
    /**
     * Util method that generates a random integer
     * @param min
     * @param max
     * @return a random int between min and max
     */

    public static int generateRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}