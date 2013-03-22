package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.GroupCodes;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IGroupDataLayer {
    public GroupCodes createGroup(String owner, String groupName);
    public GroupCodes checkExistGroup(String groupId);
    public String getMembers(String groupId);
}
