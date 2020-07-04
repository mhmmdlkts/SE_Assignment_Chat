public class TextMessage extends Message {
    public TextMessage(int id) {
        super(id);
    }

    @Override
    public void processed(User user) {
        /*if (receiptConfMessage == null)
            return;*/
        processedBy.add(user);
    }

    @Override
    public void setContents(String content) {
        super.content = content;
    }

    @Override
    public String getContents() {
        return super.content;
    }

    @Override
    public int getSize() {
        return content.length();
    }

    @Override
    public void putReceiptConfMessage(ReceiptConfMessage rcf) {
        this.receiptConfMessage = rcf;
    }
}
