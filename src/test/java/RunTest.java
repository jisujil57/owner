import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunTest extends BaseTest {

    @Test
    void googleTitleTest() {

        Selenide.open("/");
        Assertions.assertEquals("Google", Selenide.title());
    }

}
