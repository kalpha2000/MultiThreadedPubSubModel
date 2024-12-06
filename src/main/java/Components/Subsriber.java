package Components;

public interface Subsriber {

    public String getId();
    public void consume(Message message) throws InterruptedException;
}
