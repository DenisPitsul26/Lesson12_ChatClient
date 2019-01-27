package client;

public class MessageRenderImpl implements MessageRender {

    @Override
    public void renderMassage(Message message) {
        System.out.println(message.getText()+" - "+message.getDepartmentTime());
    }
}
