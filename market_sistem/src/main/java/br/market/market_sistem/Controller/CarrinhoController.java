package br.market.market_sistem.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import br.market.market_sistem.Model.Carrinho;
import br.market.market_sistem.Model.Produto;
import br.market.market_sistem.Model.ProdutoDAO;
import jakarta.servlet.ServletException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CarrinhoController {

    @GetMapping("/addNoCarrinho/{id}")
    public void adicionarNoCarrinho(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Enumeration<String> email_session = session.getAttributeNames();
        String email_validado = email_session.nextElement();
        Cookie[] cookies = request.getCookies();        
        String carrinho = "";

        if(session != null){
            carrinho += (id.replaceAll("[^0-9]", "") + "_");

            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(email_validado.replace("@", "-"))){
                        String id_produto = cookie.getValue();
                        carrinho += id_produto;
                    }
                }
            }
            
            Cookie c = new Cookie(email_validado.replace("@", "-"), carrinho);
            c.setMaxAge(48 * 60 * 60);
            c.setPath("/");
            response.addCookie(c);
            response.sendRedirect("/listarProdutosCliente");
        }else{
            response.sendRedirect("login.html");
        }
    }

    @GetMapping("/removeDoCarrinho/{id}")
    public void removerDoCarrinho(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

    }



    @GetMapping("/verCarrinho")
    public void getCarrinhoCompras(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if(session != null){
            Carrinho carrinho = new Carrinho();
            Cookie[] cookies = request.getCookies();
            Enumeration<String> email_session = session.getAttributeNames();
            String email_validado = email_session.nextElement();

            String id_produtos = "";
            int id_inteiro;
            ProdutoDAO pDAO = new ProdutoDAO();

            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(email_validado.replace("@", "-")))
                        id_produtos += cookie.getValue();
                }
            }

            // tratar ids
            if(id_produtos != ""){
                String[] id_limpos = id_produtos.split("_");

                for(int i = 0; i < id_limpos.length; i++){
                    if(!id_limpos[i].isEmpty()){
                        try{
                            // converter para int
                            id_inteiro = Integer.parseInt(id_limpos[i]);
                            // buscar o produto no bd
                            // add no carrinho
                            carrinho.addProduto(pDAO.getProdutoById(id_inteiro));
                        }catch(NumberFormatException exception){
                            exception.printStackTrace();
                        }
                    }
                }
            }

            // exibir com writer

            var writer = response.getWriter();
            writer.println("<html><head><title>Carrinho de produtos</title><head><body style='display: flex; flex-direction: column; align-items: center;'><header 'margin-bottom: 20px;'><h1>Lista Carrinho</h1></header><br><table border='1' style='margin-top: 0px;'>");
            writer.println("<tr>");
            writer.println("<th>Nome</th>");
            writer.println("<th>Descrição</th>");
            writer.println("<th>Preço</th>");
            writer.println("<th>Estoque</th>");
            writer.println("<th>Remover</th>");
            writer.println("</tr>");

            for(Produto produto : carrinho.getProdutos()){
                writer.println("<tr>");
                writer.println("<td>" + produto.getNome() + "</td>");
                writer.println("<td>" + produto.getDescricao() + "</td>");
                writer.println("<td>" + produto.getPreco() + "</td>");
                writer.println("<td>" + produto.getEstoque() + "</td>");
                writer.println("<td><a href=/removeDoCarrinho/id=" + produto.getId() + " title='http://localhost:8080/MarketSistemApplication/removeDoCarrinho?id=" + produto.getId() + "&comando=remove'>Remover</a></td>");

                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("<a href='/listarProdutosCliente'>Ver Produtos</a>");
            writer.println("<br>");
            writer.println("</body></html>");

        }else{
            response.sendRedirect("login.html");
        }
    }
}
