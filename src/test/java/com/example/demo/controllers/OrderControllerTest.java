package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    private OrderController orderController;
    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);

    @Before
    public void setUp(){
        orderController = new OrderController();
        TestUtils.injectObjects(orderController, "orderRepository", orderRepository);
        TestUtils.injectObjects(orderController, "userRepository", userRepository);
    }

    @Test
    public void testSubmitOrder(){
        when(userRepository.findByUsername("test")).thenReturn(getUser());

        ResponseEntity<UserOrder> response = orderController.submit("test");
        UserOrder userOrder = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(userOrder);
        assertEquals(2, userOrder.getItems().size());
        assertEquals(getItems(), userOrder.getItems());
        assertEquals(BigDecimal.valueOf(2*15), userOrder.getTotal());
    }

    @Test
    public void testSubmitOrderWithoutUser(){
        ResponseEntity<UserOrder> response = orderController.submit("test");
        UserOrder userOrder = response.getBody();
        assertEquals(404, response.getStatusCodeValue());
        assertNull(userOrder);
    }

    @Test
    public void testGetOrdersForUser(){
        User user = getUser();
        when(userRepository.findByUsername("test")).thenReturn(user);
        when(orderRepository.findByUser(user)).thenReturn(getUserOrders());

        ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser("test");
        List<UserOrder> userOrders= response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(userOrders);
        assertEquals(2, userOrders.get(0).getItems().size());
        assertEquals(getItems(), userOrders.get(0).getItems());
    }

    @Test
    public void testGetOrdersForUserThatDoesntExist(){
        ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser("test");
        List<UserOrder> userOrders = response.getBody();
        assertEquals(404, response.getStatusCodeValue());
        assertNull(userOrders);
    }

    private User getUser() {
        User user = new User();
        user.setId(0L);
        user.setUsername("test");
        user.setPassword("password");
        Cart cart = new Cart();
        List<Item> items = getItems();
        items.forEach(cart::addItem);
        user.setCart(cart);
        return user;
    }

    private List<UserOrder> getUserOrders() {
        List<UserOrder> userOrders = new ArrayList<>();
        UserOrder userOrder = new UserOrder();
        userOrder.setItems(getItems());
        userOrders.add(userOrder);
        return userOrders;
    }

    private List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(getItem());
        items.add(getItem());
        return items;
    }

    private Item getItem(){
        Item item = new Item();
        item.setId(0L);
        item.setName("Black Lamp");
        item.setDescription("Black lamp with E27 light bulb");
        item.setPrice(BigDecimal.valueOf(15));
        return item;
    }
}
