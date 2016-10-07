package Main;

import java.util.Random;

/**
 * Created by Татьяна on 23.09.16.
 */
public class SecondTask {

    public static void generateArray(int[] array, int size, int limit) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(limit + 1);
        }
    }

    public static void sortChose(int[] array, int size) {
        int curIndexMin;
        for (int i = 0; i < size - 1; i++) {
            curIndexMin = i;
            for (int j = i; j < size; j++)
                if (array[j] < array[curIndexMin])
                    curIndexMin = j;
            int temp = array[i];
            array[i] = array[curIndexMin];
            array[curIndexMin] = temp;
        }
    }

    public static void sortInsert(int[] array, int size) {
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++)
                if (array[j] > array[i]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    for (int k = j + 1; k <= i; k++) {
                        int tmp1 = array[k];
                        array[k] = tmp;
                        tmp = tmp1;
                    }
                    break;
                }
        }
    }


    private static boolean isPrime(int number) {
        if (number < 2)
            return false;
        for (int denominator = 2; denominator <= number / 2; denominator++) {
            if (number % denominator == 0)
                return false;
        }
        return true;
    }

    private static boolean isAliquot(int number) {
        if (number % 3 == 0)
            return true;
        return false;
    }

    public static int countPrimes(int[] array, int size) {
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (isPrime(array[i]))
                result++;
        }
        return result;
    }

    public static int countAliquots(int[] array, int size) {
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (isAliquot(array[i]))
                result++;
        }
        return result;
    }
}
