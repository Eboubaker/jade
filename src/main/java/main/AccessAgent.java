package main;

import communication.BlackBoardController;
import communication.ListenForRequest;
import communication.SubscribeRequest;
import communication.request.WithdrawRequest;
import jade.core.Agent;

public class AccessAgent extends Agent {
    @Override
    protected void setup() {
        send(new SubscribeRequest(getAID(), WithdrawRequest.class, getAID())
                .toMessage(getAID(BlackBoardController.class.getSimpleName())));
        addBehaviour(new ListenForRequest<>(this, WithdrawRequest.class, this::handleWithdrawRequest));

    }

    private void handleWithdrawRequest(WithdrawRequest req) {
        if(req.getStatus().equals("pendingAuthentication")) {
            var sql = """
                    select count(*) from customers where customerId
                    """;
            req.setStatus("authenticated");
        }
    }
}
