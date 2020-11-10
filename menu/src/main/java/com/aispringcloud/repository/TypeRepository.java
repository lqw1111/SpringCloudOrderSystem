package com.aispringcloud.repository;

import com.aispringcloud.entity.Type;
import java.util.List;

public interface TypeRepository {
    public Type findById();
    public List<Type> findAll();
}
