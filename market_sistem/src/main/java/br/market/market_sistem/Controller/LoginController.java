package br.market.market_sistem.Controller;

import java.io.IOException;

import br.market.market_sistem.Model.ClienteDAO;
import br.market.market_sistem.Model.LojistaDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

    @PostMapping("/logar")
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        var email = request.getParameter("email");
        var password = request.getParameter("password");
        
        // métodos para validar login
        ClienteDAO c = new ClienteDAO();
        LojistaDAO l = new LojistaDAO();

        boolean confirm_c = c.confirmClienteCredentials(email, password);
        boolean confirm_l = l.confirmLojistaCredentials(email, password);

        HttpSession session = request.getSession(true);// criando sessão

        if(confirm_c){   
            session.setAttribute("logado", true);
            response.sendRedirect("portal_cliente.html");
            return;
        }
        if(confirm_l){
            session.setAttribute("logado", true);
            response.sendRedirect("portal_logista.html");
            return;
        }

        response.sendRedirect("login.html?msg=login falhou");
    }

    @GetMapping("/logout")
    public void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate(); // Invalida a sessão apenas se existir
        }
        response.sendRedirect("login.html?msg=usuario deslogado");
    }
}
