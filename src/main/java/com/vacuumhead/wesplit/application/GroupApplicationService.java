package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.GroupCodes;
import com.vacuumhead.wesplit.dao.GroupDataLayer;
import com.vacuumhead.wesplit.dao.IGroupDataLayer;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class GroupApplicationService implements IGroupApplicationService {

    private IGroupDataLayer groupDataLayer;

    public GroupApplicationService() {
        this.groupDataLayer = new GroupDataLayer();
    }

    public GroupCodes createGroup(String owner, String groupName) {
        String groupId = owner+groupName;
        if (groupDataLayer.checkExistGroup(groupId).equals(GroupCodes.GROUP_ALREADY_EXIST)) {
            return GroupCodes.GROUP_ALREADY_EXIST;
        }
        return groupDataLayer.createGroup(owner, groupName);
    }

    public GroupCodes checkExistGroup(String groupId) {
        return groupDataLayer.checkExistGroup(groupId);
    }

    public String getMembers(String groupId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
