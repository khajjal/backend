package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.dao.IGroupDao;
import com.vacuumhead.wesplit.dao.IUserDao;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class GroupApplicationService implements IGroupApplicationService {

    private IGroupDao groupDao;
    private IUserDao userDao;

    public GroupApplicationService(IGroupDao groupDao, IUserDao userDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
    }

    public Group createGroup(String groupName, Integer accountId) {
        if(retrieveGroupByName(groupName) != null) {
            return null;
        }
        //if user exists
        User user = userDao.retrieveUserById(accountId);
        if(user==null){
                return  null;
        }
        Group newGroup = new Group(groupName);
        groupDao.createGroup(newGroup);
       // newGroup.getAdminList().add(user);
        //user.getGroupAdminList().add(newGroup);
        //user.getGroupMemberList().add(newGroup);
        groupDao.updateGroup(newGroup,user);
        return newGroup;
    }

    public Group retrieveGroupByName(String groupName) {
        return groupDao.retrieveGroupByName(groupName);
    }

    public Group retrieveGroupById(Integer groupId) {
        return groupDao.retrieveGroupById(groupId);
    }

    public Group addMembersToGroup(Integer groupId, Integer accountId) {
        User user = userDao.retrieveUserById(accountId);
        Group group = groupDao.retrieveGroupById(groupId);
        user.getGroupMemberList().add(group);
        group.getUserList().add(user);

        groupDao.updateGroup(group,user);
        return group;
    }

    public Group addAdminToGroup(Integer groupId, Integer accountId) {
        User user = userDao.retrieveUserById(accountId);
        Group group = groupDao.retrieveGroupById(groupId);
        group.getAdminList().add(user);
        groupDao.updateGroup(group,user);
        return group;
    }

    public boolean isAdmin(Integer groupId, Integer accountId) {
        User user = userDao.retrieveUserById(accountId);
        Group group = groupDao.retrieveGroupById(groupId);
        return group.getUserList().contains(user);
    }
}
