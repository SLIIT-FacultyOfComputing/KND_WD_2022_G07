package com.example.sweetreads;

public class BookModel
{
    String Book_name , Book_price , Book_url;

    public BookModel() {}

    public BookModel(String book_name, String book_price, String book_url) {
        Book_name = book_name;
        Book_price = book_price;
        Book_url = book_url;
    }

    public String getBook_name() {
        return Book_name;
    }

    public void setBook_name(String book_name) {
        Book_name = book_name;
    }

    public String getBook_price() {
        return Book_price;
    }

    public void setBook_price(String book_price) {
        Book_price = book_price;
    }

    public String getBook_url() {
        return Book_url;
    }

    public void setBook_url(String book_url) {
        Book_url = book_url;
    }
}

