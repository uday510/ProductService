package com.app.productservice.representingInheritance.joinedTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="jt_user")
public class User {
    private String name;
    private String email;
    private String password;
    @Id
    private Long id;
}
