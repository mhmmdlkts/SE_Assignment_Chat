import java.util.ArrayList;

public abstract class Message {
    int id;
    String content;
    ArrayList<User> processedBy;
    ReceiptConfMessage receiptConfMessage;

    public Message(int id) {
        processedBy = new ArrayList<>();
        this.id = id;
    }

    public abstract void putReceiptConfMessage(ReceiptConfMessage receiptConfMessage);

    public abstract void processed (User user);

    public abstract void setContents(String content);

    public abstract String getContents();

    public abstract int getSize();

}