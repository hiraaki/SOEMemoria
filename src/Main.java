import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException {

        /**
         * população das listas e mapeamento;
         */
        long startTime = System.nanoTime();
        Otimo otimo = new Otimo("src/bigone.trace",1024);
        Fifo fifo = new Fifo(otimo.list);
//        Otimo otimo = new Otimo("src/gcc.trace",1024);
//        Otimo otimo = new Otimo("src/file.txt",10240000);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Tempo de Load:"+duration);

        /**
         * Execução do algoritmo fifo
         */

        startTime = System.nanoTime();
        fifo.executar(1024);
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Tempo de execução FIFO:"+duration);
        System.out.println("Quantidade de falhas de página FIFO:"+fifo.nFalhadePagina);

        /**
         * Execução do algoritmo ótimo
         */

        startTime = System.nanoTime();
        otimo.executar(1024);
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Tempo de execução Ótimo:"+duration);
        System.out.println("Quantidade de falhas de página Ótimo:"+otimo.nFalhadePagina);



    }
}
