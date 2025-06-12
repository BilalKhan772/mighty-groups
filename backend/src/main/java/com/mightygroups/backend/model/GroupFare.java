package com.mightygroups.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "groupFares")
public class GroupFare {

    @Id
    private String id;

    private String airline;
    private String from;
    private String to;
    private Date departureDate;
    private int seatsAvailable;
    private double price;
    private String uploadedBy; // B2B user's ID
}
