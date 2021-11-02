package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartControllerTest {
    private CartController cartController;
    private final CartRepository cartRepository = mock(CartRepository.class);
    private final ItemRepository itemRepository = mock(ItemRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);

    @Before
    public void setUp(){
        cartController = new CartController();
        TestUtils.injectObjects(cartController, "cartRepository", cartRepository);
        TestUtils.injectObjects(cartController, "itemRepository", itemRepository);
        TestUtils.injectObjects(cartController, "userRepository", userRepository);
    }

    @Test
    public void testAddToCart(){
        when(userRepository.findByUsername("test")).thenReturn(getUser());
        when(itemRepository.findById(0L)).thenReturn(getItem());

        ModifyCartRequest modifyCartRequest = getModifyCartRequest();

        List<Item> items = new ArrayList<>();
        items.add(getItem().get());
        items.add(getItem().get());
        ResponseEntity<Cart> response = cartController.addToCart(modifyCartRequest);

        Cart cart = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(cart);
        assertEquals(items, cart.getItems());
        assertEquals(BigDecimal.valueOf(2*15), cart.getTotal());
    }

    @Test
    public void testAddToCartNoUserNameFound(){
        when(userRepository.findByUsername("test")).thenReturn(getUser());

        ModifyCartRequest modifyCartRequest = getModifyCartRequest();
        ResponseEntity<Cart> response = cartController.addToCart(modifyCartRequest);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testAddToCartNoItemFound(){
        when(itemRepository.findById(0L)).thenReturn(getItem());

        ModifyCartRequest modifyCartRequest = getModifyCartRequest();
        ResponseEntity<Cart> response = cartController.addToCart(modifyCartRequest);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testRemoveFromCart(){
        List<Item> items = new ArrayList<>();
        items.add(getItem().get());
        items.add(getItem().get());
        when(userRepository.findByUsername("test")).thenReturn(getUser(items));
        when(itemRepository.findById(0L)).thenReturn(getItem());

        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(getItem().get().getId());
        modifyCartRequest.setQuantity(1);
        modifyCartRequest.setUsername("test");
        ResponseEntity<Cart> response = cartController.removeFromCart(modifyCartRequest);

        Cart cart = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(cart);
        assertEquals(items.get(0), cart.getItems().get(0));
        assertEquals(1, cart.getItems().size());
        assertEquals(BigDecimal.valueOf(15), cart.getTotal());
    }

    @Test
    public void testRemoveAllFromCart(){
        List<Item> items = new ArrayList<>();
        items.add(getItem().get());
        items.add(getItem().get());
        when(userRepository.findByUsername("test")).thenReturn(getUser(items));
        when(itemRepository.findById(0L)).thenReturn(getItem());

        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(getItem().get().getId());
        modifyCartRequest.setQuantity(0);
        modifyCartRequest.setUsername("test");
        ResponseEntity<Cart> response = cartController.removeFromCart(modifyCartRequest);

        Cart cart = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(cart);
        assertEquals(0, cart.getItems().size());
    }

    private ModifyCartRequest getModifyCartRequest() {
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(getItem().get().getId());
        modifyCartRequest.setQuantity(2);
        modifyCartRequest.setUsername("test");
        return modifyCartRequest;
    }

    private User getUser() {
        User user = new User();
        user.setId(0L);
        user.setUsername("test");
        user.setPassword("password");
        Cart cart = new Cart();
        user.setCart(cart);
        return user;
    }

    private User getUser(List<Item> items) {
        User user = new User();
        user.setId(0L);
        user.setUsername("test");
        user.setPassword("password");
        Cart cart = new Cart();
        items.forEach(cart::addItem);
        user.setCart(cart);
        return user;
    }

    private Optional<Item> getItem(){
        Item item = new Item();
        item.setId(0L);
        item.setName("Black Lamp");
        item.setDescription("Black lamp with E27 light bulb");
        item.setPrice(BigDecimal.valueOf(15));
        return Optional.of(item);
    }
}
