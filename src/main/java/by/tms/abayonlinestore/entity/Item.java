package by.tms.abayonlinestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
@Indexed
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @FullTextField
    private String itemName;

    @FullTextField
    private String itemDesc;

    @GenericField
    private float itemPrice;

    private String picLink;

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

}
