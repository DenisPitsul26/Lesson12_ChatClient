package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageProviderImpl implements MessageProvider {

    private Socket socket;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;

    public MessageProviderImpl(Socket socket) {
        this.socket = socket;
    }

    public MessageProviderImpl() {
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException{
        this.socket = socket;
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
    }

    private final void closeStream() {
        try {
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMassage(Message message) throws IOException {
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            closeStream();
            throw e;
        }
    }

    @Override
    public Message readMassage() throws IOException {
        try {
            return (Message) ois.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        } catch (IOException e) {
            closeStream();
            throw e;
        }
    }
}
