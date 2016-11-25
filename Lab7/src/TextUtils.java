import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Tatsiana on 25.11.2016.
 */
public class TextUtils {
    private static ArrayList<String> noNumbers = new ArrayList<String>();
    private static ArrayList<String> numbersOnly = new ArrayList<String>();
    public static void main(String[] args) {
        try {
            getData();
            sortLists();
            System.out.println("Кол-во слов без цифр: " + noNumbers.size());
            System.out.println("Кол-во слов-чисел: " + numbersOnly.size());
            print();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void getData() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        sc.useDelimiter("[\\.\\,\\s]+");
        while(sc.hasNext()){
            String token = sc.next();
            if(token.matches("\\d+"))
                numbersOnly.add(token);
             else noNumbers.add(token);
        }
    }

    private static void sortLists(){
        Collections.sort(noNumbers, (s1, s2)->s1.compareToIgnoreCase(s2));
        Collections.sort(noNumbers, (s1, s2)->s1.compareToIgnoreCase(s2));
    }
    private static void print(){
        try {
            FileWriter fw = new FileWriter("output1.txt");
            for(String item: numbersOnly){
                fw.write(item + " ");
            }
            fw.close();
            fw = new FileWriter("output2.txt");
            for(String item: noNumbers){
                fw.write(item + " ");
            }
            fw.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
