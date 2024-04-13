package com.app.productservice.representingInheritance.tablePerClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor extends User {
    private double avgMentorRating;
}
