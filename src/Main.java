import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Escolha seu arquivo:" +
                "\n\t1) bigone.trace" +
                "\n\t2) bzip.trace" +
                "\n\t3) gcc.trace" +
                "\n\t4) sixpack.trace" +
                "\n\t5) swim.trace" +
                "\nOpção: ");
        int choice = scanner.nextInt();
        String path = new String();
        if(choice==1){
            path = "src/bigone.trace";
        }else if(choice==2){
            path = "src/bzip.trace";
        }else if(choice==3){
            path = "src/gcc.trace";
        }else if(choice==4){
            path = "src/sixpack.trace";
        }else if(choice==5){
            path = "src/swim.trace";
        }
        System.out.print("Digite o Tamanho da Página: ");
        int tamanhoPagina = scanner.nextInt();
        System.out.print("Digite o Número de Frames: ");
        int nFrames = scanner.nextInt();
        /**
         * população das listas e mapeamento;
         */
        long startTime = System.nanoTime();
        Otimo otimo = new Otimo(path,tamanhoPagina);
        Fifo fifo = new Fifo(otimo.list);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Tempo de Load:"+duration);

        /**
         * Execução do algoritmo fifo
         */

        startTime = System.nanoTime();
        fifo.executar(nFrames);
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Tempo de execução FIFO:"+duration);
        System.out.println("Quantidade de falhas de página FIFO:"+fifo.nFalhadePagina);

        /**
         * Execução do algoritmo ótimo
         */

        startTime = System.nanoTime();
        otimo.executar(nFrames);
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Tempo de execução Ótimo:"+duration);
        System.out.println("Quantidade de falhas de página Ótimo:"+otimo.nFalhadePagina);



    }
}
