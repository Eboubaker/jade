package communication.request;

import communication.Request;
import jade.core.AID;
import lombok.Getter;

@Getter
public class SignUpRequest extends Request {
    public final static long serialVersionUID = 289176873086L;
    private final String firstName;
    private final String lastName;
    private final String nationalIDNumber;

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalIDNumber='" + nationalIDNumber + '\'' +
                '}';
    }

    public SignUpRequest(AID senderAID, String firstName, String lastName, String nationalIDNumber) {
        super(senderAID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIDNumber = nationalIDNumber;
    }
}
