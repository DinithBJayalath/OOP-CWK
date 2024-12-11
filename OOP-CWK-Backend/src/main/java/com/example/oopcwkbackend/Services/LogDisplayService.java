package com.example.oopcwkbackend.Services;

import com.example.oopcwkbackend.Models.LoggingConfigurator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class LogDisplayService {
    /**
     * The LogDisplayService class is responsible for
     * opening the log file and adding the logs to an ArrayList
     * and returning the ArrayList to the controller.
     */

    private long lastReadPosition = 0;
    private static Logger logger = Logger.getLogger(LogDisplayService.class.getName());
    static {
        logger.addHandler(LoggingConfigurator.getFileHandler());
    }

    public ArrayList<String> returnLogs() {
        /**
         * This method reads the logs from the log file and adds them to an ArrayList.
         * @return ArrayList<String> logs
         */
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
            logger.warning("Can not find or open log file");
        }
        return logs;
    }
}
