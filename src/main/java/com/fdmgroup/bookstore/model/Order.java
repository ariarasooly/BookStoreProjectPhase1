package com.fdmgroup.bookstore.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "order")
public class Order
{
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "itemId")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;

    public Order(int orderId , Book book , User user , LocalDateTime orderDateTime)
    {
        this.orderId = orderId;
        this.book = book;
        this.user = user;
        this.orderDateTime = orderDateTime;
    }

    public Order()
    {

    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public LocalDateTime getOrderDateTime()
    {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime)
    {
        this.orderDateTime = orderDateTime;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(book , order.book) && Objects.equals(user , order.user) && Objects.equals(orderDateTime , order.orderDateTime);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(orderId , book , user , orderDateTime);
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "orderId=" + orderId +
                ", book=" + book +
                ", user=" + user +
                ", orderDateTime=" + orderDateTime +
                '}';
    }
}