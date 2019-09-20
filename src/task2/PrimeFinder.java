package task2;

import java.util.HashSet;
import java.util.Set;

public class PrimeFinder implements Runnable {
    private int startCount;
    private int finishCount;
    private boolean addToCommonSet;
    private volatile Set<Integer> numbers;

    public PrimeFinder(int start, int finish, boolean addToCommonSet) {
        this.startCount = start;
        this.finishCount = finish;
        this.addToCommonSet = addToCommonSet;
        numbers = new HashSet<>();
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public void run() {
        if (startCount == 1 || startCount == 0) {
            startCount = 2;
        }
        for (int i = startCount; i <= finishCount; i++){
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i%j == 0){
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                if (addToCommonSet) {
                    Main.primes.add(i);
                } else {
                    numbers.add(i);
                }
            }
        }
    }
}

