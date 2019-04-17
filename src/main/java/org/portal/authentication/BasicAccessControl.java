package org.portal.authentication;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.portal.ContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class BasicAccessControl implements AccessControl {
    private final Logger LOGGER = LoggerFactory.getLogger(BasicAccessControl.class);
    protected LoginService loginService = ContextProvider.getBean(LoginService.class);

    @Override
    public boolean signIn(String username, String password) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        String actualPass = loginService.getPass(username);
        if(actualPass==null) {
            return false;
        }
        String md5Pass = md5(password);
        boolean loginSuccess = actualPass.equalsIgnoreCase(md5Pass);
        LOGGER.info("logging for user : " + username);
        if (loginSuccess) {
            CurrentUser.set(username);
        }
        return loginSuccess;
    }

    @Override
    public boolean register(String username, String password) {
     return   loginService.create(username, md5(password));
    }


    private String md5(String password){
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();


            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        return sb.toString();
    }
    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

}
