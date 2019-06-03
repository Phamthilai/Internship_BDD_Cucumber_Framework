package assertion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;

public class Asserts {

        static Logger log = LogManager.getLogger(Asserts.class);

        protected Asserts() {

        }

        public static void verifyEquals(Object actual, Object expected,String message) {
            try {
                Assert.assertEquals(actual, expected,message);
            } catch (AssertionError e) {
                log.error("[Assert Fail] - " + e.getMessage());
                addToErrorBuffer(e);

            }
        }

        public static void verifyEquals(Object actual, Object expected) {
            try {
                Assert.assertEquals(actual, expected,"");
            } catch (AssertionError e) {
                log.error("[Assert Fail] - " + e.getMessage());
                addToErrorBuffer(e);

            }
        }

        public static void verifyFalse(boolean condition, String message) {
            try{
                Assert.assertFalse(condition, message);
            }catch(AssertionError e){
                log.error("[Assert Fail] - " + e.getMessage());
                addToErrorBuffer(e);
            }
        }

        public static void verifyFalse(boolean condition) {
            try{
                Assert.assertFalse(condition, "");
            }catch(AssertionError e){
                log.error("[Assert Fail] - " + e.getMessage());
                addToErrorBuffer(e);
            }
        }

    public static void verifyTrue(boolean condition, String message) {
        try{
            Assert.assertTrue(condition, message);
        }catch(AssertionError e){
            log.error("[Assert Fail] - " + e.getMessage());
            addToErrorBuffer(e);
        }
    }

    public static void verifyTrue(boolean condition) {
        try{
            Assert.assertTrue(condition, "");
        }catch(AssertionError e){
            log.error("[Assert Fail] - " + e.getMessage());
            addToErrorBuffer(e);
        }
    }

        public static void verifyNotEquals(Object actual1, Object actual2) {
            try{
                Assert.assertNotEquals(actual1,actual2,"");
            }catch(AssertionError e){
                log.error("[Assert Fail] - " + e.getMessage());
                addToErrorBuffer(e);
            }
        }

        public static void verifyNotEquals(Object actual1, Object actual2, String message) {
            try{
                Assert.assertNotEquals(actual1,actual2,message);
            }catch(AssertionError e){
                log.error("[Assert Fail] - " + e.getMessage());
                addToErrorBuffer(e);
            }
        }


    private static void addToErrorBuffer(AssertionError e){

        try{

            VerificationError verificationError = new VerificationError(e.getMessage());

            verificationError.setStackTrace(e.getStackTrace());

            TestMethodErrorBuffer.get().add(verificationError);

        }catch(Exception ex){

            throw new RuntimeException("Please let TestNG know about " + TestMethodListener.class.getName() + " listener for verify statements to work. For more information go to http://testng.org/doc/documentation-main.html#testng-listeners");
        }

    }


    public static void verifyAll() {

       try{
           if (TestMethodErrorBuffer.get().size()>0) {
               throw new RuntimeException(TestMethodErrorBuffer.get().toString());
           }
       } catch (RuntimeException e) {
           e.getMessage();
       }

    }
}
