package com.scofield.bms.servlet.admin;

import com.scofield.bms.bean.Book;
import com.scofield.bms.dao.BookDao;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/bookList")
public class BookListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao dao = new BookDao();
        List<Book> bookList = dao.selectBook();
        req.setAttribute("bookList", bookList);//将bookList保存在当前请求，在manage-book-index.jsp中利用EL表达式打印
        req.getRequestDispatcher("/admin/manage-book-index.jsp").forward(req, resp);
    }

}
