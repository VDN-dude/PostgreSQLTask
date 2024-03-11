package tms.servlet;

import tms.entity.User;
import tms.service.UserService;
import tms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet("/modify-user")
public class ModifyUserServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            req.setAttribute("notFound", "There isn't any id for search user, or user with this id isn't exist!");
        } else {
            Optional<User> byId = userService.findById(Long.parseLong(id));
            byId.ifPresent(user -> this.user = user);
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/pages/modify-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        userService.modify(user, parameterMap);
        resp.sendRedirect(req.getContextPath() + "/modify-user?id=" + user.getId());
    }
}
