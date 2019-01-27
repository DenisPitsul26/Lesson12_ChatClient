package client;

import java.io.IOException;

public interface MessageProvider {

    void sendMassage(Message message) throws IOException;

    Message readMassage() throws IOException;
}
