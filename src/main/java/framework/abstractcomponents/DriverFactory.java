package framework.abstractcomponents;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    // 1. Declare the ThreadLocal variable
    private static final ThreadLocal<WebDriver> driverContext = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverFactory() {} 

    /**
     * 2. Creates and sets a new WebDriver instance for the current thread.
     */
    public static void setDriver(WebDriver driver) {
  
//        driver.manage().window().maximize();
        // Store the unique WebDriver instance in the ThreadLocal
        driverContext.set(driver); 
    }

    /**
     * 3. Gets the WebDriver instance specific to the current thread.
     */
    public static WebDriver getDriver() {
        // 3. Retrieve the thread's unique WebDriver instance
        return driverContext.get(); 
    }

    /**
     * 4. Closes the browser and removes the ThreadLocal value. CRITICAL step.
     */
    public static void removeDriver() {
        if (driverContext.get() != null) {
            driverContext.get().quit();
            // 4. Remove the thread's copy to prevent memory leaks in the thread pool
            driverContext.remove();
        }
    }
}