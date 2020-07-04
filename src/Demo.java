public class Demo {
    public static void main(String[] args) {
        User u1 = new User("user1");
        User u2 = new User("user2");

        // Each message has an id. Assume that ids are unique.
        Message mt1 = new TextMessage(1); // A text message contains only some text
        mt1.setContents("Some text message");

        Message mi2 = new ImageMessage(2); // An image message contains only an image

        MultiPartMessage mm3 = new MultiPartMessage(3); // A multipart message contains text and attachments
        mm3.setContents("Text of multipart message");
        mm3.addAttachment("file1.pdf"); // file1.pdf is a local file
        mm3.addAttachment(mi2);

        Message mc2 = new ReceiptConfMessage(mi2); //this adds a confirmation receipt to mi2 every user that receives this message must inform the sender that the message has been processed

        mi2.setContents("0331.JPG"); // img.jpg is a local file

        Channel ch1 = new Channel(); // create channel and connect users to it
        ch1.connect(u1);
        ch1.connect(u2);






        u1.sendMessage(ch1,mt1); //sends the message mt1 to all other users connected to the channel
        u2.processLastReceivedMessage(); // this should print out something like: "user2 received from user1 the message 1 with size 17"

        u1.sendMessage(ch1, mm3);
        u2.processLastReceivedMessage(); // this should print out something like: "user2 received from user1 the message 3 with size ... "

        u1.checkReception(mm3); // this should print out something like "Message 3 has not been confirmed by any receiver" (since no confirmation receipt was attached to it)

        u2.sendMessage(ch1, mc2);
        u1.processLastReceivedMessage();
        u2.checkReception(mi2); //this should print out: “Message 2 has been confirmed by user1” all users who processed the message must be listed
    }
}
