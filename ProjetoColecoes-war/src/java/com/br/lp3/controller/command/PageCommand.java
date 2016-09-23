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
public class PageCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        responsePage = "home.jsp";
        String action = request.getParameter("command").split("\\.")[1];
        request.getSession().setAttribute("page", action);
    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
    }


}
