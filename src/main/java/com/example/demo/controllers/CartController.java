package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/addToCart")
    public ResponseEntity<Cart> addToCart(@RequestBody ModifyCartRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            log.error(String.format("error with adding to cart. User with user name %s not found.",
                    request.getUsername()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<Item> item = itemRepository.findById(request.getItemId());
        if (!item.isPresent()) {
            log.error(String.format("error with adding to cart. Item with id %s not found.",
                    request.getItemId()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Cart cart = user.getCart();
        IntStream.range(0, request.getQuantity())
                .forEach(i -> cart.addItem(item.get()));
        cartRepository.save(cart);
        log.info(String.format("Item %s added to %s's cart",
                request.getUsername(), request.getItemId()));
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<Cart> removeFromCart(@RequestBody ModifyCartRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            log.error(String.format("error with removing from cart. User with user name %s not found.",
                    request.getUsername()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<Item> item = itemRepository.findById(request.getItemId());
        if (!item.isPresent()) {
            log.error(String.format("error with removing from cart. Item with id %s not found.",
                    request.getItemId()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Cart cart = user.getCart();
        if (request.getQuantity() == 0) {
            List<Item> items = cart.getItems().stream()
                    .filter(cartItem -> !cartItem.getId().equals(item.get().getId())).collect(Collectors.toList());
            cart.setItems(items);
        } else {
            IntStream.range(0, request.getQuantity())
                    .forEach(i -> cart.removeItem(item.get()));
        }
        log.info(String.format("Quantity %s of item %s removed from %s's cart",
                request.getQuantity(), request.getUsername(), request.getItemId()));
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
    }

}
