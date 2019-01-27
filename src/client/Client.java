package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private String nickName;
    private Socket socket;
    private String serverIP;
    private BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
    private MessageProvider messageProvider = null;
    private MessageRender messageRender = null;
    private Thread listenerThread;
    private Thread senderThread;

    public Client() {
    }

    private boolean init() {
        for (boolean correct = false; !correct;) {
            System.out.println("Please input Server IP");
            try {
                this.serverIP = buff.readLine();
                this.socket = new Socket(serverIP, 8080);
                System.out.println("Input your nickName");
                this.nickName = buff.readLine();
                correct = true;
            } catch (UnknownHostException e) {
                System.out.println("Unable to connect. Try another IP");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("Initialization start");
        MessageProviderImpl messageProviderImpl = new MessageProviderImpl();
        MessageRenderImpl messageRenderImpl = new MessageRenderImpl();
        System.out.println("Set Provider and render Implementation");
        try {
            messageProviderImpl.setSocket(this.socket);
            this.messageProvider = messageProviderImpl;
            this.messageRender = messageRenderImpl;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        this.listenerThread = new Thread(new ListenerThread(this.messageProvider, this.messageRender));
        this.senderThread = new Thread(new SenderThread(messageProvider, nickName));
        System.out.println("Initialization end");

        return true;
    }

    public void start() {
        boolean startInit = this.init();
        if (!startInit) {
            System.out.println("Initialization failed...");
            return;
        }
        System.out.println("Start chat. Type text and press Enter");
        listenerThread.start();
        senderThread.start();
        while (listenerThread.isAlive() && senderThread.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (listenerThread.isAlive()) {
            listenerThread.interrupt();
        }
        if (senderThread.isAlive()) {
            senderThread.interrupt();
        }
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}