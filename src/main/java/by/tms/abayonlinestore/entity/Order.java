package by.tms.abayonlinestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @NotBlank
    @Size(min = 2, message = "Typo in name!")
    private String orderedBy;

    @NotBlank
    @Size(min = 10, message = "Enter correct mobile number!")
    private String mobilePhone;

    @NotBlank
    @Size(min = 3, message = "Typo in city!")
    private String city;

    @NotBlank
    @Size(min = 10, message = "Check your address!")
    private String address;

    @NotBlank
    @CreditCardNumber(message = "Incorrect credit card number!")
    private String creditCardNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private LocalDateTime createdAt;

    private OrderStatus orderStatus;
}
