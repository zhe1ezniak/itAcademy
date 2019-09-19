package task3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                System.out.println("Enter file name or END");
                String fileName = reader.readLine();
                if (fileName.equals("END")) {
                    break;
                }

                try {
                    SequenceFinder finder = new SequenceFinder(fileName);
                    Future<Integer> task = service.submit(finder);

                    int longestSequenceBytes = task.get();

                    System.out.println("Number of longest bytes sequence: " + longestSequenceBytes);
                    System.out.println("Number of first input sequence: " + finder.getFirstIndex());
                    System.out.println("Number of second input sequence: " + finder.getSecondIndex());
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Exception during future.get()");
                }
            }
        } catch (IOException e) {
            System.out.println("Read file's name exception");
        } finally {
            service.shutdown();
        }
    }
}
