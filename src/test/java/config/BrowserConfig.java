package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:stages/${remote}.properties",
        "classpath:stages/local.properties",
})
public interface BrowserConfig extends Config {

    @Key("browser.name")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browser.version")
    @DefaultValue("120")
    String getBrowserVersion();

    @Key("browser.selenoidHost")
    String getSelenoidHost();
}
