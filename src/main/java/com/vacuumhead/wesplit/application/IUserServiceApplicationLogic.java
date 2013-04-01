package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.tables.Group;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 24/03/13
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        rollbackFor = Exception.class,
        readOnly = true)
public interface IUserServiceApplicationLogic {
    @Transactional(readOnly = false)
    public AccountCodes createUser(String username, String password);
    public AccountCodes loginUser(String username, String password);
    public AccountCodes checkIfUserExist(String username);
    public List<Group> retrieveAllGroupForUser(Integer accountId);
}
