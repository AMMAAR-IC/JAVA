import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class WebShot {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless");
        WebDriver d = new ChromeDriver(opts);
        d.get("https://github.com/AMMAAR-IC");
        File shot = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shot, new File("github.png"));
        d.quit();
        System.out.println("📸 Screenshot saved!");
    }
}
