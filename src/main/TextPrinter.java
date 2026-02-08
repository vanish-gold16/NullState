package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextPrinter {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    public void waitEnter() {
        System.out.print("...");
        try {
            IN.readLine();
        } catch (IOException ignored) {}
    }


    public void type(String text, int pauseMs) {
        if(text == null){
            return;
        }
        System.out.print(text);
        if(pauseMs > 0){
            try{
                Thread.sleep(pauseMs);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
