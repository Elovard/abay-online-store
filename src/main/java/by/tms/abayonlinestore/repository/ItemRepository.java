package by.tms.abayonlinestore.repository;

import by.tms.abayonlinestore.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //    public Item findItemByItemCategoryList(ItemCategory itemCategory);
    public Item findItemByItemName(String itemName);
    public Item findItemByItemId(long itemId);
    public void deleteItemByItemId(long itemId);
    public boolean existsByItemId(long itemId);

}
