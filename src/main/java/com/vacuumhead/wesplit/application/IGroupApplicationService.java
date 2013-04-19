package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.ViewObject.GroupViewObject;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IGroupApplicationService {

    public GroupViewObject createGroup(String groupName, Integer accountId);
    public GroupViewObject retrieveGroupByName(String groupName);
    public GroupViewObject retrieveGroupById(Integer groupId);
    public GroupViewObject addMembersToGroup(Integer groupId, Integer accountId);
    public GroupViewObject addAdminToGroup(Integer groupId, Integer accountId);
    public boolean isAdminOfGroup(Integer groupId, Integer accountId);

}
