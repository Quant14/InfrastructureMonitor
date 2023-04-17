package org.elsys.InfrastructureMonitorServer.Controller;

public class Tokenize {
    public static String[] tokenize(String input, String delimiter) {
        return input.split(delimiter);
    }
}
