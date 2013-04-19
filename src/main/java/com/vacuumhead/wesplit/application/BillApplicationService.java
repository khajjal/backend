package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.ViewObject.BillViewObject;
import com.vacuumhead.wesplit.dao.IBillDao;
import com.vacuumhead.wesplit.dao.IGroupDao;
import com.vacuumhead.wesplit.dao.IUserDao;
import com.vacuumhead.wesplit.tables.Bill;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Vinay
 * Date: 14/4/13
 * Time: 5:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class BillApplicationService implements IBillApplicationService {
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private IGroupApplicationService groupApplicationService;
    private IUserServiceApplicationLogic userServiceApplicationLogic;
    private IBillDao billDao;
    private IUserDao userDao;
    private IGroupDao groupDao;

    public BillApplicationService(IGroupApplicationService groupApplicationService, IUserServiceApplicationLogic userServiceApplicationLogic, IBillDao billDao, IUserDao userDao, IGroupDao groupDao) {
        this.groupApplicationService = groupApplicationService;
        this.userServiceApplicationLogic = userServiceApplicationLogic;
        this.billDao = billDao;
        this.userDao = userDao;
        this.groupDao = groupDao;
    }

    public BillViewObject createBill(Integer userId, Integer groupId, Map<Integer, Double> consumerMap, Map<Integer, Double> contributorMap, String billDesc) {
        EntityManager em = emf.createEntityManager();
        Bill bill;
        try {
            em.getTransaction().begin();
            Group group = groupDao.retrieveGroupById(em, groupId);
            if (group == null)
                throw new RuntimeException();  //more specific required
            Map<User, Double> consumerMapByUser = new HashMap<User, Double>();
            Map<User, Double> contributorMapByUser = new HashMap<User, Double>();
            for (int id : consumerMap.keySet()) {
                User retrievedUser = userDao.retrieveUserById(em, id);
                if (retrievedUser == null)
                    throw new RuntimeException();
                consumerMapByUser.put(retrievedUser, consumerMap.get(id));
            }
            for (int id : contributorMap.keySet()) {
                User retrievedUser = userDao.retrieveUserById(em, id);
                if (retrievedUser == null)
                    throw new RuntimeException();
                contributorMapByUser.put(retrievedUser, contributorMap.get(id));
            }
            User billOwner = userDao.retrieveUserById(em, userId);
            if (billOwner == null) {
                throw new RuntimeException();
            }
            bill = new Bill();
            bill.setBillDesc(billDesc);
            bill.setBillOwner(billOwner);
            bill.setAssociatedGroup(group);
            bill.setConsumerMap(consumerMapByUser);
            bill.setContributorMap(contributorMapByUser);
            billDao.createBill(em, bill);
        } finally {
            em.getTransaction().commit();
        }
        return new BillViewObject(bill);
    }

    public BillViewObject editBill(int billId, int userId, int groupId, Map<Integer, Double> consumerMap, Map<Integer, Double> contributorMap, String billDesc) {
        EntityManager em = emf.createEntityManager();
        Bill bill;
        try {
            em.getTransaction().begin();
            bill = billDao.retrieveBill(em, billId);
            billDao.updateBill(em, bill);

        } finally {
            em.getTransaction().commit();
        }
        return new BillViewObject(bill);
    }

    public void deleteBill(int billId, int userId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Bill bill = billDao.retrieveBill(em, billId);
            User user = userDao.retrieveUserById(em, userId);
            if(bill.getContributorMap().containsKey(user)) {
                billDao.deleteBill(em, bill);
            }
        } finally {
            em.getTransaction().commit();
        }
    }

    public BillViewObject retrieveBill(int billId) {
        EntityManager em = emf.createEntityManager();
        Bill bill;
        try {
            em.getTransaction().begin();
            bill = billDao.retrieveBill(em, billId);
        } finally {
            em.getTransaction().commit();
        }
        return new BillViewObject(bill);
    }

    public List<BillViewObject> retrieveBillByGroup(Integer groupId) {
        EntityManager em = emf.createEntityManager();
        List<BillViewObject> billViewObjectList = new ArrayList<BillViewObject>();
        try {
            em.getTransaction().begin();
            Group group = groupDao.retrieveGroupById(em, groupId);
            for(Bill bill : group.getBillList()) {
                billViewObjectList.add(new BillViewObject(bill));
            }
        } finally {
            em.getTransaction().commit();
        }
        return billViewObjectList;
    }

    public List<BillViewObject> retrieveBillByConsumer(Integer userId) {
        EntityManager em = emf.createEntityManager();
        List<BillViewObject> billViewObjectList = new ArrayList<BillViewObject>();
        try {
            em.getTransaction().begin();
            User user = userDao.retrieveUserById(em, userId);
            for(Group group : user.getGroupMemberList()) {
                for(Bill bill : group.getBillList()) {
                    if(bill.getConsumerMap().containsKey(user)) {
                        billViewObjectList.add(new BillViewObject(bill));
                    }
                }
            }
        } finally {
            em.getTransaction().commit();
        }
        return billViewObjectList;
    }

    public List<BillViewObject> retrieveBillByContributor(Integer userId) {
        EntityManager em = emf.createEntityManager();
        List<BillViewObject> billViewObjectList = new ArrayList<BillViewObject>();
        try {
            em.getTransaction().begin();
            User user = userDao.retrieveUserById(em, userId);
            for(Group group : user.getGroupMemberList()) {
                for(Bill bill : group.getBillList()) {
                    if(bill.getContributorMap().containsKey(user)) {
                        billViewObjectList.add(new BillViewObject(bill));
                    }
                }
            }
        } finally {
            em.getTransaction().commit();
        }
        return billViewObjectList;
    }
}
