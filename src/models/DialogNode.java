package models;

import java.util.List;

public class DialogNode {

    private int id;
    private String npcText;
    private List<DialogOption> options;
    private boolean isEnd;

    public DialogNode(int id, String npcText, List<DialogOption> options) {
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

    public List<DialogOption> getOptions() {
        return options;
    }

    public void setOptions(List<DialogOption> options) {
        this.options = options;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}
