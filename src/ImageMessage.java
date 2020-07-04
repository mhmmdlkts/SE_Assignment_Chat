public class ImageMessage extends Message {
    public ImageMessage(int id) {
        super(id);
    }

    @Override
    public void processed(User user) {
        if (!processedBy.contains(user))
            processedBy.add(user);
    }

    @Override
    public void setContents(String content) {
        super.content = content;
    }

    @Override
    public String getContents() {
        return content;
    }

    @Override
    public int getSize() {
        return content.length()*content.length()*content.length()*content.length();
    }

    @Override
    public void putReceiptConfMessage(ReceiptConfMessage rcf) {
        this.receiptConfMessage = rcf;
    }
}
