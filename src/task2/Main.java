package task2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static int topBorder;
    private static int countOfThreads;
    protected static Set<Integer> primes = new ConcurrentSkipListSet<>();

    public static void main(String[] args) {
        Main.doPrepareJob();
        ExecutorService service = Executors.newFixedThreadPool(countOfThreads);
//        long start = System.currentTimeMillis();
        for (int i = 0; i < countOfThreads; i++) {
            int startCount = i * topBorder / countOfThreads + 1;
            int finishCount = (i+1)* topBorder / countOfThreads;
            service.submit(new PrimeFinder(startCount, finishCount));
        }
        service.shutdown();
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