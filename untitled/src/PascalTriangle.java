package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Татьяна on 24.09.16.
 */
public class PascalTriangle  {
    private static int size;
    private static int[][] triangle;
    public static void main(String[] args) throws IOException {
        getData();
        getTriangle();
        printTriangle(triangle, size);
    }
    private static void  getData() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество строк треугольника");
        size = Integer.parseInt(in.readLine());
        triangle = new int[size][size];

    }
    private static void getTriangle(){
        for(int i = 0; i<= size-1; i++) {
            triangle[i][0] = triangle[i][i] = 1;
        }
        for(int i =1; i <size; i++)
            for(int j = 1 ; j < i +1; j++){
            triangle[i][j] = triangle[i-1][j-1]+triangle[i-1][j];
            }
    }

    private static void printTriangle(int[][] matrix, int strings){
        System.out.println("Треугольник Паскаля");
        for(int i =0; i< strings; i++) {
            for (int j = 0; j < i+1; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }

    }

}
