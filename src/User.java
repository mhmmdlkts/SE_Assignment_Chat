import java.util.ArrayList;
import java.util.Arrays;

public class User implements Observer {
    String name;
    ArrayList<MessageAndUser> receivedMessages;

    public User(String name) {
        receivedMessages = new ArrayList<>();
        this.name = name;
    }

    public void sendMessage(Channel ch1, Message msg) {
        ch1.newMessage(msg, this);
        ch1.notifyObservers();
    }

    public void processLastReceivedMessage() {
        MessageAndUser last = receivedMessages.get(receivedMessages.size()-1);
        System.out.println(name + " received from " + last.sender.name + " the message " + last.message.id + " with size " + last.message.getSize() + "\n");
        if (receivedMessages.isEmpty() || last.message.receiptConfMessage == null)
            return;
        last.message.processed(this);
    }

    public void checkReception(Message msg) {
        if (msg.processedBy.isEmpty())
            System.out.println("Message " + msg.id + " has not been confirmed by any receiver");
        else {
            StringBuilder sb = new StringBuilder();
            for(User user:msg.processedBy)
                sb.append(user.name).append(", ");
            if (sb.length() >= 2)
                sb.delete(sb.length()-2, sb.length());
            System.out.println("Message " + msg.id + " has been confirmed by " + sb.toString());
        }
    }

    @Override
    public void notify(Channel channel) {
        MessageAndUser last = channel.getLastMessage();
        if (last == null || last.sender == this)
            return;
        receivedMessages.add(last);
        if (!(last.message instanceof ReceiptConfMessage))
            System.out.println("Msg id: " + last.message.id + ", From: " + last.sender.name + ", To: " + name + ": \n\t" + last.message.getContents().replace("\n","\n\t") + "\n");
        else
            last.message.processed(this);
    }
}
