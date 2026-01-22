package main;

public class TextPrinter {

    public void type(String text, int delayMs) {
        if(text == null){
            return;
        }
        for(int i = 0; i < text.length(); i++){
            System.out.print(text.charAt(i));
            try{
                Thread.sleep(delayMs);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println();
    }
}
