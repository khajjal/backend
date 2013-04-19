package com.vacuumhead.wesplit.ViewObject;

import com.vacuumhead.wesplit.tables.Bill;
import com.vacuumhead.wesplit.tables.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 19/04/13
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class BillViewObject {

    private int billId;
    private Integer billOwner;
    private Integer associatedGroup;
    private Map<Integer, Double> contributorMap = new HashMap<Integer, Double>();
    private Map<Integer, Double> consumerMap = new HashMap<Integer, Double>();

    public BillViewObject(Bill bill) {
        this.billId = bill.getBillId();
        this.billOwner = bill.getBillOwner().getUserrId();
        this.associatedGroup = bill.getAssociatedGroup().getGroupId();
        for(Map.Entry<User, Double> contributor : bill.getContributorMap().entrySet()) {
            this.contributorMap.put(contributor.getKey().getUserrId(), contributor.getValue());
        }
        for(Map.Entry<User, Double> consumer : bill.getConsumerMap().entrySet()) {
            this.consumerMap.put(consumer.getKey().getUserrId(), consumer.getValue());
        }
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Integer getBillOwner() {
        return billOwner;
    }

    public void setBillOwner(Integer billOwner) {
        this.billOwner = billOwner;
    }

    public Integer getAssociatedGroup() {
        return associatedGroup;
    }

    public void setAssociatedGroup(Integer associatedGroup) {
        this.associatedGroup = associatedGroup;
    }

    public Map<Integer, Double> getContributorMap() {
        return contributorMap;
    }

    public void setContributorMap(Map<Integer, Double> contributorMap) {
        this.contributorMap = contributorMap;
    }

    public Map<Integer, Double> getConsumerMap() {
        return consumerMap;
    }

    public void setConsumerMap(Map<Integer, Double> consumerMap) {
        this.consumerMap = consumerMap;
    }
}
