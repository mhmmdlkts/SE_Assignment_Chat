import java.util.ArrayList;

public class Channel implements Subject{
    ArrayList<MessageAndUser> messages;
    ArrayList<Observer> observers;

    public Channel() {
        messages = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void connect(User user) {
        register(user);
    }

    public void newMessage(Message message, User sender) {
        messages.add(new MessageAndUser(message, sender));
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers)
            o.notify(this);
    }

    public MessageAndUser getLastMessage() {
        if (messages.isEmpty())
            return null;
        return messages.get(messages.size()-1);
    }
}
