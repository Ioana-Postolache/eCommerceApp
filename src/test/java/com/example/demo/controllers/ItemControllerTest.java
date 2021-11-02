package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {
    private ItemController itemController;
    private final ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp(){
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepository);
    }

    @Test
    public void testGetItemsHappyPath() {
        Item item = getItem();
        List <Item> createdItems = new ArrayList<>();
        createdItems.add(item);
        when(itemRepository.findAll()).thenReturn(createdItems);
        ResponseEntity<List<Item>> response = itemController.getItems();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        List<Item> items = response.getBody();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }

    @Test
    public void testGetItemsWhenThereAreNoItems() {
        ResponseEntity<List<Item>> response = itemController.getItems();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        List<Item> items = response.getBody();
        assertNotNull(items);
        assertEquals(0, items.size());
    }

    @Test
    public void testGetItemById() {
        Item item = getItem();
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(item));
        ResponseEntity<Item> response = itemController.getItemById(0L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        Item foundItem = response.getBody();
        assertEquals(item, foundItem);
    }

    @Test
    public void testGetItemByIdThatDoesntExist() {
        ResponseEntity<Item> response = itemController.getItemById(1L);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testGetItemByName() {
        Item item = getItem();
        List <Item> createdItems = new ArrayList<>();
        createdItems.add(item);
        when(itemRepository.findByName("Black Lamp")).thenReturn(createdItems);
        ResponseEntity<List<Item>> response = itemController.getItemsByName("Black Lamp");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        List<Item> items = response.getBody();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }

    @Test
    public void testGetItemByNameThatDoesntExist() {
        ResponseEntity<List<Item>> response = itemController.getItemsByName("Backpack");
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
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
