import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Татьяна on 07.10.2016.
 */
public class Main {
    private static Complex first = new Complex();
    private static Complex second = new Complex();

    public static void main(String[] args) throws NumberFormatException, IOException {
        getNumbers();
        System.out.println("Сумма: " + Complex.sum(first, second));
        System.out.println("Разность: " + Complex.sub(first, second));
        System.out.println("Произведение: " + Complex.multiply(first, second));
        System.out.println("Частное: " + Complex.divide(first, second));
        System.out.println("Введите комплексное число в виде а+bi");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Complex testParse = Complex.parseComplex(in.readLine());
        System.out.println("Полученное число:");
        System.out.println(testParse);
        System.out.println("Его тригонометрическая форма: " + testParse.toTrigString());

    }

    static void getNumbers() {
        System.out.println("Введите действительную и комплексную часть первого числа");
        Scanner scanner = new Scanner(System.in);
        double a1 = nextExplicitDouble(scanner);
        double b1 = nextExplicitDouble(scanner);
        System.out.println("Введите действительную и комплексную часть второго числа");
        double a2 = nextExplicitDouble(scanner);
        double b2 = nextExplicitDouble(scanner);
        first = new Complex(a1, b1);
        second = new Complex(a2, b2);
    }

    static double nextExplicitDouble(Scanner scanner) {
        while (!scanner.hasNextDouble())
            scanner.next();
        return scanner.nextDouble();
    }
}
