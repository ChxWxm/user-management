package com.usermangement.book;

public class Book {
    private Integer id;
    private String name;
    private String author;

    private boolean publish;

    public Book(Integer id, String name, String author, Boolean publish) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }
}
