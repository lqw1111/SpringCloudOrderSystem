package com.southwind.repository;

import com.southwind.entity.Type;

import java.util.List;

public interface TypeRepository {
    public Type findById();
    public List<Type> findAll();
}
