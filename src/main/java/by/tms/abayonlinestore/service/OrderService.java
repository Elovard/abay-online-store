package by.tms.abayonlinestore.service;

import by.tms.abayonlinestore.entity.Order;
import by.tms.abayonlinestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(Order order){
        orderRepository.save(order);
    }

}
