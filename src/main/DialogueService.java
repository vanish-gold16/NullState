package main;

import models.DialogueNode;
import models.DialogueOption;

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

    public void displayNode(DialogueNode node) {
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
                DialogueOption option = node.getOptions().get(i);
                System.out.println("[" + (i + 1) + "] " + option.getText());
            }
        }
    }
}
