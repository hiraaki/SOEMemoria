import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        ArrayList<Trace> list = new ArrayList<>();
        File file = new File("src/file.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                String array[]=text.split(" ");
                System.out.println(array[0]+array[1]);
                list.add(new Trace(array[0],array[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }


    }
}
