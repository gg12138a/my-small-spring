package org.example.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderService {

    private int plainVal;

    private OrderDao orderDao;

    public void itWorks() {
        System.out.println("OrderService::itWorks(), " + plainVal);
        orderDao.itWorks();
    }
}
