package tms.servlet;

import tms.dto.RegUserDTO;
import tms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-user")
public class CreateUserServlet extends HttpServlet {
    private final UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/create-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        int age = Integer.parseInt(req.getParameter("age"));
        String phoneNumber = req.getParameter("phoneNumber");
        RegUserDTO regUserDTO = new RegUserDTO(firstname, lastname, age, phoneNumber);
        service.save(regUserDTO);
        resp.sendRedirect("/");
    }
}
