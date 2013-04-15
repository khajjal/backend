package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.BillCodes;
import com.vacuumhead.wesplit.dao.IBillDao;
import com.vacuumhead.wesplit.tables.Bill;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
public class BillApplicationService implements IBillApplicationService{
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private IGroupApplicationService groupApplicationService;
    private IUserServiceApplicationLogic userServiceApplicationLogic;
    private IBillDao billDao;

    public BillApplicationService(IGroupApplicationService groupApplicationService, IUserServiceApplicationLogic userServiceApplicationLogic, IBillDao billDao) {
        this.groupApplicationService = groupApplicationService;
        this.userServiceApplicationLogic = userServiceApplicationLogic;
        this.billDao = billDao;
    }

    public BillCodes createBill(Integer userId, Integer groupId, Map<Integer, Double> consumerMap, Map<Integer, Double> contributorMap, String billDesc) {
        EntityManager em=emf.createEntityManager();
        BillCodes billCode;
        try{
            em.getTransaction().begin();
            Group group=groupApplicationService.retrieveGroupById(groupId);
            if(group==null)
                throw new RuntimeException();  //more specific required
            Map<User,Double> consumerMapByUser=new HashMap<User, Double>();
            Map<User,Double> contributorMapByUser=new HashMap<User, Double>();
            for(int id:consumerMap.keySet()){
                User retrievedUser=userServiceApplicationLogic.retrieveUser(id).getUserEmbedded();
                if(retrievedUser==null)
                    throw new RuntimeException();
                consumerMapByUser.put(retrievedUser,consumerMap.get(id));
            }
            for (int id:contributorMap.keySet()){
                User retrievedUser=userServiceApplicationLogic.retrieveUser(id).getUserEmbedded();
                if(retrievedUser==null)
                    throw new RuntimeException();
                contributorMapByUser.put(retrievedUser,contributorMap.get(id));
            }
            User billOwner = userServiceApplicationLogic.retrieveUser(userId).getUserEmbedded();
            if(billOwner == null) {
                throw new RuntimeException();
            }
            Bill bill=new Bill();
            bill.setBillOwner(billOwner);
            bill.setAssociatedGroup(group);
            bill.setConsumerMap(consumerMapByUser);
            bill.setContributorMap(contributorMapByUser);
            billCode = billDao.createBill(em,bill);
        }finally {
            em.getTransaction().commit();
        }
        return billCode;
    }

    public void editBill(int billId, int groupId, Map<Integer, Double> consumerMap, Map<Integer, Double> contributorMap,String billDesc) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteBill(int billId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Bill retrieveBill(int billId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Bill> retrieveBills(Group group) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Bill> retrieveBillByConsumer(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Bill> retrieveBillByContributor(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
