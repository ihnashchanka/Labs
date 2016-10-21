import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Татьяна on 14.10.2016.
 */
public class RegEx {
    private static String inString = new String();

    public static void main(String[] args) throws IOException {

        boolean flag = true;
        do {
            System.out.println("Введите номер задания(1-8), для тестирования(9 для завершения)");
            getData();
            String menu = new String(inString);
            if(menu.equals("9")){
                flag = false;
                break;
            }
            System.out.println("Введите строку для проверки:");
            getData();
            switch (menu) {
                case "1":
                    System.out.println(italics());
                    break;
                case "2":
                    System.out.println((isTime() ? "Является " : "Не является ") + "корректным заданием времени");
                    break;
                case "3":
                    System.out.println((isGUID() ? "Является " : "Не является ") + "корректным заданием GUID");
                    break;
                case "4":
                    System.out.println("Найденный IP: " + getIPv4());
                    break;
                case "5":
                    System.out.println((isURL() ? "Является " : "Не является ") + "корректным заданием URL");
                    break;
                case "6":
                    System.out.println((isDate() ? "Является " : "Не является ") + "корректным заданием даты");
                    break;
                case "7":
                    System.out.println((isColor() ? "Является " : "Не является ") + "корректным заданием даты");
                    break;
                case "8":
                    tokens();
                    break;
                case "9":
                    break;
                default:
                    System.out.println("Не верный ввод!!!");
            }
        }
        while(flag);
    }

    public static void getData() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        inString = in.readLine();
    }

    public static String italics() {
        String result = new String();
        int lastPoint = 0;
        Pattern p = Pattern.compile("\\*{1,2}([^\\*]+)\\*{1,2}");
        Matcher m = p.matcher(inString);
        while (m.find()) {
            result += inString.substring(lastPoint, m.start());
            if (m.group().charAt(1) != '*') {
                result += "<em>";
                result += m.group().substring(1, m.group().length() - 1);
                result += "</em>";
            } else {
                result += m.group();
            }
            lastPoint = m.end() + 1;
        }
        return result;
    }

    public static boolean isTime() {
        Pattern p = Pattern.compile("^(([01][0-9])|(2[0-3])):[0-5][0-9]$");
        Matcher m = p.matcher(inString);
        return m.matches();
    }

    public static boolean isGUID() {
        Pattern p = Pattern.compile
                ("^(\\{([0-9A-Fa-f]{8}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{12})\\})" +
                        "|([0-9A-Fa-f]{8}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{12})$");
        Matcher m = p.matcher(inString);
        return m.matches();
    }

    public static String getIPv4() {
        Pattern p = Pattern.compile
                ("(((([0-9])|([1-9][0-9])|(1\\d\\d)|(2(([0-4][0-9])|(5[0-5]))))|(0x([0-9a-fA-F][0-9a-fA-F]))|(0(([0-7]{1,2})|([0-3][0-7][0-7]))))\\." +
                        "((([1-9])|([1-9][0-9])|(1\\d\\d)|(2(([0-4][0-9])|(5[0-5]))))|(0x([0-9a-fA-F][0-9a-fA-F]))|(0(([0-7]{1,2})|([0-3][0-7][0-7]))))\\." +
                        "((([1-9])|([1-9][0-9])|(1\\d\\d)|(2(([0-4][0-9])|(5[0-5]))))|(0x([0-9a-fA-F][0-9a-fA-F]))|(0(([0-7]{1,2})|([0-3][0-7][0-7]))))\\." +
                        "((([1-9])|([1-9][0-9])|(1\\d\\d)|(2(([0-4][0-9])|(5[0-5]))))|(0x([0-9a-fA-F][0-9a-fA-F]))|(0(([0-7]{1,2})|([0-3][0-7][0-7])))))|" +
                        "((([1-9])|([1-9][0-9])|(1\\d\\d)|(2(([0-4][0-9])|(5[0-5]))))|(0x([0-9a-fA-F][0-9a-fA-F]))|(0(([0-7]{1,2})|([0-3][0-7][0-7])))){4}");
        Matcher m = p.matcher(inString);
        if (m.find())
            return m.group();
        return null;
    }

    public static boolean isURL() {
        Pattern p = Pattern.compile("^((htttp)|(https)://)?(www\\.)?[^\\-][\\d\\w\\-]+(.\\w+)+(\\/\\S+)*(\\#[\\w\\d])*");
        Matcher m = p.matcher(inString);
        return m.matches();
    }

    public static boolean isDate() {
        Pattern p = Pattern.compile("(((([0-2][0-9])|(3[01]))/((0[1357])|(1[02])))|((([0-2][0-9])|(30))/((0[469])|(11)))|" +
                "([0-2][0-9]/02))/(((16)|([2-9][0-9]))[0-9][0-9])");
        Matcher m = p.matcher(inString);
        return m.matches();
    }

    public static boolean isColor() {
        Pattern p = Pattern.compile("^\\#[A-F0-9a-f]{6}$");
        Matcher m = p.matcher(inString);
        return m.matches();
    }

    public static void tokens() {
        Pattern word = Pattern.compile("(\\w+'\\w*)|(\\w+\\-\\w+)|(\\w+)");
        Pattern quote = Pattern.compile("\".[^\"]+\"");
        Matcher mWord = word.matcher(inString);
        Matcher mQuote = quote.matcher(inString);
        while (mWord.find() || mQuote.find()) {
            int start1 = mWord.start();
            if (mQuote.find()) {
                int start2 = mQuote.start();
                if (start2 <= start1) {
                    System.out.println(mQuote.group());
                    inString = inString.substring(mQuote.end());
                    mWord = word.matcher(inString);
                    mQuote = quote.matcher(inString);
                } else {
                    System.out.println(mWord.group());
                    inString = inString.substring(mWord.end());
                    mQuote = quote.matcher(inString);
                    mWord = word.matcher(inString);
                }
            } else {
                System.out.println(mWord.group());
                inString = inString.substring(mWord.end());
                mQuote = quote.matcher(inString);
                mWord = word.matcher(inString);
            }
        }
    }
}
