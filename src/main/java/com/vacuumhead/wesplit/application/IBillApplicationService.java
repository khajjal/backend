package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.tables.Bill;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

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
    void createBill(int groupId,Map<Integer,Double> consumerMap,Map<Integer,Double> contributorMap,String billDesc);
    void editBill(int billId,int groupId,Map<Integer,Double> consumerMap,Map<Integer,Double> contributorMap,String billDesc);
    void deleteBill(int billId);
    Bill retrieveBill(int billId);
    List<Bill> retrieveBills(Group group);
    List<Bill> retrieveBillByConsumer(User user);
    List<Bill> retrieveBillByContributor(User user);
}
