package com.fdmgroup.bookstore.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue
    @Column
    (name = "user_id")
    private int userId;

    @Column
    (name = "first_name")
    private String firstName;

    @Column
    (name = "last_name")
    private String lastName;

    @Column
    (nullable = false, unique = true)
    private String username;

    private String password;

    @Column
    (nullable = false, unique = true)
    private String email;

    @ManyToMany
    private List<Order> orders;

    public User(int userId , String firstName , String lastName , String username , String password , String email , List<Order> orders)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.orders = orders;
    }

    public User(String firstName , String lastName , String username , String password , String email , List<Order> orders)
    {
        this.userId = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.orders = orders;
    }

    public User()
    {

    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName , user.firstName) && Objects.equals(lastName , user.lastName) && Objects.equals(username , user.username) && Objects.equals(password , user.password) && Objects.equals(email , user.email) && Objects.equals(orders , user.orders);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId , firstName , lastName , username , password , email , orders);
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                '}';
    }
}