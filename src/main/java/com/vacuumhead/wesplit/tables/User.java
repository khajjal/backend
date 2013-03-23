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
@Table(name="USER")
public class User implements Serializable {
    @Id
    @Column(name="USER_ID")
    private int userId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MEMBERSHIP", schema = "wesplit_ddb",
            joinColumns = { @JoinColumn(name = "USER_ID")},
            inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
    private List<Group> groupList=new ArrayList<Group>();

    public User() {
    }
    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
