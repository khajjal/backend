package com.vacuumhead.wesplit.tables;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 20/02/13
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "loggedin", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class Loggedin implements Serializable {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="loggedin")
    private boolean loggedin;

    public Loggedin() {
    }

    public Loggedin(String username, boolean loggedin) {

        this.username = username;
        this.loggedin = loggedin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }
}
