package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.ViewObject.GroupViewObject;
import com.vacuumhead.wesplit.dao.IGroupDao;
import com.vacuumhead.wesplit.dao.IUserDao;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class GroupApplicationService implements IGroupApplicationService {
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private IGroupDao groupDao;
    private IUserDao userDao;

    public GroupApplicationService(IGroupDao groupDao, IUserDao userDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
    }

    public GroupViewObject createGroup(String groupName, Integer accountId) {
        EntityManager entityManager = emf.createEntityManager();
        Group newGroup;
        try {
            entityManager.getTransaction().begin();
            User user = userDao.retrieveUserById(entityManager, accountId);
            if (user == null) {
                return null;
            }
            newGroup = new Group(groupName);
            newGroup.getUserList().add(user);
            newGroup.getAdminList().add(user);
            user.getGroupAdminList().add(newGroup);
            user.getGroupMemberList().add(newGroup);
            groupDao.createGroup(entityManager, newGroup);

        } finally {
            entityManager.getTransaction().commit();
        }
        return new GroupViewObject(newGroup);
    }

    public GroupViewObject retrieveGroupByName(String groupName) {
        EntityManager entityManager = emf.createEntityManager();
        Group group = groupDao.retrieveGroupByName(entityManager, groupName);
        return new GroupViewObject(group);
    }

    public GroupViewObject retrieveGroupById(Integer groupId) {
        EntityManager entityManager = emf.createEntityManager();

        Group group = groupDao.retrieveGroupById(entityManager, groupId);
        return new GroupViewObject(group);
    }

    public GroupViewObject addMembersToGroup(Integer groupId, Integer accountId) {
        EntityManager entityManager = emf.createEntityManager();
        Group group;
        try {
            entityManager.getTransaction().begin();
            User user = userDao.retrieveUserById(entityManager, accountId);
            group = groupDao.retrieveGroupById(entityManager, groupId);
            user.getGroupMemberList().add(group);
            group.getUserList().add(user);
            groupDao.updateGroup(entityManager, group);

        } finally {
            entityManager.getTransaction().commit();
        }
        return new GroupViewObject(group);
    }

    public GroupViewObject addAdminToGroup(Integer groupId, Integer accountId) {
        EntityManager entityManager = emf.createEntityManager();
        Group group;
        try {
            entityManager.getTransaction().begin();
            User user = userDao.retrieveUserById(entityManager, accountId);
            group = groupDao.retrieveGroupById(entityManager, groupId);
            if(!group.getUserList().contains(user)) {
                group.getUserList().add(user);
            }
            group.getAdminList().add(user);
            groupDao.updateGroup(entityManager, group);

        } finally {
            entityManager.getTransaction().commit();
        }
        return new GroupViewObject(group);
    }

    public boolean isAdminOfGroup(Integer groupId, Integer accountId) {
        EntityManager em = emf.createEntityManager();
        Group group;
        User user;
        try {
            em.getTransaction().begin();
            user = userDao.retrieveUserById(em, accountId);
            group = groupDao.retrieveGroupById(em, groupId);
        } finally {
            em.getTransaction().commit();
        }

        return group.getAdminList().contains(user);
    }
}
