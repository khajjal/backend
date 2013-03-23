package com.vacuumhead.wesplit.tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Vinay
 * Date: 23/3/13
 * Time: 2:00 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="USER_TABLE")
public class User implements Serializable {
    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId=1;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="PASSWORD")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MEMBERSHIP", schema = "wesplit_ddb",
            joinColumns = { @JoinColumn(name = "USER_ID")},
            inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
    private List<Group> groupList=new ArrayList<Group>();

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public User( String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
