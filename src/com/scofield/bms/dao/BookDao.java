package com.scofield.bms.dao;

import com.scofield.bms.bean.Book;
import com.scofield.bms.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    /**
     * 图书列表
     *
     * @return
     */
    public List<Book> selectBook() {
        List<Book> list = new ArrayList<>();
        Connection conn = DbUtil.getConnection();
        String sql = "select * from book";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Book book = new Book();
                book.setBookId(rst.getInt("book_id"));
                book.setBookName(rst.getString("book_name"));
                book.setBookSprice(rst.getDouble("book_sprice"));
                book.setBookCount(rst.getInt("book_count"));
                book.setBookAuthor(rst.getString("book_author"));
                list.add(book);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 新增图书
     *
     * @param book
     * @return
     */
    public boolean addBook(Book book) {
        String sql = "INSERT INTO book(book_name,book_sprice,book_count,book_author) VALUES (?,?,?,?)";
        Connection conn = DbUtil.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, book.getBookName());
            pst.setDouble(2, book.getBookSprice());
            pst.setInt(3, book.getBookCount());
            pst.setString(4, book.getBookAuthor());
            int count = pst.executeUpdate();
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新图书
     *
     * @param book
     * @return
     */
    public boolean updateBook(Book book) {
        String sql = "update book set book_name=?, book_sprice=?, book_count=?, book_author=? where book_id = ?";
        Connection conn = DbUtil.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, book.getBookName());
            pst.setDouble(2, book.getBookSprice());
            pst.setInt(3, book.getBookCount());
            pst.setString(4, book.getBookAuthor());
            pst.setInt(5, book.getBookId());
            int count = pst.executeUpdate();
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除图书
     *
     * @param bookId
     * @return
     */
    public boolean deleteBook(int bookId) {
        String sql = "delete from book where book_id = ?";
        Connection conn = DbUtil.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, bookId);
            int count = pst.executeUpdate();
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获得图书
     *
     * @param bookId
     * @return
     */
    public Book getBook(int bookId) {
        Connection conn = DbUtil.getConnection();
        String sql = "select * from book where book_id = " + bookId;
        Book book = null;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                book = new Book();
                book.setBookId(rst.getInt("book_id"));
                book.setBookName(rst.getString("book_name"));
                book.setBookSprice(rst.getDouble("book_sprice"));
                book.setBookCount(rst.getInt("book_count"));
                book.setBookAuthor(rst.getString("book_author"));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * 模糊搜索
     *
     * @param key
     * @return
     */
    public List<Book> searchBook(String key) {
        List<Book> list = new ArrayList<>();
        Connection conn = DbUtil.getConnection();
        String sql = "SELECT * FROM book WHERE book_name like '%" + key + "%'";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Book book = new Book();
                book.setBookId(rst.getInt("book_id"));
                book.setBookName(rst.getString("book_name"));
                book.setBookSprice(rst.getDouble("book_sprice"));
                book.setBookCount(rst.getInt("book_count"));
                book.setBookAuthor(rst.getString("book_author"));
                list.add(book);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
