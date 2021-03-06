package web.servlet;

import dto.Role;
import dto.User;
import services.ServiceFactory;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/edituser.jhtml")
public class EditUserServlet extends HttpServlet {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginUser");
        User user = null;
        try {
            user = userService.findByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user != null) {
            req.setAttribute("user", user);
            req.setAttribute("loginUser", login);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String loginUser = req.getParameter("loginUser");
        String newLogin = req.getParameter("newLogin");
        String email = req.getParameter("email");
        String dob = req.getParameter("dob");
        String[] roles = req.getParameterValues("role");
        ArrayList<Role> userRoles = new ArrayList<>();
        if (roles != null) {
            Arrays.stream(roles).forEach(role-> userRoles.add(new Role(role)));
        }
        String password = req.getParameter("password");
        String loginFromSession = (String) session.getAttribute("login");
        try {
            if (!email.contains("@")
                    || newLogin.equals("")
                    || dob.equals("")
                    || (password != null && password.equals(""))
                    || userService.findByLogin(newLogin) != null) {
                String errors = "";
                if (newLogin.equals("")) {
                    errors += "login(empty) ";
                }
                if (userService.findByLogin(newLogin) != null) {
                    errors += "login(exist) ";
                }
                if (!email.contains("@")) {
                    errors += "email ";
                }
                if (password != null && password.equals("")) {
                    errors += "password ";
                }
                if (dob.equals("")) {
                    errors += "dob";
                }
                req.setAttribute("loginUser", loginUser);
                req.setAttribute("errors", errors);
                User user = new User(newLogin, userRoles, email, dob);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp?editFailed=true").forward(req, resp);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (loginUser != null) {
            if (loginUser.equals(loginFromSession)) {
                session.setAttribute("login", newLogin);
            }
            try {
                userService.update(
                        loginUser,
                        newLogin,
                        email,
                        dob,
                        userRoles);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/listUsers.jhtml");
        } else {
            User newUser = new User(newLogin, password, userRoles, email, dob);
            try {
                userService.create(newUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/listUsers.jhtml");
        }
    }
}
