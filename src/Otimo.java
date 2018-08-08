import java.io.*;
import java.math.BigInteger;
import java.util.*;

/*
* Classe que possui os métodos do algoritimo ótimo para tratamento de falha de pagina
* */
public class Otimo {
    //Lista de traces do arquivo
    ArrayList<Trace> list;
    //HashMap para aumentar a velocidade de busca das futuras repetições
    HashMap<String, ArrayList<Integer>> hashmap;
    //Variavel de incremento responsavel por guardar o numero de falha de pagina
    int nFalhadePagina;
    //Variavel que possui o tamanho da pagina em entradas
    int tamPagina;

    /*
    *   Constroi a classe populando o hashmap e a lista de traces.
    */
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

            //verifica se a pagina está na memória
            if(!framesMemoria.contains(substring)){
                //verifica se ainda há frames livres na memória
                if(framesMemoria.size()<nFrames){
                    //se possui é adicionado
                    framesMemoria.add(substring);
                    hashmap.get(substring).remove(0);
                //Se não á espaços livres é realizado uma busca
                // para ver qual é o freme que vai demorar mais para ser utilizado
                }else{
                    //Variavel que guarda a pagina a que vai ser retirada da memória
                    String selected=new String();
                    //Index da proxima vez que a pagina será chamada
                    int geater=-1;
                    //Variavel que verifica se uma página não será chamada novamente
                    boolean nocall = false;

                    for(String frame:framesMemoria){
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
