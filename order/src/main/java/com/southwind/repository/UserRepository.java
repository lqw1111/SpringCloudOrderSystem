package com.southwind.repository;

import com.southwind.entity.User;

import java.util.List;

public interface UserRepository {
    public User findById(long id);
}
