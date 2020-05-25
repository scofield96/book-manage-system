package com.scofield.bms.servlet.user;

import com.scofield.bms.bean.Book;
import com.scofield.bms.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index/bookList")
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
        req.setAttribute("bookList", bookList);
        req.getRequestDispatcher("/user-index.jsp").forward(req, resp);
    }
}
