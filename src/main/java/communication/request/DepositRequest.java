package communication.request;

import communication.Request;
import jade.core.AID;
import lombok.Getter;

@Getter
public class DepositRequest extends Request {
    private final String accountNumber;
    private final String secretCode;
    private final double amount;

    public DepositRequest(AID senderAID, String accountNumber, String secretCode, double amount) {
        super(senderAID);
        this.accountNumber = accountNumber;
        this.secretCode = secretCode;
        this.amount = amount;
    }
}
