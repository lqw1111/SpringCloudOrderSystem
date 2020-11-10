package com.aispringcloud.entity;

import lombok.Data;

@Data
public class Menu {
    private int id;
    private String name;
    private double price;
    private String flavor;
    private Type type;
}
