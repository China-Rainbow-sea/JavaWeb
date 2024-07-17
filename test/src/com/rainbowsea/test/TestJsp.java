package com.rainbowsea.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/test01")
public class TestJsp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //request.getRequestDispatcher("/WEB-INF/test.html").forward(request,response);
        request.getRequestDispatcher("/WEB-INF/jsp/menuView.jsp").forward(request,response);
    }
}
