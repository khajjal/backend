package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.tables.Group;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public interface IGroupApplicationService {
    @Transactional(readOnly = false)
    public Group createGroup(String groupName, Integer accountId);
    public Group retrieveGroupByName(String groupName);
    public Group retrieveGroupById(Integer groupId);
    @Transactional(readOnly = false)
    public Group addMembersToGroup(Integer groupId, Integer accountId);
    @Transactional(readOnly = false)
    public Group addAdminToGroup(Integer groupId, Integer accountId);
    public boolean isAdmin(Integer groupId, Integer accountId);

}
