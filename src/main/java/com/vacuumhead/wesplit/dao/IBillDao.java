package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.tables.Bill;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: Vinay
 * Date: 14/4/13
 * Time: 6:13 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IBillDao {
    void createBill(EntityManager entityManager,Bill Bill);
    void retrieveBill(EntityManager entityManager,int billId);
    void deleteBill(EntityManager entityManager,Bill bill);
    void updateBill(EntityManager entityManager,Bill bill);
}
