package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.BillCodes;
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
    BillCodes createBill(EntityManager entityManager, Bill bill);
    Bill retrieveBill(EntityManager entityManager, int billId);
    BillCodes deleteBill(EntityManager entityManager, Bill bill);
    BillCodes updateBill(EntityManager entityManager, Bill bill);
}
