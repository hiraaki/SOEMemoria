import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException {
        ArrayList<Trace> list = new ArrayList<>();
//        File file = new File("src/file.txt");

        File file = new File("src/bigone.treace");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                String array[]=text.split(" ");
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

        int tamPagina = scanner.nextInt();
//        int nframe = scanner.nextInt();

        int i = 0;
        HashMap<String, ArrayList<Integer>> hashmap = new HashMap<>();

        for (Trace trace : list) {
            if(trace.getOperation()!= "W") {
                String substring = new String(trace.getMemoryAccess()).substring(0,
                        (int)(Math.log(tamPagina)/Math.log(2))
                );
//                System.out.println(trace.getMemoryAccess());
//                System.out.println(substring);
//                System.out.println((int)(Math.log(tamPagina)/Math.log(2)));
                if (hashmap.containsKey(substring)) {
                    hashmap.get(substring).add(i);
                } else {
                    ArrayList index = new ArrayList();
                    index.add(i);
                    hashmap.put(substring, index);
                }
            }
            i++;
        }
//        int num = (Integer.parseInt("00a02b0", 16));
//        System.out.println(Integer.toBinaryString(num));
//        for (String key : hashmap.keySet()) {
//            System.out.println(key+" "+Arrays.toString(hashmap.get(key).toArray()));
//            //System.out.printf("%x -> %s\n", Integer.parseInt(key, 2), Arrays.toString(hashmap.get(key).toArray()));
//        }
        System.out.println("pronto");
    }
}
