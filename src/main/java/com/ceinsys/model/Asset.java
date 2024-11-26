package com.ceinsys.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asset" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String category;

    @NotNull
    private String value;

    @NotNull
    private String subCategory;

    private  String manufacturer;

    private String location;

    private Long quantity;

    private String mpn;

    private String sap_no;

    private String asset_package;
}
