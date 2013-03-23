package com.vacuumhead.wesplit.rest;

import com.google.gson.Gson;
import com.vacuumhead.wesplit.application.ISessionApplicationService;
import com.vacuumhead.wesplit.application.SessionApplicationService;
import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.constants.HttpResponseCode;
import com.vacuumhead.wesplit.responseobject.ResponseWrapper;
import com.vacuumhead.wesplit.responseobject.SessionWrapper;
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

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 04/02/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/session")
public class SessionManager {

    @Autowired
    private ISessionApplicationService sessionApplicationService;

    public void setSessionApplicationService() {
        this.sessionApplicationService = new SessionApplicationService();
    }

    @RequestMapping(value = "/createSession", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> createSession(HttpServletRequest request) {

        SessionWrapper sessionObject = sessionApplicationService.createSession(request);

        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, sessionObject);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson, new HttpHeaders(), HttpStatus.OK);

    }




    @RequestMapping(value = "/createUser/{user}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> createUser(@PathVariable("user") String user, @PathVariable("password") String password, HttpServletRequest request) {

        AccountCodes responseCode = sessionApplicationService.createUser(user, password);

        return new ResponseEntity<String>(request.getSession().getId() + " .. " + request.getSession().getLastAccessedTime(),
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/existUser/{user}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> checkExistUser(@PathVariable("user") String user, HttpServletRequest request) {

        AccountCodes responseCode = sessionApplicationService.checkExistUser(user);

        return new ResponseEntity<String>(responseCode.toString(),
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/loginUser/{user}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> loginUser(@PathVariable("user") String user, @PathVariable("password") String password, HttpServletRequest request) {

        AccountCodes responseCode = sessionApplicationService.loginUser(user, password);

        return new ResponseEntity<String>(responseCode.toString(),
                new HttpHeaders(), HttpStatus.OK);

    }
        @RequestMapping(value = "/checkAlreadyLogged/{user}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> checkAlreadyLogged(@PathVariable("user") String user, HttpServletRequest request) {

        AccountCodes responseCode = sessionApplicationService.checkAlreadyLogged(user);

        return new ResponseEntity<String>(responseCode.toString(),
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/logoutUser/{user}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> logoutUser(@PathVariable("user") String user, HttpServletRequest request) {

        AccountCodes responseCode = sessionApplicationService.logoutUser(user);

        return new ResponseEntity<String>(responseCode.toString(),
                new HttpHeaders(), HttpStatus.OK);

    }
}
