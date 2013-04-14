package com.vacuumhead.wesplit.application;

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

    @Override
    public void createBill(int groupId, Map<Integer, Double> consumerMap, Map<Integer, Double> contributorMap,String billDesc) {
        EntityManager em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Group group=groupApplicationService.retrieveGroupById(groupId);
            if(group==null)
                throw new RuntimeException();  //more specific required
            Map<User,Double> consumerMapByUser=new HashMap<User, Double>();
            Map<User,Double> contributorMapByUser=new HashMap<User, Double>();
            for(int id:consumerMap.keySet()){
                User retrievedUser=userServiceApplicationLogic.retrieveUser(id);
                if(retrievedUser==null)
                    throw new RuntimeException();
                consumerMapByUser.put(retrievedUser,consumerMap.get(id));
            }
            for (int id:contributorMap.keySet()){
                User retrievedUser=userServiceApplicationLogic.retrieveUser(id);
                if(retrievedUser==null)
                    throw new RuntimeException();
                contributorMapByUser.put(retrievedUser,contributorMap.get(id));
            }
            Bill bill=new Bill();
            bill.setAssociatedGroup(group);
            bill.setConsumerMap(consumerMapByUser);
            bill.setContributorMap(contributorMapByUser);
            billDao.createBill(em,bill);
        }finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public void editBill(int billId, int groupId, Map<Integer, Double> consumerMap, Map<Integer, Double> contributorMap,String billDesc) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteBill(int billId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Bill retrieveBill(int billId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Bill> retrieveBills(Group group) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Bill> retrieveBillByConsumer(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Bill> retrieveBillByContributor(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
