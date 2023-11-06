import java.util.Scanner;

public class ThreadedPrimes {
    public static int nextNumber;
    public static int[] primes = new int[100000];
    public static int primeCount;
    public static int max = 1000000;

    // ***** Note:  There is a subtle bug in the Worker class
    // *****        The number of primes found can be off by the number of threads

    public static void main(String[] args) throws InterruptedException {
        Worker[] thread = new Worker[4];
        int i;
        long runTime;
        runTime = System.currentTimeMillis();
        primes[0] = 2;
        primes[1] = 3;
        primeCount = 2;
        nextNumber = 5;

        for (i=0; i<thread.length; i++) {
            thread[i] = new Worker(i);
            thread[i].start();
        }

        for (i=0; i<thread.length; i++) {
            thread[i].join();
        }

        runTime = System.currentTimeMillis()-runTime;
        System.out.println("Prime count: "+primeCount);
        System.out.println("Run time: "+runTime);

        Scanner scanMeDaddy = new Scanner(System.in);
        System.out.println();
        long n; int valerie;
        while (true) {
            System.out.println("Enter a number: ");
            n = scanMeDaddy.nextLong();
            if(n<=0) break;
            valerie = binarySearch(n, primes, 0, primeCount-1);
            System.out.println(valerie);

        }

    }

    private static int binarySearch (long val, int[] primeList, int start, int end){
        try {
            if (end <= start) return -1;
            int halfway = (start + end) / 2;
            if (primeList[halfway] == val) return halfway;
            if (val > primeList[halfway]) {
                return binarySearch(val, primeList, halfway, end);
            }
            if (val < primeList[halfway]) {
                return binarySearch(val, primeList, start, halfway);
            }
            return 0;
        }
        catch (StackOverflowError e){
            return -1;
        }
    }

}