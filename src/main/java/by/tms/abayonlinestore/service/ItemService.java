package by.tms.abayonlinestore.service;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.entity.ItemCategory;
import by.tms.abayonlinestore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void addItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item findItemById(long itemId){
        return itemRepository.findItemByItemId(itemId);
    }

    public Item findItemByName(String itemName){
        return itemRepository.findItemByItemName(itemName);
    }

    public void removeItemById(long itemId){
        itemRepository.deleteItemByItemId(itemId);
    }

    public boolean isItemExistsById(long itemId){
        if (itemRepository.existsByItemId(itemId)){
            return true;
        } else{
            return false;
        }
    }

    public List<Item> searchItemByCategory(ItemCategory itemCategory){
      return itemRepository.findItemByItemCategory(itemCategory);
    }

    public List<Item> searchItemsByName(String itemName){
        return itemRepository.findItemsByItemName(itemName);
    }

}
