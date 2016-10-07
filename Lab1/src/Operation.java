package ihnashchanka;

/**
 * Created by Татьяна on 05.09.16.
 */
public class Operation {
    public char operationSign;
    public Operation(char operationSign) {
        this.operationSign = operationSign;
    }
    public short priority() {
        switch (operationSign)
        {
            case '+': case '-':
            return 1;
            case '/': case '*':
            return 2;
            default:
                return 0;
        }
    }
    public double doOperation(double firstArg, double secondArg) throws Exception {
        double result = 0;
        switch(operationSign){
            case '+':
                result = firstArg+ secondArg;
                break;
            case '-':
                result = firstArg - secondArg;
                break;
            case '*':
                result = firstArg * secondArg;
                break;
            case '/':
                if (secondArg == 0)
                    throw new Exception("Деление на 0!");
                result = firstArg / secondArg;
                break;
        }
        return  result;
    }
}
