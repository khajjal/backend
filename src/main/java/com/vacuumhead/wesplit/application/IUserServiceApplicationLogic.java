package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.ViewObject.GroupViewObject;
import com.vacuumhead.wesplit.ViewObject.UserViewObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 24/03/13
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserServiceApplicationLogic {

    public UserViewObject createUser(String username, String password);
    public UserViewObject loginUser(String username, String password);
    public boolean checkIfUserExist(String username);
    public List<GroupViewObject> retrieveAllGroupForUser(Integer accountId);
    public UserViewObject retrieveUser(Integer userId);
}
