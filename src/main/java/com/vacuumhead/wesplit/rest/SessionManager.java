package com.vacuumhead.wesplit.rest;

import com.google.gson.Gson;
import com.vacuumhead.wesplit.application.ISessionApplicationService;
import com.vacuumhead.wesplit.application.SessionApplicationService;
import com.vacuumhead.wesplit.constants.HttpResponseCode;
import com.vacuumhead.wesplit.responseobject.ResponseWrapper;
import com.vacuumhead.wesplit.responseobject.SessionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    @RequestMapping(value = "/addDataToSession", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> addDataToSession(@RequestBody String sessionId, HttpServletRequest request) {

        Map dataJson = new Gson().fromJson(sessionId, Map.class);
        SessionWrapper sessionObject = sessionApplicationService.addDataToSession(request.getSession(), dataJson);

        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, sessionObject);
        String responseJson = new Gson().toJson(responseWrapper);

        return new ResponseEntity<String>(responseJson, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getDataFromSession", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getDataFromSession(HttpServletRequest request) {

        SessionWrapper sessionObject = sessionApplicationService.getDataFromSession(request.getSession());

        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, sessionObject.getSessionData());
        String responseJson = new Gson().toJson(responseWrapper);

        return new ResponseEntity<String>(responseJson, new HttpHeaders(), HttpStatus.OK);
    }

}
