package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by Татьяна on 17.09.16.
 */
public class MyClass {
    final static int AMOUNT = 3;
    final static int ARRAY_SIZE = 15;
    private static int[] numberArray = new int[AMOUNT];
    private static int[] myArray = new int[ARRAY_SIZE];

    public static void main(String[] args) throws IOException {
        try {
            getData();
            System.out.println("Выделенные числа:");
            for (int i = 0; i < AMOUNT; i++)
                System.out.print(numberArray[i] + " ");
            System.out.println();
            System.out.println("Среднее арифметическое:");
            System.out.println(getAverage(numberArray, AMOUNT));
            System.out.println("Максимальное из введенных чисел:");
            System.out.println(findMax(numberArray, AMOUNT));
            System.out.println("Минимальное из введенных чисел:");
            System.out.println(findMin(numberArray, AMOUNT));

            System.out.println("Сгенерированный массив:");
            SecondTask.generateArray(myArray, ARRAY_SIZE, findMin(numberArray, AMOUNT));
            for (int i = 0; i < ARRAY_SIZE; i++) {
                System.out.print(myArray[i] + " ");
            }
            System.out.println();
            System.out.println("Отсортированный массив:");
            SecondTask.sortInsert(myArray, ARRAY_SIZE); // sortChose(myArray, ARRAY_SIZE);
            for (int i = 0; i < ARRAY_SIZE; i++)
                System.out.print(myArray[i] + " ");

            System.out.println();
            System.out.println("Количество простых: " );
            System.out.println(SecondTask.countPrimes(myArray, ARRAY_SIZE));
            System.out.println("Количество кратных трем:");
            System.out.println(SecondTask.countAliquots(myArray, ARRAY_SIZE));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getData() throws IOException, Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите строку:");
        parseSting(in.readLine());
    }

    private static void parseSting(String inString) throws Exception {
        int number = 0;

        boolean flag;
        for (int i = 0; i < inString.length() && number < AMOUNT; i++) {
            boolean isBinary = false;
            flag = true;
            char curSign = inString.charAt(i);
            String bufferString = new String();

            if (curSign == '[') {
                isBinary = true;
                i++;
                curSign = inString.charAt(i);
                while (curSign == '1' || curSign == '0') {

                    bufferString += curSign;
                    i++;
                    curSign = inString.charAt(i);
                }
                if (curSign != ']') {
                    isBinary = false;
                }
            }
            while ((curSign <= '9' && curSign >= '0') || curSign == '.') {
                bufferString += curSign;
                if (curSign == '.') {
                    flag = false;
                }
                i++;
                if (i < inString.length()) curSign = inString.charAt(i);
                else break;
            }
            if (flag && !bufferString.equals("")) {
                if (isBinary) {
                    numberArray[number] = convertByteToInt(bufferString);
                } else {
                    numberArray[number] = Integer.parseInt(bufferString);
                }
                number++;
            }
        }
        if (number < AMOUNT) throw new Exception("Введено недостаточно целых чисел");
    }

    private static double getAverage(int[] array, int size) {
        double result = 0;
        for (int i = 0; i < size; i++)
            result += array[i];
        result /= size;
        return result;
    }

    private static int findMax(int[] array, int size) {
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max)
                max = array[i];
        }
        return max;
    }

    private static int findMin(int[] array, int size) {
        int min = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] < min)
                min = array[i];
        }
        return min;
    }

    private static int convertByteToInt(String inSting) {
        int result = 0;
        int length = inSting.length();
        for (int i = length - 1; i >= 0; i--) {
            if (inSting.charAt(i) == '1') {
                result += (1 << (length - 1 - i));
            }
        }
        return result;
    }


}