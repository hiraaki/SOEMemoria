import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Otimo {

    ArrayList<Trace> list;
    HashMap<String, ArrayList<Integer>> hashmap;
    int nFalhadePagina;
    int tamPagina;

    public Otimo (String filePath,int tamPagina){
        this.list=new ArrayList<>();
        this.hashmap= new HashMap<>();
        this.tamPagina = tamPagina;
        int linha = 0;

        File file = new File(filePath);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {

                String array[]=text.split(" ");
                Trace trace =new Trace(array[0],array[1],this.tamPagina);
                this.list.add(trace);

                String substring = trace.getPage();

//                System.out.println(trace.getMemoryAccess());
//                System.out.println(substring);
//                System.out.println((int)(Math.log(tamPagina)/Math.log(2)));

                if (hashmap.containsKey(substring)) {
                    hashmap.get(substring).add(linha);
                } else {
//                    System.out.println(new BigInteger(substring, 2).toString(10));
                    ArrayList index = new ArrayList();
                    index.add(linha);
                    hashmap.put(substring, index);
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
        ArrayList<String> framesMemoria = new ArrayList<>();
        for(int i=0; i<this.list.size();i++){
            Trace trace = this.list.get(i);
            String substring = trace.getPage();

//            System.out.println(new BigInteger(trace.getPage(), 2).toString(10));

            if(!framesMemoria.contains(substring)){

                if(framesMemoria.size()<nFrames){
//                    System.out.println("S");
                    framesMemoria.add(substring);
                    hashmap.get(substring).remove(0);
                }else{
                    String selected=new String();
                    int geater=-1;
                    boolean nocall = false;
                    for(String frame:framesMemoria){
//                        System.out.println(hashmap.get(frame).size()+" "+ frame);
                        if(hashmap.get(frame).size()>0){
                            int index = this.hashmap.get(frame).get(0);
                            if(index>geater) {
                                geater = index;
                                selected = frame;
                            }
                        }else{
                            selected = frame;
                            nocall=true;
                            break;
                        }

                    }
//                    System.out.println(new BigInteger(selected, 2).toString(10)+" troca por: "+new BigInteger(substring, 2).toString(10));
                    if(nocall){
                        this.hashmap.remove(selected);
                    }
                    framesMemoria.remove(selected);
                    this.hashmap.get(substring).remove(0);
                    framesMemoria.add(substring);
                    this.nFalhadePagina++;
                }
            }else{
                if(this.hashmap.get(substring).size()>0) {
                    this.hashmap.get(substring).remove(0);
                }else{
                    this.hashmap.remove(substring);
                    framesMemoria.remove(substring);
                }
            }

        }
    }

}
