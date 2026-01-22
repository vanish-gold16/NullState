package main;

public class TextPrinter {

    public static void type(String text){
        for (int i = 0; i < text.length(); i++) {
            System.out.println(text.charAt(i));
            try{
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }

}
