package io.ashimjk.prime.generator.refactor;

class PrimeGenerator {

    private int ordinalMax = 30;
    private int[] primes;
    private int candidate;
    private int ord;
    private int square;
    private int n;
    private int[] multiples = new int[ordinalMax + 1];

    PrimeGenerator() {
        candidate = 1;
        ord = 2;
        square = 9;
        n = 0;
    }

    int[] generatePrimes(int numberOfPrimes) {
        int primeIndex = 1;
        primes = new int[numberOfPrimes + 1];
        primes[1] = 2;

        while (primeIndex < numberOfPrimes) {
            findNextPrime();
            primeIndex++;
            primes[primeIndex] = candidate;
        }

        return primes;
    }

    private void findNextPrime() {
        boolean possiblyPrime;
        do {
            candidate += 2;
            if (candidate == square) {
                ord++;
                square = primes[ord] * primes[ord];
                multiples[ord - 1] = candidate;
            }
            n = 2;
            possiblyPrime = true;
            while (n < ord && possiblyPrime) {
                while (multiples[n] < candidate)
                    multiples[n] += primes[n] + primes[n];
                if (multiples[n] == candidate)
                    possiblyPrime = false;
                n++;
            }
        } while (!possiblyPrime);
    }

}
