package assertion;

import org.testng.annotations.Listeners;


public class VerificationError extends Error{

    private static final long serialVersionUID = 8247563849457669512L;

    public VerificationError(String message){
            super(message);
    }

}
