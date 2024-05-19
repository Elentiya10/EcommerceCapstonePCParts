package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderItemFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    private static Order order1;



    @Test
    void a_setup(){
        OrderItem orderItem1 = new OrderItemFactory().buildOrderItem("01", "Graphic Card", 4000.00, "Palit GeForce RTX 3060"
                , 2);
        OrderItem orderItem2 = new OrderItemFactory().buildOrderItem("02", "Graphic Card", 12000.00, "Sapphire Pure AMD Radeon RX 8700"
                , 1);

        List<OrderItem> orderItem = new ArrayList<OrderItem>();
        {
            orderItem.add(orderItem1);
            orderItem.add(orderItem2);
        }
        Contact contact1 = ContactFactory.buildContact("ihassan@gmail.com","012 345 6789", "21 Jump Street", "Cape Town" ,"Western Cape" , "7540", "South Africa");
      Customer customer1 = CustomerFactory.buildCustomer("01","Isa", "Hassan", "123",contact1);
      order1 = OrderFactory.buildOrder("001",45000,customer1,orderItem);
        assertNotNull(order1);
    }
    @Test
    void b_create() {
        Order createdOrder1 = orderService.create(order1);
        assertNotNull(createdOrder1);
        System.out.println(createdOrder1);

    }

    @Test
    void c_read() {
        Order read = orderService.read(order1.getOrderId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void d_update() {
        Order newOrder = new Order.Builder().copy(order1).setOverallPrice(230000).build();
        Order updateOrder =orderService.update(newOrder);
        assertNotNull(updateOrder);
        System.out.println(updateOrder);
    }

    @Test
    void e_getAll() {
        System.out.println(orderService.getAll());
    }
}

