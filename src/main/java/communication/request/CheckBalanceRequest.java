package communication.request;

import communication.Request;
import jade.core.AID;
import lombok.Getter;

@Getter
public class CheckBalanceRequest extends Request {
    private final String accountNumber;
    private final String secretCode;

    public CheckBalanceRequest(AID senderAID, String accountNumber, String secretCode) {
        super(senderAID);
        this.accountNumber = accountNumber;
        this.secretCode = secretCode;
    }
}
