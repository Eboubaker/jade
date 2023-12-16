package main;

import communication.BlackBoardController;
import communication.ListenForRequest;
import communication.request.SignUpRequest;
import communication.SubscribeRequest;
import jade.core.Agent;

public class AllReceiverAgent extends Agent {
    public final static long serialVersionUID = 212242086L;

    @Override
    protected void setup() {
        send(new SubscribeRequest(getAID(),
                SignUpRequest.class, getAID()).toMessage(getAID(BlackBoardController.class.getSimpleName())));
        addBehaviour(new ListenForRequest<>(this, SignUpRequest.class, this::handleRequest));
    }

    public void handleRequest(SignUpRequest req) {
        System.out.println("got request " + req);
    }
}
