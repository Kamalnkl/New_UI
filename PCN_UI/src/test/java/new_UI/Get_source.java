package new_UI;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Get_source {

	@FindBy(xpath = "//a[contains(text(),'Compare')]")
	public static WebElement signup;
	
	
	public void dta() {
		
		
    signup.click();
		
		
		
	}
}
