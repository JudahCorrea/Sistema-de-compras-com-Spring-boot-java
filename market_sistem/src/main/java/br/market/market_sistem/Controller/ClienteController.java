package br.market.market_sistem.Controller;

import br.market.market_sistem.Model.Cliente;
import br.market.market_sistem.Model.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ClienteController {
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
            response.sendRedirect("cadastro_cliente.html?msg=email ja cadastrado");
        }
    }


    @GetMapping("/redirectLogin")
    public void retornarPaginaLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.html");
    }
}
