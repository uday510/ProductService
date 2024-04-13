package com.app.productservice.representingInheritance.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_ta")
@PrimaryKeyJoinColumn(name = "user_id") // override the default column name
public class TA extends User {
    private int numberOfSession;
    private double avgRating;

}
