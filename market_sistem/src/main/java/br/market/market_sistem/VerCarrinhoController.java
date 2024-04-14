package br.market.market_sistem;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class VerCarrinhoController {

    @GetMapping("/verCarrinho")
    public void verCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Cookie[] c = request.getCookies();

        if(c != null){
            
        }

    }

    @GetMapping("/addNoCarrinho/{id}")
    public void addNoCarrinho(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException{

        Cookie c =new Cookie("produtoSelecionado",id );
        c.setMaxAge(172800);
        response.addCookie(c);

    }
}
