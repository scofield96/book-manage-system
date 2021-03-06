package com.scofield.bms.servlet;

import com.scofield.bms.bean.Book;
import com.scofield.bms.bean.User;
import com.scofield.bms.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/search.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String key = req.getParameter("key");
        BookDao dao = new BookDao();
        List<Book> bookList = dao.searchBook(key);
        req.setAttribute("bookList", bookList);
        req.setAttribute("search", "搜索结果");
        // find都是找到一个，搜索能写search不
        // 这里返回到图列表页，没必要写一模一样的

        if (currentUser == null || currentUser.getUserLevelId() == 1) {
            req.getRequestDispatcher("/").forward(req, resp);
        } else {
            req.getRequestDispatcher("/admin/manage-book-index.jsp").forward(req,resp);
        }
    }

}
