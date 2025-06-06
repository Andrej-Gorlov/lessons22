package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AuthPage extends BasePage{

    private final SelenideElement authCodeField = $("input[id='otp-code']");
    private final SelenideElement authButton = $("button[id='login-otp-button']");

    public AuthPage(){
        super();
    }

    /**
     * Вводит код подтверждения в соответствующее поле.
     *
     * @param code код подтверждения, который будет введен.
     * @return текущий объект AuthPage для использования Fluent Interface.
     */
    public AuthPage enterAuthCode(String code) {
        enterText(authCodeField, code, "коду подтверждения");
        return this;
    }

    /**
     * Нажимает на кнопку подтверждения и возвращает текущий URL страницы.
     *
     * @return URL текущей страницы после нажатия на кнопку подтверждения.
     */
    public String clickAuth() {
        clickElement(authButton, "кнопка подтверждения");
        return getCurrentUrl();
    }
}
