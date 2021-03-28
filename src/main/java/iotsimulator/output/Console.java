package iotsimulator.output;

public class Console implements Output {

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

}
