package com.app.productservice.representingInheritance.tablePerClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TA extends User {
    private int numberOfSession;
    private double avgRating;

}
