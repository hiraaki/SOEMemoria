import java.io.*;
import java.util.*;

public class Otimo {

    ArrayList<Trace> list;
    HashMap<String, ArrayList<Integer>> hashmap;
    int nFalhadePagina;
    int tamPagina;

    public Otimo (String filePath,int tamPagina){
        this.list=new ArrayList<>();
        this.hashmap= new HashMap<>();
        int linha = 0;

        File file = new File(filePath);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {

                String array[]=text.split(" ");
                Trace trace =new Trace(array[0],array[1]);
                this.list.add(trace);


                if(trace.getOperation()!= "W") {
                    String substring = new String(trace.getMemoryAccess()).substring(0,
                            (int)(Math.log(tamPagina)/Math.log(2))
                    );

//                System.out.println(trace.getMemoryAccess());
//                System.out.println(substring);
//                System.out.println((int)(Math.log(tamPagina)/Math.log(2)));

                    if (hashmap.containsKey(substring)) {
                        hashmap.get(substring).add(linha);
                    } else {
                        ArrayList index = new ArrayList();
                        index.add(linha);
                        hashmap.put(substring, index);
                    }
                }
                linha++;
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
//        int num = (Integer.parseInt("00a02b0", 16));
//        System.out.println(Integer.toBinaryString(num));
//        for (String key : hashmap.keySet()) {
//            System.out.println(key+" "+Arrays.toString(hashmap.get(key).toArray()));
//            //System.out.printf("%x -> %s\n", Integer.parseInt(key, 2), Arrays.toString(hashmap.get(key).toArray()));
//        }

        System.out.println("Carregou");
    }

    public void executar(int nFrames){
        ArrayList<String> framesMemória = new ArrayList<>();
        for(int i=0; i>this.list.size();i++){
            Trace trace = this.list.get(i);
            String substring = new String(trace.getMemoryAccess()).substring(0,
                    (int)(Math.log(tamPagina)/Math.log(2))
            );
            if(!framesMemória.contains(substring)){
                if(framesMemória.size()<nFrames-1){
                    framesMemória.add(substring);
                }else{
                    String selected;
                    for(String frame:framesMemória){

//                        if(hashmap.get(frame).listIterator(0)>)
                    }
                }
            }

        }
    }




}
