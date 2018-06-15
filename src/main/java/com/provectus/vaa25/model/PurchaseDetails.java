package com.provectus.vaa25.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseDetails {

    private Long bookId;
    private String firstName;
    private String lastName;
    private String address;
    private Integer quantity;

}
