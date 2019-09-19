package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main {
    private static int topBorder;
    private static int countOfThreads;
    protected static Set<Integer> primes = new ConcurrentSkipListSet<>();

    public static void main(String[] args) {
        Main.doPrepareJob();
        Thread t = null;
//        long start = System.currentTimeMillis();
        for (int i = 0; i < countOfThreads; i++) {
            int startCount = i * topBorder / countOfThreads + 1;
            int finishCount = (i+1)* topBorder / countOfThreads;
            t = new Thread(new PrimeFinder(startCount, finishCount));
            t.start();
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("Join exception");
        }
//        long finish = System.currentTimeMillis();
//        long time = finish - start;
//        System.out.println("Time : " + time);
        System.out.println("Primes : " + primes.toString());
    }

    private static void doPrepareJob () {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Enter number of top border: ");
            topBorder = Integer.parseInt(reader.readLine());
            System.out.println("Enter count of threads");
            countOfThreads = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Read numbers exception");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Close reader exception");
            }
        }
    }
}
