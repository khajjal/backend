package com.vacuumhead.wesplit.rest;

import com.google.gson.Gson;
import com.vacuumhead.wesplit.ViewObject.BillViewObject;
import com.vacuumhead.wesplit.application.IBillApplicationService;
import com.vacuumhead.wesplit.constants.HttpResponseCode;
import com.vacuumhead.wesplit.responseobject.ResponseWrapper;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 14/04/13
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/bill")
public class BillServiceRest {

    @Autowired
    private IBillApplicationService billApplicationService;

    public void setBillApplicationService(IBillApplicationService billApplicationService) {
        this.billApplicationService = billApplicationService;
    }

    @RequestMapping(value = "/createBill/{userId}", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createBill(@PathVariable("userId") String billOwner, @RequestBody String billJSON, HttpServletRequest request) {

        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        HashMap<String, Object> billMap = null;
        try {
            billMap = mapper.readValue(billJSON, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Integer userId = Integer.parseInt(billOwner);
        Integer groupId = Integer.parseInt(String.valueOf(billMap.get("groupId")));

        Map<Integer, Double> billConsumerMap = (Map<Integer, Double>) billMap.get("consumerMap");
        Map<Integer, Double> consumerMap = new HashMap<Integer, Double>();
        Iterator consumerIterator = billConsumerMap.keySet().iterator();
        while (consumerIterator.hasNext()) {
           Integer key = Integer.parseInt(consumerIterator.next().toString());
           Double value = Double.parseDouble(String.valueOf(billConsumerMap.get(key.toString())));
           consumerMap.put(key, value);
        }

        Map<Integer, Double> billContributorMap = (Map<Integer, Double>) billMap.get("contributorMap");
        Map<Integer, Double> contributorMap = new HashMap<Integer, Double>();
        Iterator contributorIterator = billContributorMap.keySet().iterator();
        while (contributorIterator.hasNext()) {
            Integer key = Integer.parseInt(contributorIterator.next().toString());
            Double value = Double.parseDouble(String.valueOf(billContributorMap.get(key.toString())));
            contributorMap.put(key, value);
        }

        String billDesc = (String) billMap.get("billDesc");
        BillViewObject bill = billApplicationService.createBill(userId, groupId, consumerMap, contributorMap, billDesc);
        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, bill);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/editBill/{billId}/{userId}", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> editBill(@PathVariable("billId") String _billId, @PathVariable("userId") String billOwner, @RequestBody String billJSON, HttpServletRequest request) {

        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        HashMap<String, Object> billMap = null;
        try {
            billMap = mapper.readValue(billJSON, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Integer billId = Integer.parseInt(_billId);
        Integer userId = Integer.parseInt(billOwner);
        Integer groupId = Integer.parseInt(String.valueOf(billMap.get("groupId")));

        Map<Integer, Double> billConsumerMap = (Map<Integer, Double>) billMap.get("consumerMap");
        Map<Integer, Double> consumerMap = new HashMap<Integer, Double>();
        Iterator consumerIterator = billConsumerMap.keySet().iterator();
        while (consumerIterator.hasNext()) {
           Integer key = Integer.parseInt(consumerIterator.next().toString());
           Double value = Double.parseDouble(String.valueOf(billConsumerMap.get(key.toString())));
           consumerMap.put(key, value);
        }

        Map<Integer, Double> billContributorMap = (Map<Integer, Double>) billMap.get("contributorMap");
        Map<Integer, Double> contributorMap = new HashMap<Integer, Double>();
        Iterator contributorIterator = billContributorMap.keySet().iterator();
        while (contributorIterator.hasNext()) {
            Integer key = Integer.parseInt(contributorIterator.next().toString());
            Double value = Double.parseDouble(String.valueOf(billContributorMap.get(key.toString())));
            contributorMap.put(key, value);
        }

        String billDesc = (String) billMap.get("billDesc");

        BillViewObject bill = billApplicationService.editBill(billId, userId, groupId, consumerMap, contributorMap, billDesc);
        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, bill);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/deleteBill/{billId}/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> deleteBill(@PathVariable("billId") String _billId, @PathVariable("userId") String billOwner, HttpServletRequest request) {

        Integer billId = Integer.parseInt(_billId);
        Integer userId = Integer.parseInt(billOwner);

        billApplicationService.deleteBill(billId, userId);
        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, "deleted");
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/retrieveBill/{billId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> retrieveBill(@PathVariable("billId") String _billId, HttpServletRequest request) {

        Integer billId = Integer.parseInt(_billId);

        BillViewObject bill = billApplicationService.retrieveBill(billId);
        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, bill);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/retrieveBillByGroup/{groupId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> retrieveBillByGroup(@PathVariable("groupId") String _groupId, HttpServletRequest request) {

        Integer groupId = Integer.parseInt(_groupId);

        List<BillViewObject> bill = billApplicationService.retrieveBillByGroup(groupId);

        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, bill);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/retrieveBillByConsumer/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> retrieveBillByConsumer(@PathVariable("userId") String _userId, HttpServletRequest request) {

        Integer userId = Integer.parseInt(_userId);

        List<BillViewObject> bill = billApplicationService.retrieveBillByConsumer(userId);
        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, bill);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/retrieveBillByContributor/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> retrieveBillByContributor(@PathVariable("userId") String _userId, HttpServletRequest request) {

        Integer userId = Integer.parseInt(_userId);

        List<BillViewObject> bill = billApplicationService.retrieveBillByContributor(userId);
        ResponseWrapper responseWrapper = new ResponseWrapper(request.getRequestURI(), HttpResponseCode.ok, bill);
        String responseJson = new Gson().toJson(responseWrapper);
        return new ResponseEntity<String>(responseJson,
                new HttpHeaders(), HttpStatus.OK);

    }
}
