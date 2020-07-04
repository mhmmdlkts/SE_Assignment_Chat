import java.util.ArrayList;

public class MultiPartMessage extends Message {
    ArrayList<Message> messageAttachment;
    ArrayList<String> fileAttachment;

    public MultiPartMessage(int id) {
        super(id);
        messageAttachment = new ArrayList<>();
        fileAttachment = new ArrayList<>();
    }

    public void setContents(String text) {
        super.content = text;
    }

    @Override
    public String getContents() {
        int attachmentCounter = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (String file:fileAttachment)
            stringBuilder.append(attachmentCounter++).append(". Attachment: ").append(file).append(System.getProperty("line.separator"));
        for (Message message:messageAttachment)
            stringBuilder.append(attachmentCounter++).append(". Attachment: ").append(message.getContents()).append(System.getProperty("line.separator"));
        int last = stringBuilder.lastIndexOf(System.getProperty("line.separator"));
        if (last >= 0) { stringBuilder.delete(last, stringBuilder.length()); }

        return stringBuilder.toString();
    }

    @Override
    public void processed(User user) {
        if (!processedBy.contains(user))
            processedBy.add(user);
        processedBy.add(user);
        for (Message message:messageAttachment)
            message.processed(user);
    }

    @Override
    public int getSize() {
        int size = 0;
        for (String file:fileAttachment)
            size += file.length()*file.length()*file.length()*file.length(); // TODO
        for (Message message:messageAttachment)
            size += message.getSize();
        return size;
    }

    public void addAttachment(String file) {
        fileAttachment.add(file);
    }

    public void addAttachment(Message msg) {
        messageAttachment.add(msg);
    }

    @Override
    public void putReceiptConfMessage(ReceiptConfMessage rcf) {
        this.receiptConfMessage = rcf;
        for (Message message:messageAttachment)
            message.putReceiptConfMessage(rcf);
    }
}
