package org.portal.authentication;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UI content when the user is not logged in yet.
 */
@Route("Login")
@PageTitle("Login")
@HtmlImport("css/shared-styles.html")
public class LoginScreen extends HorizontalLayout {

    private TextField username;
    private PasswordField password;
    private EmailField email;
    private TextField promocode;
    private Button login;
    private Button register;
    private AccessControl accessControl;

    public LoginScreen() {
        accessControl = AccessControlFactory.getInstance().createAccessControl();
        buildUI();
        username.focus();
    }

    private void buildUI() {
        //  setSizeFull();
        setClassName("login-screen");
        Component loginForm = buildLoginForm();
        HorizontalLayout centeringLayout = new HorizontalLayout();
        centeringLayout.setAlignItems(Alignment.CENTER);
        centeringLayout.add(loginForm);
        add(centeringLayout);
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.setWidth("310px");
        loginForm.addFormItem(username = new TextField(), "Username");
        username.setWidth("15em");
        loginForm.add(new Html("<br/>"));
        loginForm.addFormItem(password = new PasswordField(), "Password");
        password.setWidth("15em");
        loginForm.add(new Html("<br/>"));


        loginForm.add(login = new Button("Login"));
        login.addClickListener(event -> login());
        loginForm.getElement().addEventListener("keypress", event -> login()).setFilter("event.key == 'Enter'");
        login.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        loginForm.addFormItem(email = new EmailField(), "Email (only for registration)");
        email.setWidth("15em");
        loginForm.add(new Html("<br/>"));

        loginForm.addFormItem(promocode = new TextField(), "Promo Code (optional for registration)");
        promocode.setWidth("15em");
        loginForm.add(new Html("<br/>"));

        loginForm.add(register = new Button("Register"));
        register.addClickListener(event -> register());
        register.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        VerticalLayout telegram = new VerticalLayout();

        Label telegramLabel = new Label("Join our telegram channel:");
        email.setWidth("20em");
        Anchor telegramLink = new Anchor("https://t.me/sportportal365","https://t.me/sportportal365");

        telegram.add(telegramLabel, telegramLink);
        loginForm.add(telegram);

        return loginForm;
    }

    private void login() {
        login.setEnabled(false);
        try {
            if (accessControl.signIn(username.getValue(), password.getValue())) {
                getUI().get().navigate("Tennis_Leagues");
            } else {
                showNotification("Login failed. Please check another username/pass and try again.");
                username.focus();
                return;
            }
        } finally {
            login.setEnabled(true);
        }
    }

    private void register() {

        if (username.getValue().isEmpty() || password.getValue().isEmpty()) {
            showNotification("Enter Username and password !!!");
            return;
        }

        if (!checkLogin(username.getValue())) {
            showNotification("Only Latin characters and digits allowed. Start with character.  Try another username");
            return;
        }

        if (checkLogin(promocode.getValue())) {
            showNotification("Only Latin characters and digits allowed. Start with character.  Try another promocode");
            return;
        }

        if (accessControl.signIn(username.getValue(), password.getValue())) {
            getUI().get().navigate("Tennis_Leagues");
            return;
        }

        if (email.isInvalid() || email.getValue().isEmpty()) {
            showNotification("Enter valid Email !!!");
            return;
        }

        if (!accessControl.register(username.getValue(), password.getValue(), email.getValue(), promocode.getValue())) {
            showNotification("Username already exist. Please try another");
            return;
        }


        showNotification("Successful create user !!!");
    }

    boolean checkLogin(String login) {
        final int maxLength = 20;
        if (login == null || login.isEmpty() || login.length() > maxLength) {
            return false;
        }

        final Pattern pattern = Pattern.compile("^[a-z][a-z\\d\\.\\-]*[a-z\\d]+$", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    private void showNotification(String message) {
        Notification notification = new Notification(message);
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.setDuration(5000);
        notification.open();
    }
}
