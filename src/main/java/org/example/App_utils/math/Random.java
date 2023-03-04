package App_utils.math;

import java.util.List;

/**
 * Provides methods to do action based on random values.
 */
public class Random {

    /**
     * Returns a random int value between the given boundaries.
     * @param minValue  min boundary
     * @param maxValue  max boundary
     * @return  a random int between the boundaries.
     */
    public static int randomInt(int minValue, int maxValue) {
        if (minValue >= maxValue)
            throw new InvertedMinAndMaxBoundException("minValue must be less than maxValue.");
        java.util.Random rand = new java.util.Random();
        return rand.nextInt(maxValue - minValue + 1) + minValue;
    }

    /**
     * Returns a random object of a given list.
     * @param list List to choose items from.
     * @return A random object from the list.
     * @param <T> Type of the objects contained within the given list.
     */
    public static <T> T selectRandom (List<T> list){
        if(list == null)
            return null;
        int randomIndex = randomInt(0, list.size()-1);
        return list.get(randomIndex);
    }

    /**
     * Returns a random object of a given array.
     * @param array array to choose items from.
     * @return A random object from the array.
     * @param <T> Type of the objects contained within the given list.
     */
    public static <T> T selectRandom (T[] array){
        if(array == null)
            return null;
        int randomIndex = randomInt(0, array.length-1);
        return array[randomIndex];
    }
}
