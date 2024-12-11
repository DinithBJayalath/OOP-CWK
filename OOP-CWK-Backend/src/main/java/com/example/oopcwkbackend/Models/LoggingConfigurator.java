package com.example.oopcwkbackend.Models;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingConfigurator {
    /**
     * LoggingConfigurator class, responsible for configuring the logger
     */

    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("logs.log", false);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger.getLogger("").addHandler(fileHandler);
            Logger.getLogger("").setLevel(Level.INFO);
        } catch (IOException e) {
            System.out.println("Can not find or open log file");
        }
    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }
}
