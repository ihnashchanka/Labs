import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Татьяна on 07.10.2016.
 */
public class Complex {
    private double re;
    private double im;

    public Complex() {
        re = im = 0;
    }

    public Complex(double re) {
        this.re = re;
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex(Complex copy) {
        this.re = copy.re;
        this.im = copy.im;
    }

    public double getReal() {
        return re;
    }

    public double getImaginary() {
        return im;
    }

    public Complex sum(Complex first) {
        this.re += first.re;
        this.im += first.im;
        return this;
    }

    public static Complex sum(Complex first, Complex second) {
        Complex result = new Complex(first);
        return result.sum(second);
    }

    public Complex sub(Complex first) {
        this.re -= first.re;
        this.im -= first.im;
        return this;
    }

    public static Complex sub(Complex first, Complex second) {
        Complex result = new Complex(first);
        return result.sub(second);
    }

    public Complex multiply(Complex second) {
        this.re = this.re * second.re - this.im * second.im;
        this.im = this.re * second.im + this.im * second.re;
        return this;
    }

    public static Complex multiply(Complex first, Complex second) {
        Complex result = new Complex(first);
        return result.multiply(second);
    }

    public Complex divide(Complex second) {
        double c = second.re;
        double d = second.im;
        this.re = (re * c + im * d) / (c * c + d * d);
        this.im = (c * im + d * re) / (c * c + d * d);
        return this;
    }

    public static Complex divide(Complex first, Complex second) {
        Complex result = new Complex(first);
        return result.divide(second);
    }

    @Override
    public String toString() {
        return String.format("%.3f %s %.3fi", re, im >= 0 ? "+" : "-", Math.abs(im));
    }

    public String toTrigString() {
        double mod = Math.sqrt(re * re + im * im);
        double angle = Math.atan(im / re) / Math.PI;
        return String.format("%.3f(cos(%.3fpi) + i*sin(%.3fpi))", mod, angle, angle);
    }

    public static Complex parseComplex(String input) throws NumberFormatException {
        Complex result = new Complex();
        Pattern p = Pattern.compile("-? *\\d+\\.?+\\d*+ *[-+] *(\\d+\\.?+\\d*)i");
        if (!input.matches(p.pattern()))
            throw new NumberFormatException("Uncompetiable string (parseComplex)");
        p = Pattern.compile("-?\\d+(\\.+\\d+)?");
        Matcher m = p.matcher(input);
        if (m.find()) {
            result.re = Double.parseDouble(m.group());
            if (m.find())
                result.im = Double.parseDouble(m.group());
        }

        return result;
    }
}
