package com.southwind.repository;

import com.southwind.entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);
    public List<Order> findAllByUid(int index, int limit, int uid);
    public int countByUid(int uid);
    public List<Order> findAllByState(int index, int limit, int state);
    public void updateState(int id);
    public int countByState();
}
