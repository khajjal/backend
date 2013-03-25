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
        User user = userDao.retrieveUserById(accountId);
        Group newGroup = new Group(groupName, user);
        groupDao.createGroup(newGroup);
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
        group.getUserList().add(user);
        groupDao.updateGroup(group);
        return group;
    }

    public Group addAdminToGroup(Integer groupId, Integer accountId) {
        User user = userDao.retrieveUserById(accountId);
        Group group = groupDao.retrieveGroupById(groupId);
        group.getAdminList().add(user);
        groupDao.updateGroup(group);
        return group;
    }

    public boolean isAdmin(Integer groupId, Integer accountId) {
        User user = userDao.retrieveUserById(accountId);
        Group group = groupDao.retrieveGroupById(groupId);
        return group.getUserList().contains(user);
    }
}
