package ihnashchanka;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Татьяна on 05.09.16.
 */

public class Calculator {

    public static void main(String[] args) throws IOException, EmptyStackException {
        System.out.println("Введите выражение:");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String inputString = in.readLine();
        try {
            double result = Calculator.getResult(Calculator.getFinishedString(inputString));
            System.out.println("Рузультат: " + result);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static String getFinishedString(String inputString) throws EmptyStackException {
        Stack stack = new Stack();
        String finishedString = new String();
        short curPriority = 0;
        for (int i = 0; i < inputString.length(); i++) {
            char curSign = inputString.charAt(i);
            char nextSign = 0;
            if (i + 1 < inputString.length()) {
                nextSign = inputString.charAt(i + 1);
            }
            switch (Sign.recognizeSign(curSign)) {
                case NUMBER:
                    finishedString += curSign;
                    if (Sign.recognizeSign(nextSign) != Sign.NUMBER)
                        finishedString += " ";
                    break;
                case OPEN_BRACKET:
                    stack.push(curSign);
                    break;
                case OPERATION:
                case CLOSE_BRACKET: {
                    Operation operation = new Operation(curSign);
                    if (curPriority < operation.priority() && Sign.recognizeSign(curSign) != Sign.CLOSE_BRACKET) {
                        stack.push(curSign);
                        curPriority = operation.priority();
                    } else {
                        char topSign = (char) stack.peek();
                        while (!stack.isEmpty() && Sign.recognizeSign(topSign) != Sign.OPEN_BRACKET) {
                            stack.pop();
                            finishedString += topSign + " ";
                            if (!stack.isEmpty()) {
                                topSign = (char) stack.peek();
                            }
                        }
                        if (Sign.recognizeSign(curSign) != Sign.CLOSE_BRACKET) {
                            Operation topOperation;
                            if (!stack.isEmpty()) {
                                topOperation = new Operation((char) stack.peek());
                                stack.pop();
                            } else {
                                topOperation = new Operation(curSign);
                            }
                            curPriority = topOperation.priority();
                            stack.push(curSign);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
        while (!stack.isEmpty()) {
            char topSign = (char) stack.peek();
            stack.pop();
            finishedString += topSign + " ";
        }
        return finishedString;
    }


    public static double getResult(String finishedString) throws Exception {
        double result;
        String bufferString = new String();
        Stack stack = new Stack();
        for (int i = 0; i < finishedString.length(); i++) {
            char curSign = finishedString.charAt(i);
            char nextSign = 0;
            if (i + 1 < finishedString.length()) {
                nextSign = finishedString.charAt(i + 1);
            }
            switch (Sign.recognizeSign(curSign)) {
                case NUMBER:
                    bufferString += curSign;
                    if (Sign.recognizeSign(nextSign) != Sign.NUMBER) {
                        stack.push(Double.parseDouble(bufferString));
                        bufferString = "";
                    }
                    break;
                case OPERATION:
                    double secondAgr = (double) stack.peek();
                    stack.pop();
                    double firstAgr = (double) stack.peek();
                    stack.pop();
                    Operation operation = new Operation(curSign);
                    result = operation.doOperation(firstAgr, secondAgr);
                    stack.push(result);
                    break;
                default:
                    break;
            }

        }
        result = (double) stack.peek();
        return result;
    }


}
