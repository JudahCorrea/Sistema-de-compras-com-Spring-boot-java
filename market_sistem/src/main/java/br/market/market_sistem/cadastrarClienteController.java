package br.market.market_sistem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class cadastrarClienteController {
    @PostMapping("/cadastroCliente")
    public void cadastrarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var nome = request.getParameter("nome");
        var email = request.getParameter("email");
        var password = request.getParameter("password");

        ClienteDAO cDAO = new ClienteDAO();

        // verificando se o email já está cadastrado no bd
        boolean check_cliente = cDAO.checkEmailExistence(email);

        if(!check_cliente){
            Cliente cliente = new Cliente(nome, email, password);
            cDAO.insertClientBD(cliente);
            response.sendRedirect("login.html");
        }
        else{
            response.sendRedirect("cadastroCliente.html?msg=email ja cadastrado");
        }
    }
}
