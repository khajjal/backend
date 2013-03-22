package com.vacuumhead.wesplit.rest;

import com.vacuumhead.wesplit.application.GroupApplicationService;
import com.vacuumhead.wesplit.application.IGroupApplicationService;
import com.vacuumhead.wesplit.constants.GroupCodes;
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
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:27 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/group")
public class GroupManager {

    @Autowired
    private IGroupApplicationService groupApplicationService;

    public void setGroupApplicationService(IGroupApplicationService groupApplicationService) {
        this.groupApplicationService = new GroupApplicationService();
    }

    @RequestMapping(value = "/createGroup/{user}/{groupname}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> createGroup(@PathVariable("user") String user, @PathVariable("groupname") String groupName, HttpServletRequest request) {

        GroupCodes responseCode = groupApplicationService.createGroup(user, groupName);

        return new ResponseEntity<String>(responseCode.toString(),
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/existGroup/{user}/{groupname}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> checkExistGroup(@PathVariable("user") String user, @PathVariable("groupname") String groupName, HttpServletRequest request) {

        String groupId = user+groupName;
        GroupCodes responseCode = groupApplicationService.checkExistGroup(groupId);

        return new ResponseEntity<String>(responseCode.toString(),
                new HttpHeaders(), HttpStatus.OK);

    }
}
