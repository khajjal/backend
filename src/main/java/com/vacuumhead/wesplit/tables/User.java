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
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId = 1;

    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private UserAccount userAccountEmbedded;

    @ManyToMany(mappedBy = "userList",cascade = CascadeType.ALL)
    private List<Group> groupMemberList = new ArrayList<Group>();

    @ManyToMany(mappedBy = "adminList",cascade = CascadeType.MERGE)
    private List<Group> groupAdminList = new ArrayList<Group>();

    public User() {
    }

    public UserAccount getUserAccountEmbedded() {
        return userAccountEmbedded;
    }

    public void setUserAccountEmbedded(UserAccount userAccountEmbedded) {
        this.userAccountEmbedded = userAccountEmbedded;
    }

    public Integer getUserrId() {
        return userId;
    }

    public void setUserrId(Integer userrId) {
        this.userId = userrId;
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


}
