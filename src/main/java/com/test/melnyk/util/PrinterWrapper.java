package com.test.melnyk.util;

import java.io.PrintStream;

public class PrinterWrapper {

    private PrintStream printStream;

    public PrinterWrapper(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printOut(String s) {
        printStream.println(s);
    }
}


