package tms.servlet;

import tms.entity.PageableUser;
import tms.service.UserService;
import tms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        int offset;
        int pageSize = 10;
        if (page == null || page.equals("0")){
            offset = 0;
        } else {
            req.setAttribute("page", page);
            offset = Integer.parseInt(page) * pageSize - pageSize;
        }
        Optional<PageableUser> pageableUser = userService.findAll(offset, pageSize);
        pageableUser.ifPresent(user -> req.setAttribute("pageableUserList", user));

        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
    }
}
