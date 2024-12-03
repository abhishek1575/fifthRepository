package com.ceinsys.dto;

import lombok.Data;

@Data
public class ItemDto {
    private Long id;

    private String name;

    private String category;

    private String subCategory;

    private String value;

    private String description;

    private String manufacturer;

    private String package_box;

    private String location;

    private String mpn;

    private Long sap_no;

    private Long stock;

    private boolean status;
}
