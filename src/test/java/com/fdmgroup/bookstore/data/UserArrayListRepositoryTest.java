package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.data.UserArrayListRepository;
import com.fdmgroup.bookstore.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserArrayListRepositoryTest
{

    UserArrayListRepository repository;

    @BeforeEach
    void before()
    {
        final List<User> users = new ArrayList<>();
        for (int i = 1; i <= 100; i++)
        {
            users.add(
                    new User(
                            i ,
                            "name_" + i ,
                            "family_" + i ,
                            "username_" + i ,
                            "12345_" + i ,
                            "email_" + i + "@company.com" ,
                            null
                    )
            );
        }

        repository = new UserArrayListRepository(users);
        repository.setGenerateId(users.size());
    }

    @Test
    void save()
    {
        final User newUser = new User(
                "new_name" ,
                "new_family" ,
                "new_username" ,
                "new_12345" ,
                "new_email@company.com" ,
                null
        );

        final User save = repository.save(newUser);

        assertNotNull(save);
        assertEquals(save.getUserId() , repository.getGenerateId());

        int id = 10;

        // Modify
        final User modifyUser = repository.findById(id);
        assertNotNull(modifyUser);

        modifyUser.setPassword("this is a new password");

        User user = repository.save(modifyUser);
        assertNotNull(user);
        assertEquals(user.getPassword() , "this is a new password");


        user = repository.findById(id);
        assertNotNull(user);
        assertEquals(user.getPassword() , "this is a new password");
    }

    @Test
    void saveAll()
    {
        final List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            final User newUser = new User(
                    i ,
                    "new_name_" + i ,
                    "new_family_" + i ,
                    "new_username_" + i ,
                    "new_12345_" + i ,
                    "new_email_" + i + "@company.com" ,
                    null
            );
            users.add(newUser);
        }

        final List<User> newUsers = repository.saveAll(users);
        assertNotNull(newUsers);
    }

    @Test
    void findById()
    {
        int id = 2;

        User byId = repository.findById(id);

        assertNotNull(byId);
        assertEquals(byId.getUserId() , id);
        assertEquals(byId.getFirstName() , "name_" + id);
        assertEquals(byId.getUsername() , "username_" + id);

        id = 0;
        byId = repository.findById(id);
        assertNull(byId);
    }

    @Test
    void validate()
    {
        int id = 2;
        boolean validate = repository.validate("username_" + id , "12345_" + id);
        assertTrue(validate);

        id = 0;
        validate = repository.validate("username_" + id , "12345_" + id);
        assertFalse(validate);
    }

    @Test
    void findByUsername()
    {
        int id = 2;
        User byUsername = repository.findByUsername("username_" + id);
        assertNotNull(byUsername);
        assertEquals(byUsername.getUserId() , id);

        id = 0;
        byUsername = repository.findByUsername("username_" + id);
        assertNull(byUsername);
    }

    @Test
    void findAll()
    {
        final List<User> all = repository.findAll();
        assertNotNull(all);
    }

    @Test
    void findAllById()
    {
        Iterable<Integer> integers = Arrays.asList(
                2 , 4 , 8 , 4 , 100 , 1
        );
        List<User> allById = repository.findAllById(integers);
        assertNotNull(allById);
        assertEquals(allById.size() , 6);

        integers = Collections.singletonList(
                0
        );
        allById = repository.findAllById(integers);
        assertNotNull(allById);
        assertEquals(allById.size() , 0);
    }

    @Test
    void count()
    {
        final long count = repository.count();
        assertEquals(count , 100);
    }

    @Test
    void deleteById()
    {
        int id = 2;
        repository.deleteById(id);
    }

    @Test
    void delete()
    {
        int id = 2;

        final User byId = repository.findById(id);
        assertNotNull(byId);
        assertEquals(byId.getUserId() , id);

        repository.delete(byId);
    }

    @Test
    void deleteAllById()
    {
        Iterable<Integer> integers = Arrays.asList(
                2 , 4 , 8 , 4 , 100 , 1
        );
        repository.deleteAllById(integers);

        integers = Collections.singletonList(
                0
        );
        repository.deleteAllById(integers);
    }

    @Test
    void deleteAll()
    {
        repository.deleteAll();

        long count = repository.count();
        assertEquals(count , 0);
    }


    @Test
    void existsById()
    {
        int id = 2;
        boolean exists = repository.existsById(id);
        assertTrue(exists);

        id = 0;
        exists = repository.existsById(id);
        assertFalse(exists);
    }

    @Test
    void getOne()
    {
        int id = 2;
        User one = repository.getOne(id);
        assertNotNull(one);
        assertEquals(one.getUserId() , id);
        assertEquals(one.getFirstName() , "name_" + id);
        assertEquals(one.getUsername() , "username_" + id);

        id = 0;
        one = repository.getOne(id);
        assertNull(one);
    }

    @Test
    void getById()
    {
        int id = 2;
        User one = repository.getById(id);
        assertNotNull(one);
        assertEquals(one.getUserId() , id);
        assertEquals(one.getFirstName() , "name_" + id);
        assertEquals(one.getUsername() , "username_" + id);

        id = 0;
        one = repository.getById(id);
        assertNull(one);
    }

    @Test
    void getUsers()
    {
        final List<User> users = repository.getUsers();
        assertNotNull(users);
        assertEquals(users.size() , 100);
    }
}