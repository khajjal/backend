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
    private List<Group> groupMemberList=new ArrayList<Group>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ADMIN", schema = "wesplit_ddb",
            joinColumns = { @JoinColumn(name = "USER_ID")},
            inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
    private List<Group> groupAdminList=new ArrayList<Group>();
    public User() {
    }

    public List<Group> getGroupMemberList() {
        return groupMemberList;
    }

    public void setGroupMemberList(List<Group> groupMemberList) {
        this.groupMemberList = groupMemberList;
    }

    public List<Group> getGroupAdminList() {
        return groupAdminList;
    }

    public void setGroupAdminList(List<Group> groupAdminList) {
        this.groupAdminList = groupAdminList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
