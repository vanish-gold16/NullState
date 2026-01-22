package models;

import java.util.List;

public class DialogueNode {

    private int id;
    private String npcText;
    private List<DialogueOption> options;
    private boolean isEnd;

    public DialogueNode(int id, String npcText, List<DialogueOption> options) {
        this.id = id;
        this.npcText = npcText;
        this.options = options;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNpcText() {
        return npcText;
    }

    public void setNpcText(String npcText) {
        this.npcText = npcText;
    }

    public List<DialogueOption> getOptions() {
        return options;
    }

    public void setOptions(List<DialogueOption> options) {
        this.options = options;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}
