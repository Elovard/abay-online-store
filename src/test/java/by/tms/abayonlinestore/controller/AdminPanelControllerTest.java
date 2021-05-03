package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.entity.ItemCategory;
import by.tms.abayonlinestore.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AdminPanelControllerTest {

    private final Long itemId = 1L;
    private final String itemName = "testName";
    private final String itemDesc = "testDesc";
    private final Float itemPrice = 12.99f;
    private final String picLink = "test.png";
    private final ItemCategory itemCategory = ItemCategory.TOY;

    private Item item;

    @Autowired
    private ItemService itemService;

    @BeforeEach
    void settingUp(){
        item = new Item();
        item.setItemName(itemName);
        item.setItemDesc(itemDesc);
        item.setItemPrice(itemPrice);
        item.setPicLink(picLink);
        item.setItemCategory(itemCategory);
    }

    @Test
    void addItemByAdmin() {
        itemService.addItem(item);
        Mockito.when(itemService.isItemExistsById(1)).thenReturn(true);
    }

    @Test
    void removeItemByAdmin() {
        long itemId = 1;
        itemService.removeItemById(itemId);
        assertFalse(itemService.isItemExistsById(itemId));
    }
}