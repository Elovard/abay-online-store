package by.tms.abayonlinestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;
    private String itemDesc;
    private float itemPrice;

    private String picLink;

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

//    @ElementCollection(targetClass = ItemCategory.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "item_category", joinColumns = @JoinColumn(name = "items"))
//    @Enumerated(EnumType.STRING)
//    private Set<ItemCategory> itemCategoryList;

}
