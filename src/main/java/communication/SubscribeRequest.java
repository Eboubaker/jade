package communication;

import jade.core.AID;
import lombok.Getter;

@Getter
public class SubscribeRequest extends Request {
    private static final long serialVersionUID = 1245235681L;

    private final Class<? extends Request> requestType;
    private final AID aid;

    public SubscribeRequest(AID senderAid, Class<? extends Request> requestType, AID aid) {
        super(senderAid);
        this.requestType = requestType;
        this.aid = aid;
    }
}
