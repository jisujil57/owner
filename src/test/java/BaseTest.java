import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.BrowserConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        setUpBrowser();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    static void setUpBrowser() {
        BrowserConfig config = ConfigFactory.create(BrowserConfig.class, System.getProperties());

        Configuration.baseUrl = System.getProperty("baseUrl", "https://www.google.com");
        Configuration.browser = config.getBrowserName();;
        Configuration.browserVersion = config.getBrowserVersion();

        if ("remote".equals(System.getProperty("remote"))) {
            String selenoidHost = config.getSelenoidHost();
            Configuration.remote = selenoidHost;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true));
            Configuration.browserCapabilities = capabilities;

            System.out.println("Удаленный запуск с Selenoid: " + selenoidHost);
        } else {
            System.out.println("Локальный запуск");
        }
    }
}
