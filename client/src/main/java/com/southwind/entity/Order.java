package com.southwind.entity;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    private int id;
    private User user;
    private Admin admin;
    private Menu menu;
    private Date date;
    private int state;
}
