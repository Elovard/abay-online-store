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
    private long itemId;

    private String itemName;
    private String itemDesc;
    private float itemPrice;

    private String picLink;

//    @ElementCollection
//    @LazyCollection(value = LazyCollectionOption.FALSE)
//    private List<ItemCategory> itemCategoryList;

}
