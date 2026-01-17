public class DialogOption {

    private String text;
    private String nextDialogNodeId;

    public DialogOption(String text, String nextDialogNodeId) {
        this.text = text;
        this.nextDialogNodeId = nextDialogNodeId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNextDialogNodeId() {
        return nextDialogNodeId;
    }

    public void setNextDialogNodeId(String nextDialogNodeId) {
        this.nextDialogNodeId = nextDialogNodeId;
    }
}
