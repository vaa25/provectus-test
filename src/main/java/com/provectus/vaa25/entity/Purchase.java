package com.provectus.vaa25.entity;

import com.provectus.vaa25.model.OrderDetails;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "purchase")
public final class Purchase {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(targetEntity = Book.class)
    private Book book;
    private String address;
    private Integer quantity;

    public Purchase(final Book book, final OrderDetails orderDetails){
        this.book = book;
        firstName = orderDetails.getFirstName();
        lastName = orderDetails.getLastName();
        address = orderDetails.getAddress();
        quantity = orderDetails.getQuantity();
    }
}
