package me.figoxu.middleware;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * User: figo.xu
 * Date: 12-10-17
 * Time: 下午2:23
 *
 */
public class RandomUtil {
    private static Logger logger = Logger.getLogger(RandomUtil.class);

    /** The basic random provider */
    private static Random random = new Random();

    public static Boolean randomBoolean(){
         return  RandomUtil.randomInt(Integer.MIN_VALUE, Integer.MAX_VALUE)-RandomUtil.randomInt(Integer.MIN_VALUE, Integer.MAX_VALUE)>0;
    }

    /** Generates a random long value in the range from min to max */
    public static long randomLong(long min, long max) {
        if (min > max)
            throw new IllegalArgumentException("min (" + min + ") > max (" + max + ")");
        long range = max - min + 1;
        long result;
        if (range != 0)
            result = min + (random.nextLong() % range);
        else
            result = random.nextLong();
        if (result < min)
            result += range;
        return result;
    }

    /** Generates a random int value in the range from min to max */
    public static int randomInt(int min, int max) {
        if (min > max)
            throw new IllegalArgumentException("min > max: " + min + " > " + max);
        int range = max - min + 1;
        int result;
        if (range != 0)
            result = min + (random.nextInt() % range);
        else
            result = random.nextInt();
        if (result < min)
            result += range;
        return result;
    }

    public static <T> T randomElement(T ... values) {
        if (values.length == 0)
            throw new IllegalArgumentException("Cannot choose random value from an empty array");
        return values[random.nextInt(values.length)];
    }

    public static <T> T randomElement(List<T> values) {
        return values.get(randomIndex(values));
    }


    public static int randomIndex(List<?> values) {
        if (values.size() == 0)
            throw new IllegalArgumentException("Cannot create random index for an empty array");
        return random.nextInt(values.size());
    }

    public static char randomDigit(int min) {
        return (char) ('0' + min + random.nextInt(10 - min));
    }

    public static float randomProbability() {
        return random.nextFloat();
    }

    public static Date randomDate(Date min, Date max) {
        return new Date(randomLong(min.getTime(), max.getTime()));
    }


}
