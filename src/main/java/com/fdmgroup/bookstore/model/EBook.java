package com.fdmgroup.bookstore.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "e_book")
public class EBook
{
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "size_mega_bytes")
    private double sizeMegaBytes;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "bookId")
    private Book book;

    public EBook(int id , double sizeMegaBytes , Book book)
    {
        this.id = id;
        this.sizeMegaBytes = sizeMegaBytes;
        this.book = book;
    }

    public EBook()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getSizeMegaBytes()
    {
        return sizeMegaBytes;
    }

    public void setSizeMegaBytes(double timeLengthSeconds)
    {
        this.sizeMegaBytes = timeLengthSeconds;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EBook eBook = (EBook) o;
        return id == eBook.id && sizeMegaBytes == eBook.sizeMegaBytes && Objects.equals(book , eBook.book);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id , sizeMegaBytes , book);
    }

    @Override
    public String toString()
    {
        return "EBook{" +
                "id=" + id +
                ", timeLengthSeconds=" + sizeMegaBytes +
                ", book=" + book +
                '}';
    }
}
