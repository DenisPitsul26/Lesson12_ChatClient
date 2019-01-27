package client;

import java.io.IOException;

public class ListenerThread implements Runnable {

    private MessageProvider messageProvider;
    private MessageRender messageRender;

    public ListenerThread(MessageProvider messageProvider, MessageRender messageRender) {
        this.messageProvider = messageProvider;
        this.messageRender = messageRender;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        try {
            while (!thread.isInterrupted()){
                Message message = messageProvider.readMassage();
                messageRender.renderMassage(message);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("ListenerThread shutdown");
    }
}
