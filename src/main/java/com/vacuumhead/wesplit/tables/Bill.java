package com.vacuumhead.wesplit.tables;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Vinay
 * Date: 23/3/13
 * Time: 5:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BILL")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BILL_ID")
    private int billId = 1;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User billOwner;

    @ManyToOne(cascade = CascadeType.ALL)
    private Group associatedGroup;

    @ElementCollection(targetClass = Double.class)
    @MapKeyClass(User.class)
    @JoinTable(
            name = "CONTRIBUTOR_MAP",
            schema = "wesplit_ddb",
            joinColumns = @JoinColumn(name = "BILL_ID")
    )
    @Column(name = "CONTRIBUTOR_MAP")
    private Map<User, Double> contributorMap = new HashMap<User, Double>();

    @ElementCollection(targetClass = Double.class)
    @MapKeyClass(User.class)
    @JoinTable(
            name = "CONSUMER_MAP",
            schema = "wesplit_ddb",
            joinColumns = @JoinColumn(name = "BILL_ID")
    )
    @Column(name = "CONSUMER_MAP")
    private Map<User, Double> consumerMap = new HashMap<User, Double>();

    @Column(
            name = "BILL_DESC",
            columnDefinition = "text"
    )
    private String billDesc;

    public Bill() {
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Group getAssociatedGroup() {
        return associatedGroup;
    }

    public User getBillOwner() {
        return billOwner;
    }

    public void setBillOwner(User billOwner) {

        this.billOwner = billOwner;
    }

    public void setAssociatedGroup(Group associatedGroup) {
        this.associatedGroup = associatedGroup;
        associatedGroup.addBill(this);
    }

    public Map<User, Double> getContributorMap() {
        return contributorMap;
    }

    public void setContributorMap(Map<User, Double> contributorMap) {
        this.contributorMap = contributorMap;
    }

    public Map<User, Double> getConsumerMap() {
        return consumerMap;
    }

    public void setConsumerMap(Map<User, Double> consumerMap) {
        this.consumerMap = consumerMap;
    }

    public String getBillDesc() {
        return billDesc;
    }

    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }
}
