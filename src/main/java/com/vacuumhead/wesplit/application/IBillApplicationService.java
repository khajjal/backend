package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.ViewObject.BillViewObject;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Vinay
 * Date: 14/4/13
 * Time: 5:57 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IBillApplicationService {
    BillViewObject createBill(Integer userId, Integer groupId, Map<Integer, Double> consumerMap, Map<Integer, Double> contributorMap, String billDesc);
    BillViewObject editBill(int billId, int userId, int groupId,Map<Integer,Double> consumerMap,Map<Integer,Double> contributorMap,String billDesc);
    void deleteBill(int billId, int userId);
    BillViewObject retrieveBill(int billId);
    List<BillViewObject> retrieveBillByGroup(Integer groupId);
    List<BillViewObject> retrieveBillByConsumer(Integer userId);
    List<BillViewObject> retrieveBillByContributor(Integer userId);
}
