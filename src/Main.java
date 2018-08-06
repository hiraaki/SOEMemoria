import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException {
        long startTime = System.nanoTime();
        Otimo otimo = new Otimo("src/bigone.trace",1024);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Tempo de Load:"+duration);

    }
}
