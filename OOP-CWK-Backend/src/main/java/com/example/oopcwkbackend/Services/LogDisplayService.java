package com.example.oopcwkbackend.Services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

@Service
public class LogDisplayService {

    private long lastReadPosition = 0;

    public ArrayList<String> returnLogs() {
        System.out.println("Returning logs");
        ArrayList<String> logs = new ArrayList<String>();
        try {
            File logFile = new File("logs.log");
            RandomAccessFile randomAccessFile = new RandomAccessFile(logFile, "r");
            if (logFile.length() < lastReadPosition) {
                lastReadPosition = 0;
            }
            randomAccessFile.seek(lastReadPosition);
            String line;
            while ((line = randomAccessFile.readLine()) != null) {
                if (line.contains(":")) {
                    line += "\n\n";
                }
                logs.add(line);
            }
            lastReadPosition = randomAccessFile.getFilePointer();
        } catch (IOException e) {
            System.out.println("Can not find or open log file");
        }
        return logs;
    }
}
