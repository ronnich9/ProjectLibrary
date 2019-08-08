package com.myapp.entity;

import com.myapp.entity.Book;
import com.myapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taken_books")
public class TakenBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "taken_time")
    private LocalDateTime taken_time;

    public LocalDateTime getTaken_time() {
        return taken_time;
    }

    public void setTaken_time(LocalDateTime taken_time) {
        this.taken_time = taken_time;
    }

    public LocalDateTime getReturned_time() {
        return returned_time;
    }

    public void setReturned_time(LocalDateTime returned_time) {
        this.returned_time = returned_time;
    }

    @Column(name = "returned_time")
    private LocalDateTime returned_time;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getBookLoanedAt() {
        return taken_time;
    }

    public void setBookLoanedAt(LocalDateTime taken_time) {
        this.taken_time = taken_time;
    }

    public Long getId() {
        return id;
    }

}
