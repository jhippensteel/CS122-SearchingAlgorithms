public class Worker extends Thread{
    private int id;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public Worker(int i) {
        id = i;
    }

    public void run() {
        int n;
        while (ThreadedPrimes.nextNumber<ThreadedPrimes.max) {
            synchronized (lock1) {
                n = ThreadedPrimes.nextNumber;
                ThreadedPrimes.nextNumber += 2;
            }
            if (isPrime(n)) {
                synchronized (lock2) {
                    ThreadedPrimes.primes[ThreadedPrimes.primeCount++] = n;
                }
            }
        }

    }

    public boolean isPrime(int n) {
        int i;
        for (i=1; ThreadedPrimes.primes[i]<=Math.sqrt(n); i++)
            if (n%ThreadedPrimes.primes[i]==0) return false;
        return true;
    }
}

