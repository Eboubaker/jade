package communication;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.function.Consumer;

public class ListenForRequest<T extends Request> extends CyclicBehaviour {
    public static final long serialVersionUID = 12452;
    private final Consumer<T> consumer;
    private final Class<T> klass;

    public ListenForRequest(Agent thisAgent, Class<T> klass, Consumer<T> consumer) {
        super(thisAgent);
        this.consumer = consumer;
        this.klass = klass;
    }

    @SuppressWarnings("unchecked")
    public void action() {
        ACLMessage msg = getAgent().receive();
        if (msg != null) {
            try {
                var req = msg.getContentObject();
                if (req.getClass().isAssignableFrom(klass)) {
                    consumer.accept((T) req);
                } else {
                    System.out.println("Warning: message ignored: " + req.toString());
                }
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}
