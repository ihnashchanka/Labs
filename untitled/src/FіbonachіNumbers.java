package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Татьяна on 24.09.16.
 */
public class FіbonachіNumbers {
    private static int[] fibonachiArray;
    private static int amount;
    public static void main(String[] args) throws IOException{
        getData();
        getSequence();
        System.out.println("Полученная последовательность:");
        for(int item:fibonachiArray)
            System.out.print(item+" ");
    }
    private static void  getData() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество чисел в последовательности");
        amount = Integer.parseInt(in.readLine());
        fibonachiArray = new int[amount];
        System.out.println("Введите x1:");
        fibonachiArray[0] = Integer.parseInt(in.readLine());
        System.out.println("Введите x2:");
        fibonachiArray[1] = Integer.parseInt(in.readLine());
    }
    private static void getSequence(){
        for(int i = 2 ; i< amount; i++){
            fibonachiArray[i] = fibonachiArray[i-1] + fibonachiArray[i-2];
        }
    }
}
