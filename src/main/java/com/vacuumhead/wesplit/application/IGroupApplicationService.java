package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.GroupCodes;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IGroupApplicationService {
    public GroupCodes createGroup(String owner, String groupName);
    public GroupCodes checkExistGroup(String groupId);
    public String getMembers(String groupId);
}
