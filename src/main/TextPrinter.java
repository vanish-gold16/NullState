package main;

public class TextPrinter {

    public void type(String text, int pauseMs) {
        if(text == null){
            return;
        }
        System.out.println(text);
        if(pauseMs > 0){
            try{
                Thread.sleep(pauseMs);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
