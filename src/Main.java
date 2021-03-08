import java.io.*;

public class Main {
    public static void main(String[] args) {
        var properties = new Properties();

        /*
        try {
            properties.putValue("class", "com.fr.Student");
            properties.putValue("class", "com.fr.Hello");
            properties.write(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("setting.prop"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        try {
            properties.load(new BufferedReader(new InputStreamReader(new FileInputStream("setting.prop"))));
            var lists = properties.getValue("class");
            for (var x: lists) {
                System.out.println(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}