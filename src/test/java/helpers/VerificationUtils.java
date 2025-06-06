package helpers;

import pages.AuthPage;
import pages.LoginPage;
import pages.WelcomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerificationUtils {
    private final LoginPage loginPage;
    private final AuthPage authPage;
    private final WelcomePage welcomePage;

    public VerificationUtils(WelcomePage welcomePage) {
        loginPage = new LoginPage();
        authPage = new AuthPage();
        this.welcomePage = welcomePage;
    }

    /**
     * Выполняет вход в систему с указанными учетными данными.
     *
     * @return URL страницы после входа
     */
    public String performLogin() {
        return loginPage.enterUsername("demo")
                .enterPassword("demo")
                .clickLogin();
    }

    /**
     * Выполняет аутентификацию с указанным кодом.
     *
     * @return URL страницы после аутентификации
     */
    public String performAuth() {
        return authPage.enterAuthCode("0000")
                .clickAuth();
    }

    /**
     * Проверяет, соответствует ли сумма ожидаемому формату.
     *
     * @param amount сумма для проверки
     * @return true, если сумма соответствует формату, иначе false
     */
    public boolean isValidFinancialFreedomAmount(String amount) {
        return amount.matches("^\\d{1,3}( \\d{3})*(\\.\\d{2})? ₽$");
    }

    /**
     * Проверяет URL на соответствие ожидаемому значению.
     *
     * @param expectedUrl ожидаемый URL
     * @param actualUrl фактический URL
     */
    public void verifyUrl(String expectedUrl, String actualUrl) {
        assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");
    }

    /**
     * Проверяет содержимое, для указанного имени карты.
     *
     * @param cardName имя карты для проверки
     */
    public void verifyCardPopoverContent(String cardName) {
        String popoverContent = welcomePage.hoverOverCardSymbol(cardName)
                .getCardPopoverContent(cardName);
        assertEquals(cardName, popoverContent, "Надпись для " + cardName + " не отображается корректно");
    }
}
