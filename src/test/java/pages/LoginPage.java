package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage{
    private final SelenideElement usernameField = $("input[id='username']");
    private final SelenideElement passwordField = $("input[name='password']");
    private final SelenideElement loginButton = $("button[id='login-button']");

    public LoginPage(){
        super();
    }

    /**
     * Вводит имя пользователя в соответствующее поле.
     *
     * @param username имя пользователя, которое будет введено.
     * @return текущий объект LoginPage для использования Fluent Interface.
     */
    public LoginPage enterUsername(String username) {
        enterText(usernameField, username, "username");
        return this;
    }

    /**
     * Вводит пароль в соответствующее поле.
     *
     * @param password пароль, который будет введен.
     * @return текущий объект LoginPage для использования Fluent Interface.
     */
    public LoginPage enterPassword(String password) {
        enterText(passwordField, password, "password");
        return this;
    }

    /**
     * Нажимает на кнопку входа и возвращает текущий URL страницы.
     *
     * @return URL текущей страницы после нажатия на кнопку входа.
     */
    public String clickLogin() {
        clickElement(loginButton, "кнопка входа");
        return getCurrentUrl();
    }
}
