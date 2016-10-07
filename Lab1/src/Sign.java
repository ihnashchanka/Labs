package ihnashchanka;

/**
 * Created by Татьяна on 05.09.16.
 */

import ihnashchanka.Calculator;

public enum Sign {
    OPERATION,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    NUMBER;

    public final static String OPERATIONS = new String("+-/*");

    public static Sign recognizeSign(char sign) {
        if (OPERATIONS.indexOf(sign) != -1)
            return OPERATION;
        if ('(' == sign)
            return OPEN_BRACKET;
        if (')' == sign)
            return CLOSE_BRACKET;
        if ('0' <= sign && sign <= '9' || sign == '.')
            return NUMBER;
        return null;
    }
}
