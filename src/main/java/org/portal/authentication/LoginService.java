package org.portal.authentication;

import org.portal.back.model.Logins;
import org.portal.back.model.LoginsRepository;
import org.portal.back.model.PinaccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class LoginService {
    @Autowired
    LoginsRepository loginsRepository;


    public String getPass(String userName) {
        List<Logins> log = loginsRepository.findByLogin(userName);
        if (log.size() == 0) {
            return null;
        }
        return log.get(0).getPass_md5();
    }

    public boolean create(String userName, String passMd5) {
        List<Logins> logList = loginsRepository.findByLogin(userName);
        if (logList.size() > 0) {
            return false;
        }
        Logins log = new Logins();
        log.setLogin(userName);
        log.setPass_md5(passMd5);
        loginsRepository.save(log);
        return true;

    }
}
