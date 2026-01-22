package main;

import models.DialogNode;
import models.DialogOption;

public class DialogueService {

    private final TextPrinter printer;
    private final int delayMs;

    public DialogueService(int delayMs) {
        this.printer = new TextPrinter();
        this.delayMs = delayMs;
    }

    public void say(String text) {
        printer.type(text, delayMs);
    }

    public void displayNode(DialogNode node) {
        if(node == null){
            say("Konec dialogu.");
            return;
        }

        say(node.getNpcText());
        if(node.isEnd()){
            return;
        }

        if(node.getOptions() != null){
            for(int i = 0; i < node.getOptions().size(); i++){
                DialogOption option = node.getOptions().get(i);
                System.out.println("[" + (i + 1) + "] " + option.getText());
            }
        }
    }
}
