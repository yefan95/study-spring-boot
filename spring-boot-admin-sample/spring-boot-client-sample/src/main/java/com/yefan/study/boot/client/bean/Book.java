package com.yefan.study.boot.client.bean;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    @GeneratedValue
    private long bookId;

    @NotNull
    @Column(name = "book_name", nullable = false)
    private String bookName;
    @Column(name = "author")
    private String author;
    @Min(value = 0, message = "字数不能为负数")
    @Column(name = "page_num")
    private int pageNum;
    @Column(name = "press")
    private String press;
    @Column(name = "publish_date")
    private Date publishDate;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", pageNum=" + pageNum +
                ", press='" + press + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
