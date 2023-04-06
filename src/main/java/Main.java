import java.nio.charset.Charset;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int a = 100;
        int b = 500;

        String d = "100";
        String e = "500";


        int g = Integer.parseInt(d) + Integer.parseInt(e);
        String c = "" +  a+b;
        System.out.println(g);

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
    }
}