package task1;

public class PrimeFinder implements Runnable {
    private int startCount;
    private int finishCount;

    public PrimeFinder(int start, int finish) {
        this.startCount = start;
        this.finishCount = finish;
    }

    @Override
    public void run() {
        if (startCount == 1) {
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
                Main.primes.add(i);
            }
        }
    }
}
