package com.ceinsys.dto;

import com.ceinsys.model.Item;
import lombok.Data;

import java.util.Date;

@Data
public class ItemRequestDto {
    private Long id;
    private Item item;
    private int quantityRequest;
    private boolean isApproved;
    private Date date;
    private  String userName;
    private String projectName;
    private String remark;
}
