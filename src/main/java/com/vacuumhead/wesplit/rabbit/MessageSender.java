package com.vacuumhead.wesplit.rabbit;

import com.google.gson.Gson;
import com.vacuumhead.wesplit.ViewObject.BillViewObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Vinay
 * Date: 27/4/13
 * Time: 4:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(Object retVal){

        String messageToBeSent = "Test message";
        if (retVal.getClass()!=BillViewObject.class){
            System.out.println("wtf!");
        }
       rabbitTemplate.convertAndSend("exchange","topic1", new Gson().toJson((BillViewObject)retVal));

        System.out.println("Message '" +  new Gson().toJson((BillViewObject)retVal) + "' sent.");
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
