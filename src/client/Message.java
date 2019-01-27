package client;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private String text;
    private Date departmentTime;
    private String sender;

    public Message(String text, Date departmentTime, String sender) {
        this.text = text;
        this.departmentTime = departmentTime;
        this.sender = sender;
    }

    public Message() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDepartmentTime() {
        return departmentTime;
    }

    public void setDepartmentTime(Date departmentTime) {
        this.departmentTime = departmentTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", departmentTime=" + departmentTime +
                ", sender='" + sender + '\'' +
                '}';
    }
}
