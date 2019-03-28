package org.portal.authentication;

import org.portal.backend.data.model.Logins;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LoginService{
    @PersistenceContext
    private EntityManager em;


    public String getPass(String userName) {
        List<Logins> log = em.createQuery(
                "SELECT e FROM Logins e where login=:name", Logins.class)
                .setParameter("name", userName)
                .getResultList();
        if (log.size() == 0) {
            return null;
        }
        return log.get(0).getPass_md5();
    }
}
