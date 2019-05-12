package io.ashimjk.prime.generator.refactor;

class NumberPrinter {

    private int linesPerPage;
    private int columns;

    NumberPrinter(int linesPerPage, int columns) {
        this.linesPerPage = linesPerPage;
        this.columns = columns;
    }

    void print(int[] numbers, int numberOfPrimes) {
        int pagenumber = 1;
        int pageoffset = 1;

        while (pageoffset <= numberOfPrimes) {

            printStartingInfo(numberOfPrimes, pagenumber);

            printAllRows(numbers, numberOfPrimes, pageoffset);

            System.out.println("\f");

            pagenumber++;
            pageoffset += linesPerPage * columns;
        }
    }

    private void printStartingInfo(int numberOfPrimes, int pagenumber) {
        System.out.print("The First ");
        System.out.print(numberOfPrimes);
        System.out.print(" Prime Numbers --- Page ");
        System.out.print(pagenumber);
        System.out.print("\n");
    }

    private void printAllRows(int[] numbers, int numberOfPrimes, int pageoffset) {
        for (int rowoffset = pageoffset; rowoffset <= pageoffset + linesPerPage - 1; rowoffset++) {
            printAllColumns(numbers, numberOfPrimes, rowoffset);
            System.out.println();
        }
    }

    private void printAllColumns(int[] numbers, int numberOfPrimes, int rowoffset) {
        for (int column = 0; column <= columns - 1; column++)
            if (rowoffset + column * linesPerPage <= numberOfPrimes)
                System.out.printf("%10d", numbers[rowoffset + column * linesPerPage]);
    }

}
