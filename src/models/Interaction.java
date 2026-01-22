package models;

import java.util.List;
import java.util.Map;

public class Interaction {

    private Map<String, DialogueNode> dialogNodes;
    private String currentNodeId;
    private boolean active;

    public Interaction(Map<String, DialogueNode> dialogNodes) {
        this.dialogNodes = dialogNodes;
        this.currentNodeId = "start";
        this.active = false;
    }

    public void startDialog(){
        this.currentNodeId = "start";
        this.active = true;
    }

    public DialogueNode getCurrentNode(){
        return dialogNodes.get(currentNodeId);
    }

    public String displayCurrentNode(){
        DialogueNode node = getCurrentNode();
        if(node == null){
            active = false;
            return "Konec dialogu.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(node.getNpcText()).append("\n");

        if(node.isEnd()){
            active = false;
            return sb.toString();
        }

        List<DialogueOption> options = node.getOptions();
        for(int i = 0; i < options.size(); i++){
            sb.append(String.format("[%d] %s\n", i + 1, options.get(i).getText()));
        }

        return sb.toString();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<String, DialogueNode> getDialogNodes() {
        return dialogNodes;
    }

    public void setDialogNodes(Map<String, DialogueNode> dialogNodes) {
        this.dialogNodes = dialogNodes;
    }

    public String getCurrentNodeId() {
        return currentNodeId;
    }

    public void setCurrentNodeId(String currentNodeId) {
        this.currentNodeId = currentNodeId;
    }
}
