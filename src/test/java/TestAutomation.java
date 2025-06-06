import helpers.BrowserManager;
import helpers.ResultOutput;
import helpers.VerificationUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.WelcomePage;

import static org.junit.jupiter.api.Assertions.*;

public class TestAutomation {
    private String nameMethod;
    private final String auth = "https://idemo.bspb.ru/";
    private VerificationUtils verifyUrl;
    private WelcomePage welcomePage;

    @BeforeEach
    public void setUp() {
        ResultOutput.log("выполнение тестов класса TestAutomation");
        welcomePage = new WelcomePage();
        verifyUrl = new VerificationUtils(welcomePage);
    }

    @Test
    public void testLoginAndVerifyFinancialFreedom() {
        nameMethod = "testLoginAndVerifyFinancialFreedom";
        ResultOutput.printTestStart(nameMethod);

        String pageLogin = verifyUrl.performLogin();
        verifyUrl.verifyUrl(auth + "auth/otp", pageLogin);

        String pageAuth = verifyUrl.performAuth();
        verifyUrl.verifyUrl(auth + "welcome", pageAuth);

        assertDoesNotThrow(() -> welcomePage.checkFinancialFreedomVisible(),
                "Блок 'Финансовая свобода' не отображается или вызвал исключение");

        assertTrue(verifyUrl.isValidFinancialFreedomAmount(welcomePage.retrieveAndFormatFinancialFreedomAmount()),
                "Сумма на странице не соответствует ожидаемому формату.");

        verifyUrl.verifyCardPopoverContent("Travel *6192");
        verifyUrl.verifyCardPopoverContent("Золотая *2224");
    }

    @AfterEach
    public void tearDown() {
        ResultOutput.printTestEnd(nameMethod);
        BrowserManager.closeDriver();
    }
}
