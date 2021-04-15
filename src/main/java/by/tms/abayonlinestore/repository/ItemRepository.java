package by.tms.abayonlinestore.repository;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {
    public Item findItemByItemName(String itemName);
    public Item findItemByItemId(long itemId);
    public void deleteItemByItemId(long itemId);
    public boolean existsByItemId(long itemId);
    @Query(value = "select n from Item n where n.itemCategory= :itemCategory")
    public List<Item> findItemByItemCategory(@Param("itemCategory") ItemCategory itemCategory);
    @Query(value = "select n from Item n where n.itemName= :itemName")
    public List<Item> findItemsByItemName(@Param("itemName")String itemName);

}
