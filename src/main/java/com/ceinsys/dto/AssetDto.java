package com.ceinsys.dto;

import lombok.Data;

@Data
public class AssetDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String subCategory;
    private String value;
    private String manufacturer;
    private Long quantity;
    private String location;
    private String asset_package;
    private String sap_no;
    private String mpn;

}
