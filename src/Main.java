import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("A".getBytes("UTF-16").length); //>> 4
        System.out.println("A".getBytes("UTF-16BE").length); //>> 2
        System.out.println("AA".getBytes("UTF-16").length); //>> 6
        System.out.println(Arrays.toString("A".getBytes("UTF-16")));
        System.out.println(Arrays.toString("A".getBytes("UTF-16BE")));
        System.out.println(Arrays.toString("AA".getBytes("UTF-16")));
        System.out.println(Arrays.toString("AA".getBytes("UTF-16LE")));

        System.out.println(Arrays.toString("A".getBytes("UTF-32")));
        System.out.println(Arrays.toString("AA".getBytes("UTF-32")));

        System.out.println(Arrays.toString("A".getBytes("UTF-8")));
        System.out.println(Arrays.toString("AA".getBytes("UTF-8")));

        System.out.println(Arrays.toString("ЭЮЯ".getBytes("UTF-8")));
        System.out.println("ЭЮЯ".getBytes("UTF-8").length);


        char ch0 = 55378;
        char ch1 = 56816;
        String str = new String(new char[]{ch0, ch1});
        System.out.println(str);
        System.out.println(str.length());
        System.out.println(str.codePointCount(0,str.length()-1));
        System.out.println(str.charAt(0));
        System.out.println(str.charAt(1));


    }
}
