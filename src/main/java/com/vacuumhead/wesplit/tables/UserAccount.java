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
@Table(name = "USER_ACCOUNT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "account_id"})
})
public class UserAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private Integer accountId = 1;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;
    @OneToOne(mappedBy = "userAccountEmbedded",cascade = CascadeType.ALL)
    private User userEmbedded;

    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getUserEmbedded() {
        return userEmbedded;
    }

    public void setUserEmbedded(User userEmbedded) {
        this.userEmbedded = userEmbedded;
    }
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public UserAccount() {
    }

    public UserAccount(String username, String password) {
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




}
