package new_UI;


import com.twilio.Twilio;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class TwiloSMS {
	public static final String ACCOUNT_SID = "AC23f8da5cf53bde8c9d4b11fcf31c797a";
	public static final String AUTH_TOKEN = "d331cfb47d30a24b35c2572c3dce7f82";


	public static void main(String[] args) throws Exception {
		ReciverOTP();
	}

	public static String ReciverOTP() throws InterruptedException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Thread.sleep(2000);
		String smsBody = getMessage();
		System.out.println("The full Message : \n" + smsBody.trim());
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
		OTPNumber = OTPNumber.replaceAll("\\s+", "");
		System.out.println("Only numbers:\n" + OTPNumber);
		OTPNumber = OTPNumber.substring(0, 6);
		System.out.println("GOT from Twilio : " + OTPNumber);
		return OTPNumber;
	}

	public static String getMessage() {

		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+12058946954")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}

	private static Stream<Message> getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
}
