package org.portal.authentication;

import java.io.Serializable;
import com.vaadin.flow.component.datepicker.DatePicker;
import java.time.LocalDate;
/**
 * Simple interface for authentication and authorization checks.
 */
public interface AccessControl extends Serializable {

    String ADMIN_ROLE_NAME = "admin";
    String ADMIN_USERNAME = "admin";

    public boolean signIn(String username, String password);

    boolean register(String username, String password, LocalDate birthDay, String email);

    public boolean isUserSignedIn();

    public boolean isUserInRole(String role);

    public String getPrincipalName();
}
