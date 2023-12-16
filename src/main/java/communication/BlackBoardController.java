package communication;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.io.Serial;
import java.util.*;

public class BlackBoardController extends Agent {
    @Serial
    private static final long serialVersionUID = 23561923881L;

    private Map<Class<? extends Request>, Set<AID>> listenersMap;

    protected void setup() {
        listenersMap = new HashMap<>();
        addBehaviour(new ListenForMessages());
    }

    // Method to subscribe listeners for a specific request type
    public void subscribe(Class<? extends Request> requestType, AID listenerAID) {
        System.out.printf("new subscriber: requestType: %s, AID: %s\n", requestType.toString(), listenerAID.toString());

        listenersMap.computeIfAbsent(requestType, k -> new HashSet<>())
                .add(listenerAID);
    }

    private class ListenForMessages extends CyclicBehaviour {
        public static final long serialVersionUID = 12452;

        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                if (msg.getPerformative() == ACLMessage.INFORM) {
                    try {
                        var content = msg.getContentObject();
                        if (content instanceof SubscribeRequest sr) {
                            subscribe(sr.getRequestType(), sr.getAid());
                        } else if (content instanceof Request request) {
                            notifyListeners(request);
                        }
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Warning: unknown message type: " + msg);
                }
            } else {
                block();
            }
        }
    }

    public <T extends Request> void notifyListeners(T request) {
        var requestType = request.getClass();
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        try {
            msg.setContentObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (var listenerAID : listenersMap.getOrDefault(requestType, new HashSet<>())) {
            msg.addReceiver(listenerAID);
            send(msg);
        }
    }
}
