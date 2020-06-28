package base;

public class utilities {
	
	public static void clickfunction(WebElement element) {
		/*
		 * // Code wil be here // and
		 */	
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.excuteScript("arguments[0].click();" element)
		}

}
