package com.vacuumhead.wesplit.rest;

import com.vacuumhead.wesplit.application.IBillApplicationService;
import com.vacuumhead.wesplit.constants.BillCodes;
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
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 14/04/13
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/group")
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

        Map<Integer, Double> billContributerMap = (Map<Integer, Double>) billMap.get("contributorMap");
        Map<Integer, Double> contributorMap = new HashMap<Integer, Double>();
        Iterator contributorIterator = billContributerMap.keySet().iterator();
        while (contributorIterator.hasNext()) {
            Integer key = Integer.parseInt(contributorIterator.next().toString());
            Double value = Double.parseDouble(String.valueOf(billContributerMap.get(key.toString())));
            contributorMap.put(key, value);
        }

        String billDesc = (String) billMap.get("billDesc");
        BillCodes billCode = billApplicationService.createBill(userId, groupId, consumerMap, contributorMap, billDesc);

        return new ResponseEntity<String>(billCode.toString(),
                new HttpHeaders(), HttpStatus.OK);

    }

}
