package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>, Searchable, Persistable, Removable
{
    boolean validate(String username , String password);

    User findByUsername(String username);

}