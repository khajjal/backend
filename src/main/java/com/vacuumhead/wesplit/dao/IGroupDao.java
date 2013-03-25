package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.tables.Group;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IGroupDao {

    public void createGroup(Group group);
    public Group retrieveGroupById(Integer groupId);
    public Group retrieveGroupByName(String groupName);
    public void updateGroup(Group group);

}
