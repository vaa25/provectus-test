package com.provectus.vaa25.entity;

import com.provectus.vaa25.model.PurchaseDetails;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "purchase")
public final class Purchase {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Book book;
    private String address;
    private Integer quantity;

    public Purchase(final PurchaseDetails purchaseDetails){
        this.book = new Book(purchaseDetails.getBookId());
        firstName = purchaseDetails.getFirstName();
        lastName = purchaseDetails.getLastName();
        address = purchaseDetails.getAddress();
        quantity = purchaseDetails.getQuantity();
    }
}
