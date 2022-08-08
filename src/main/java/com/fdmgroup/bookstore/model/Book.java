package com.fdmgroup.bookstore.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book
{
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private int itemId;

    private double price;

    private String title;

    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_genre")
    private BookGenre bookGenre;

    @OneToMany
    private List<Order> orders;

    public Book(int itemId , double price , String title , String author , BookGenre bookGenre)
    {
        this.itemId = itemId;
        this.price = price;
        this.title = title;
        this.author = author;
        this.bookGenre = bookGenre;
    }

    public Book(int itemId , double price , String title , String author , BookGenre bookGenre , List<Order> orders)
    {
        this.itemId = itemId;
        this.price = price;
        this.title = title;
        this.author = author;
        this.bookGenre = bookGenre;
        this.orders = orders;
    }

    public Book()
    {

    }

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public BookGenre getBookGenre()
    {
        return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre)
    {
        this.bookGenre = bookGenre;
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
    public String toString()
    {
        return "Book{" +
                "itemId=" + itemId +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookGenre=" + bookGenre +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return itemId == book.itemId && Double.compare(book.price , price) == 0 && Objects.equals(title , book.title) && Objects.equals(author , book.author) && bookGenre == book.bookGenre && Objects.equals(orders , book.orders);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(itemId , price , title , author , bookGenre , orders);
    }
}
