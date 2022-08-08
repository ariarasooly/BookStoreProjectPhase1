package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;

import java.util.List;

public interface Searchable
{
    User findById(int id);

    List<User> findAll();
}
