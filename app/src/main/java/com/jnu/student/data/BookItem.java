package com.jnu.student.data;


public class BookItem {
    public int getImageId() {
        return imageId;
    }



    public String getName() {
        return name;
    }

    private final String name;
    private final int imageId;

    public BookItem(String name_, int bookId_) {
        this.name=name_;
        this.imageId=bookId_;
    }
}