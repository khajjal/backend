package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class GroupDao implements IGroupDao {

  /*  @PersistenceUnit
    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }*/

    public GroupDao() {
    }

    public void createGroup(EntityManager entityManager,Group group) {

        //EntityManager entityManager = emf.createEntityManager();
        //entityManager.getTransaction().begin();
        entityManager.persist(group);
        //entityManager.getTransaction().commit();
    }

    public Group retrieveGroupById(EntityManager entityManager,Integer groupId) {

        //EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Group> query = entityManager.createQuery("select g from Group g where groupId = :groupId", Group.class);
        query.setParameter("groupId", groupId);
        List<Group> resultSet =  query.getResultList();
        return resultSet.size() > 0 ? resultSet.get(0) : null ;
    }

    public Group retrieveGroupByName(EntityManager entityManager,String groupName) {

        //EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Group> query = entityManager.createQuery("select g from Group g where groupName = :groupName", Group.class);
        query.setParameter("groupName", groupName);
        List<Group> resultSet =  query.getResultList();
        return resultSet.size() > 0 ? resultSet.get(0) : null ;
    }



    public void updateGroup(EntityManager entityManager,Group group) {
        //EntityManager entityManager = emf.createEntityManager();
        //entityManager.getTransaction().begin();
        /*user=entityManager.find(User.class,user.getUserrId());
        group=entityManager.find(Group.class,group.getGroupId());
        user.getGroupMemberList().add(group);
        group.getUserList().add(user);
        //group=entityManager.find(Group.class,group.getGroupId());*/
        entityManager.merge(group);
        //entityManager.getTransaction().commit();
    }
}
