package by.tms.abayonlinestore.service;

import by.tms.abayonlinestore.entity.Item;
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


}
