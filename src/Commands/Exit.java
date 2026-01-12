package Commands;

public class Exit implements Command{
    @Override
    public String execute() {
        return "Go fuck yourself";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
