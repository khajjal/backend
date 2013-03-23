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
@Table(name = "user_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "account_id"})
})
public class Login implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Integer accountId = 1;

    public Integer getAccountId() {
        return accountId;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


}
