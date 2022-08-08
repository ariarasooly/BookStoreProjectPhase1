package com.fdmgroup.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "audio_book")
public class AudioBook
{
    @Id
    @GeneratedValue
    private int id;

    private int timeLengthSeconds;

    public AudioBook(int id , int timeLengthSeconds)
    {
        this.id = id;
        this.timeLengthSeconds = timeLengthSeconds;
    }

    public AudioBook()
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

    public int getTimeLengthSeconds()
    {
        return timeLengthSeconds;
    }

    public void setTimeLengthSeconds(int timeLengthSeconds)
    {
        this.timeLengthSeconds = timeLengthSeconds;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioBook audioBook = (AudioBook) o;
        return id == audioBook.id && timeLengthSeconds == audioBook.timeLengthSeconds;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id , timeLengthSeconds);
    }

    @Override
    public String toString()
    {
        return "AudioBook{" +
                "id=" + id +
                ", timeLengthSeconds=" + timeLengthSeconds +
                '}';
    }
}
