package com.vacuumhead.wesplit.ViewObject;

import com.vacuumhead.wesplit.tables.Bill;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 19/04/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupViewObject {

    private Integer groupId;
    private String groupName;
    private List<Integer> userList=new ArrayList<Integer>();
    private List<Integer> adminList=new ArrayList<Integer>();
    private List<Integer> billList=new ArrayList<Integer>();

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

    public List<Integer> getUserList() {
        return userList;
    }

    public void setUserList(List<Integer> userList) {
        this.userList = userList;
    }

    public List<Integer> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Integer> adminList) {
        this.adminList = adminList;
    }

    public List<Integer> getBillList() {
        return billList;
    }

    public void setBillList(List<Integer> billList) {
        this.billList = billList;
    }

    public GroupViewObject(Group group) {
        this.groupId = group.getGroupId();
        this.groupName = group.getGroupName();
        for(User user : group.getUserList()) {
            userList.add(user.getUserrId());
        }
        for(User user : group.getAdminList()) {
            adminList.add(user.getUserrId());
        }
        for(Bill bill : group.getBillList()) {
            billList.add(bill.getBillId());
        }
    }
}
