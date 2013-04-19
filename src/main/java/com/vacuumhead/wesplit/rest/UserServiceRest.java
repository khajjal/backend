package com.vacuumhead.wesplit.rest;

import com.google.gson.Gson;
import com.vacuumhead.wesplit.ViewObject.UserViewObject;
import com.vacuumhead.wesplit.application.ISessionApplicationService;
import com.vacuumhead.wesplit.application.IUserServiceApplicationLogic;
import com.vacuumhead.wesplit.application.UserServiceApplicationLogic;
import com.vacuumhead.wesplit.constants.HttpResponseCode;
import com.vacuumhead.wesplit.responseobject.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 24/03/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/userService")
public class UserServiceRest {

    @Autowired
    private IUserServiceApplicationLogic userServiceApplicationLogic;

    @Autowired
    private ISessionApplicationService sessionApplicationService;

    public void setSessionApplicationService(UserServiceApplicationLogic userServiceApplicationLogic) {
        this.userServiceApplicationLogic = userServiceApplicationLogic;
    }

    @RequestMapping(value = "/createUser/{user}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> createUser(@PathVariable("user") String user, @PathVariable("password") String password, HttpServletRequest request) {

        UserViewObject userObject = userServiceApplicationLogic.createUser(user, password);

        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, userObject);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/existUser/{user}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> checkExistUser(@PathVariable("user") String user, HttpServletRequest request) {

        boolean exist = userServiceApplicationLogic.checkIfUserExist(user);

        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, exist);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/loginUser/{user}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> loginUser(@PathVariable("user") String user, @PathVariable("password") String password, HttpServletRequest request) {


        UserViewObject userObject = userServiceApplicationLogic.loginUser(user, password);
        String responseJson = null;
        if (userObject != null) {
            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("username", user);
            sessionApplicationService.addDataToSession(request.getSession(), dataMap);
            ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, userObject);
            responseJson = new Gson().toJson(responseWrapper);
        }


        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/logoutUser/{user}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> logoutUser(@PathVariable("user") String user, HttpServletRequest request) {


        sessionApplicationService.invalidateSession(request.getSession());

        return new ResponseEntity<String>("INVALIDATED",
                new HttpHeaders(), HttpStatus.OK);

    }
}
