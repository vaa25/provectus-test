package com.provectus.vaa25.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PurchaseDetails {

    private Long bookId;
    private String firstName;
    private String lastName;
    private String address;
    private Integer quantity;

}
