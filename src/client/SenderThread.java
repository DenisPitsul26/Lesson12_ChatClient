package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class SenderThread implements Runnable {

    private MessageProvider messageProvider;
    private String sender;

    public SenderThread(MessageProvider messageProvider, String sender) {
        this.messageProvider = messageProvider;
        this.sender = sender;
    }

    public SenderThread() {
    }

    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public void run() {
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            Thread thread = Thread.currentThread();
            while (!thread.isInterrupted()) {
                String text = buff.readLine();
                Message message = new Message(text, new Date(), sender);
                messageProvider.sendMassage(message);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("SenderThread shutdown");
    }
}
