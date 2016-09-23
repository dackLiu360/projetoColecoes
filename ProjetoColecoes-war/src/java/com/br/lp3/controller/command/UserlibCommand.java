package com.br.lp3.controller.command;

import com.br.lp3.dao.UserlibDAO;
import com.br.lp3.entities.Userlibinfo;
import com.br.lp3.entities.Userlib;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1147106
 */
public class UserlibCommand implements Command {

    UserlibDAO userlp3DAO = lookupUserlibDAOBean();

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;
    private String username;
    private String password;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("command").split("\\.")[1];
        switch (action) {
            case "register":
                username = request.getParameter("username");
                password = request.getParameter("password");
                String password2 = request.getParameter("password2");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String email = request.getParameter("email");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date birthday = new Date();

                try {
                    birthday = sdf.parse(request.getParameter("birthday"));
                } catch (ParseException ex) {
                    Logger.getLogger(UserlibCommand.class.getName()).log(Level.SEVERE, null, ex);
                }

                Userlib temp1 = userlp3DAO.findByUsername(username);
                Userlib temp2 = userlp3DAO.findByEmail(email);

                if (temp1 != null) {
                    responsePage = "error.jsp";
                    request.getSession().setAttribute("error", "Username already registerd!");
                    break;
                } else if (temp2 != null) {
                    responsePage = "error.jsp";
                    request.getSession().setAttribute("error", "Email already registered!");
                    break;
                } else if (!password.equals(password2)) {
                    responsePage = "error.jsp";
                    request.getSession().setAttribute("error", "Passwords don't match!");
                    break;
                } else {
                    Userlib user = new Userlib();
                    user.setUsername(username);
                    user.setPassword(password);

                    Userlibinfo ui = new Userlibinfo();
                    ui.setBirthday(birthday);
                    ui.setEmail(email);
                    ui.setFirstname(firstname);
                    ui.setLastname(lastname);

                    ui.setUserlib(user);
                    user.setUserlibinfo(ui);

                    userlp3DAO.insert(user);
                    responsePage = "index.jsp";
                }

                break;
            case "login":
                username = request.getParameter("username");
                password = request.getParameter("password");
                Userlib temp3 = userlp3DAO.findByUsername(username);
                if (temp3 == null) {
                    responsePage = "error.jsp";
                    request.getSession().setAttribute("error", "User not found!");
                } else if (!password.equals(temp3.getPassword())) {
                    responsePage = "error.jsp";
                    request.getSession().setAttribute("error", "Wrong password!");
                } else {
                    request.getSession().setAttribute("user", temp3);
                    request.getSession().setAttribute("page", "home");
                    responsePage = "home.jsp";
                }
                break;
            case "logout":
                request.getSession().invalidate();
                responsePage = "index.jsp";
                break;
        }
    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
    }

    private UserlibDAO lookupUserlibDAOBean() {
        try {
            Context c = new InitialContext();
            return (UserlibDAO) c.lookup("java:global/BaseLP3/BaseLP3-ejb/UserlibDAO!com.br.lp3.model.dao.UserlibDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
