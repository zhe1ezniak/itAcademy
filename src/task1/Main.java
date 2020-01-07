package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main {
    private static int topBorder;
    private static int botBorder;
    private static int countOfThreads;
    private static boolean addToCommonSet;
    protected static Set<Integer> primes = new ConcurrentSkipListSet<>();

    public static void main(String[] args) {
        Main.doPrepareJob();
        Thread t = null;
        List<PrimeFinder> pfList = new ArrayList<>();
//        long start = System.currentTimeMillis();
        for (int i = 0; i < countOfThreads; i++) {
            int startCount;
            if (i == 0){
                startCount = botBorder;
            } else {
                startCount = i * topBorder / countOfThreads + 1;
            }
            int finishCount = (i + 1) * topBorder / countOfThreads;
            PrimeFinder pf = new PrimeFinder(startCount, finishCount, addToCommonSet);
            pfList.add(pf);
            t = new Thread(pf);
            t.start();
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("Join exception");
        }
        if(!addToCommonSet) {
            for (PrimeFinder p : pfList) {
                primes.addAll(p.getNumbers());
            }
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
            System.out.println("Enter number of bottom border: ");
            botBorder = Integer.parseInt(reader.readLine());
            System.out.println("Enter count of threads: ");
            countOfThreads = Integer.parseInt(reader.readLine());
            try {
                System.out.println("Enter 'true' if want to add to common set or 'false' - to separate set: ");
                addToCommonSet = Boolean.parseBoolean(reader.readLine());
                } catch (IOException e){
                System.out.println("Please enter correct word(true/false");
                doPrepareJob();
            }
        } catch (IOException e) {
            System.out.println("It's not integer. Please try again");
            doPrepareJob();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Close reader exception");
            }
        }
    }
}
