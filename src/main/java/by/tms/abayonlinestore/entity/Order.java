package by.tms.abayonlinestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Size(min = 2, message = "Minimum 2 symbols!")
    private String orderedBy;

    @NotBlank
    @Pattern(regexp = "[+]{1}[0-9]{12}", message = "Check mobile number!")
    private String mobilePhone;

    @NotBlank
    @Size(min = 3, message = "Check city!")
    private String city;

    @NotBlank
    @Size(min = 10, message = "Check your address!")
    private String address;

    @NotBlank
    @Size(min = 16, max = 16, message = "Incorrect credit card number!")
    private String creditCardNumber;

    @NotNull
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
    @NotNull
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private LocalDateTime createdAt;

    private OrderStatus orderStatus;
}
