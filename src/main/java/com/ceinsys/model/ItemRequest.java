package com.ceinsys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Defines the relationship
    @JoinColumn(name = "item_id", nullable = false) // Maps the foreign key column
    private Item item;

    private int quantityRequested;

    private boolean isApproved;

    private String userName;

    private String projectName;

    private String remark;

    @Column(name="requestDate")
    private LocalDateTime localDateTime;

    @PrePersist
    public void onCreate(){
        this.localDateTime = LocalDateTime.now();
    }
}

