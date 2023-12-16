package communication;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import lombok.Getter;
import lombok.Setter;
import main.Account;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

@Getter
public abstract class Request implements Serializable {
    public final static long serialVersionUID = 23069873086L;
    private final AID senderAID;
    private Request previousRequest;
    @Setter
    private String status;
    @Setter
    private String message;
    @Setter
    private Account account;

    public Request(AID senderAID) {
        this.senderAID = senderAID;
    }

    public void setPrevious(Request previous) {
        this.previousRequest = previous;
    }

    public ACLMessage toMessage(AID receiver) {
        Objects.requireNonNull(receiver);
        var msg = toMessage();
        msg.addReceiver(receiver);
        return msg;
    }

    public ACLMessage toMessage() {
        try {
            var msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContentObject(this);
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
