package Commands;

public class Exit implements Command{
    @Override
    public String execute() {
        return "Thanks for playing";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
