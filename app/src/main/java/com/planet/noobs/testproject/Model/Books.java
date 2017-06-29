package com.planet.noobs.testproject.Model;

/**
 * Created by rio on 29/6/17.
 */

public class Books {
    private String title;
    private String author;
    private String issueDate;
    private int bookId;
    private int bookIssued;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookIssued() {
        return bookIssued;
    }

    public void setBookIssued(int bookIssued) {
        this.bookIssued = bookIssued;
    }
}
