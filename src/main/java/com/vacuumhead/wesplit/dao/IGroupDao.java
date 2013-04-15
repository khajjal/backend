package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IGroupDao {

    public void createGroup(EntityManager entityManager,Group group);
    public Group retrieveGroupById(EntityManager entityManager,Integer groupId);
    public Group retrieveGroupByName(EntityManager entityManager,String groupName);
    public void updateGroup(EntityManager entityManager,Group group);

}
