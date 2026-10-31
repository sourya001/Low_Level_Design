import java.util.*;

interface DocumentSessionMediator {
    void broadcastchange(String change, User sender);

    void join(User user);
}

class CollaborativeDocument implements DocumentSessionMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void join(User user) {
        users.add(user);
    }

    @Override
    public void broadcastchange(String change, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receiveChange(change, sender);
            }
        }
    }
}

class User {
    protected String name;
    protected DocumentSessionMediator mediator;

    public User(String name, DocumentSessionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void makeChange(String change) {
        System.out.println(name + " edited the document: " + change);
        mediator.broadcastchange(change, this);
    }

    public void receiveChange(String change, User sender) {
        System.out.println(name + " saw change from " + sender.name + ": \"" + change + "\"");
    }
}

public class MediatorPattern {
    public static void main(String[] args) {
        CollaborativeDocument doc = new CollaborativeDocument();

        User alice = new User("Alice", doc);
        User bob = new User("Bob", doc);
        User charlie = new User("Charlie", doc);

        doc.join(alice);
        doc.join(bob);
        doc.join(charlie);

        alice.makeChange("Added project title");
        bob.makeChange("Corrected grammar in paragraph 2");
    }
}