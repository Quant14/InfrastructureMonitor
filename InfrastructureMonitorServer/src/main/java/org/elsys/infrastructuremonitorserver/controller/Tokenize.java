package org.elsys.infrastructuremonitorserver.controller;

public class Tokenize {
    public static String[] tokenize(String input, String delimiter) {
        return input.split(delimiter);
    }
}
