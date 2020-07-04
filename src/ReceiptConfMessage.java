public class ReceiptConfMessage extends Message {

    Message confMessage;

    public ReceiptConfMessage(Message msg) {
        super(msg.id);
        confMessage = msg;
        putReceiptConfMessage(this);
    }

    @Override
    public void setContents(String content) { }

    @Override
    public void processed(User user) {
        if (!processedBy.contains(user))
            processedBy.add(user);
        processedBy.add(user);
        confMessage.processed(user);
    }

    @Override
    public String getContents() {
        return "Message Received id: " + id;
    }

    @Override
    public int getSize() {
        return 8; // TODO
    }

    @Override
    public void putReceiptConfMessage(ReceiptConfMessage rcf) {
        this.receiptConfMessage = rcf;
    }
}