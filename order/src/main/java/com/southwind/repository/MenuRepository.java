package com.southwind.repository;

import com.southwind.entity.Menu;

import java.util.List;

public interface MenuRepository {
    public Menu findById(long id);
}
