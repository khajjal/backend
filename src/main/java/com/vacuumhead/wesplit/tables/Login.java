package com.vacuumhead.wesplit.tables;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 20/02/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "login", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "account_id"})
})
public class Login implements Serializable {
    @Id
    @Column(name="account_id")
    private String account_id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public Login() {
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String username, String password) {

        this.username = username;
        this.password = password;
        this.account_id = username + password;
    }

}
