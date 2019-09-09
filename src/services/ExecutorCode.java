package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecutorCode {

    public void exec() throws IOException {
        ProcessBuilder pb =
                new ProcessBuilder("python", "1.py");
        pb.directory(new File("solution"));
        Process process =  pb.start();
        BufferedReader reader = new BufferedReader( new InputStreamReader( process.getInputStream()));
        String str = reader.readLine();
    }
}
