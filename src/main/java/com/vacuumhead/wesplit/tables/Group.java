package com.vacuumhead.wesplit.tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vinay
 * Date: 23/3/13
 * Time: 2:17 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="GROUP_TABLE")
public class Group implements Serializable {
    @Id
    @Column(name="GROUP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groupId=1;

    @Column(name="GROUP_NAME")
    private String groupName;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "MEMBERSHIP", schema = "wesplit_ddb",
            joinColumns = {@JoinColumn(name = "GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> userList=new ArrayList<User>();

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "ADMIN", schema = "wesplit_ddb",
            joinColumns = {@JoinColumn(name = "GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> adminList=new ArrayList<User>();


    @OneToMany(mappedBy = "associatedGroup",cascade = CascadeType.ALL)
    private List<Bill> billList=new ArrayList<Bill>();

    public void addBill(Bill bill){
       billList.add(bill);
    }
    public List<User> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<User> adminList) {
        this.adminList = adminList;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public Group(String groupName) {
        this.groupName = groupName;
       // this.adminList.add(owner);
       // this.userList.add(owner);
    }

    public Group() {
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
