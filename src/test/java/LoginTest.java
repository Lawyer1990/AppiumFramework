import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import static configs.AppMessages.INCORRECT_SIZE;
import static configs.AssertMessages.MSG_TEXT_MESSAGEBOX_IS_NOT_CORRECT;
import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginTest extends BaseTest {
    @Test
    public void checkIncorrectSizeCodeTest() {
        loginPage.clickLoginButton();
        String message = authorizationPage.inputCode("123")
                .clickEnterButton()
                .receiveTextMessageBox();
        assertEquals(message, INCORRECT_SIZE, MSG_TEXT_MESSAGEBOX_IS_NOT_CORRECT.getValue());
    }
}
