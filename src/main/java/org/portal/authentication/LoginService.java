package org.portal.authentication;

import org.portal.back.model.Logins;
import org.portal.back.model.LoginsRepository;
import org.portal.back.model.PinaccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class LoginService {
    @Autowired
    LoginsRepository loginsRepository;


    public String getPass(String userName) {
        Date current = Calendar.getInstance().getTime();

        List<Logins> log = loginsRepository.findByLogin(userName);
        if (log.size() == 0) {
            return null;
        }
        log.get(0).setLastLogin(current);
        log.get(0).setIp(CurrentUser.getIp());
        loginsRepository.save(log.get(0));
        return log.get(0).getPass_md5();
    }

    public boolean create(String userName, String email, String passMd5) {
        List<Logins> logList = loginsRepository.findByLogin(userName);
        if (logList.size() > 0) {
            return false;
        }
        Date current = Calendar.getInstance().getTime();
        Logins log = new Logins();
        log.setLogin(userName);
        log.setPass_md5(passMd5);
        log.setEmail(email);
        log.setCreateDate(current);
        loginsRepository.save(log);
        return true;

    }
}
