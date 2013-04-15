package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.BillCodes;
import com.vacuumhead.wesplit.tables.Bill;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 14/04/13
 * Time: 8:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class BillDao implements IBillDao {
    public BillCodes createBill(EntityManager entityManager, Bill bill) {
        entityManager.persist(bill);
        return BillCodes.BILL_CREATED;
    }

    public Bill retrieveBill(EntityManager entityManager, int billId) {
        TypedQuery<Bill> query = entityManager.createQuery("select b from Bill b where billId = :billId", Bill.class);
        query.setParameter("billId", billId);
        List<Bill> resultSet =  query.getResultList();
        return resultSet.size() > 0 ? resultSet.get(0) : null ;
    }

    public BillCodes deleteBill(EntityManager entityManager, Bill bill) {
        entityManager.remove(bill);
        return BillCodes.BILL_DELETED;
    }

    public BillCodes updateBill(EntityManager entityManager, Bill bill) {
        entityManager.merge(bill);
        return BillCodes.BILL_UPDATED;
    }
}
