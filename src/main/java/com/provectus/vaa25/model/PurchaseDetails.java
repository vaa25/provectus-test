package com.provectus.vaa25.model;

import lombok.Data;

@Data
public class PurchaseDetails {

    private Long bookId;
    private String firstName;
    private String lastName;
    private String address;
    private Integer quantity;

}
