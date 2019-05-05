package org.portal.back.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String login;

    @Column
    String ip;

    @Column
    String email;

    @Column
    Date createDate;

    @Column
    Date lastLogin;

    @Column
    String pass_md5;

    @Column
    String role;

    public Logins() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass_md5() {
        return pass_md5;
    }

    public void setPass_md5(String pass_md5) {
        this.pass_md5 = pass_md5;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
