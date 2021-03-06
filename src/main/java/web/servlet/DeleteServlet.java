package web.servlet;

import dto.User;
import services.ServiceFactory;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUser.jhtml")
public class DeleteServlet extends HttpServlet {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deletableLogin = req.getParameter("deletableLogin");
        User deletableUser = null;
        try {
            deletableUser = userService.findByLogin(deletableLogin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            userService.delete(deletableUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//
        resp.sendRedirect(req.getContextPath() + "/listUsers.jhtml");
    }
}
