package br.market.market_sistem;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class loginController {
    @PostMapping("/logar")
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var email = request.getParameter("email");
        var password = request.getParameter("password");
        
        // métodos para validar login
    }

    @PostMapping("/logout")
    public void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("login.html?msg=Usuario deslogado");
    }
}
