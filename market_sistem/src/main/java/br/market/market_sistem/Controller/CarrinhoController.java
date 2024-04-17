package br.market.market_sistem.Controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import br.market.market_sistem.Model.Carrinho;
import br.market.market_sistem.Model.Produto;
import br.market.market_sistem.Model.ProdutoDAO;
import jakarta.servlet.ServletException;
import jakarta.websocket.server.PathParam;
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
                    if (cookie.getName().equals(email_validado.replace("@", "-"))) {
                        String[] produtos = cookie.getValue().split("_");
                        boolean produtoJaAdicionado = false;
                        for (String produto : produtos) {
                            if (!produto.isEmpty()) {
                                if (produto.equals(id)) {
                                    produtoJaAdicionado = true;
                                    break;
                                }
                            }
                        }
                        if (!produtoJaAdicionado) {
                            carrinho += cookie.getValue();
                        }
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
        HttpSession session = request.getSession(false);
        Enumeration<String> email_session = session.getAttributeNames();
        String email_validado = email_session.nextElement();
        String carrinho = "";
        String id_remove = id.replaceAll("[^0-9]","");

        if(session != null){
            //pegar cookies
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(email_validado.replace("@","-"))){
                        String[] id_produtos = cookie.getValue().split("_");
                        boolean removeu = false;
                        //mapeando ids para construção do novo trem de id`s
                        for(String id_produto : id_produtos){
                            if(!id_produto.isEmpty()){
                                if(!removeu){
                                    //remove id do carrinho (cookie)
                                    if(id_produto.equals(id_remove)){
                                        id_produto = "";
                                        removeu = true;
                                        continue;
                                    }
                                }
                                //adiciona id remanecentes ao novo carrinho
                                carrinho += (id_produto + "_");
                            }
                        }
                    }
                }
                Cookie c = new Cookie(email_validado.replace("@","-"),carrinho);
                c.setMaxAge(48 * 60 * 60);
                c.setPath("/");
                response.addCookie(c);
            }
            response.sendRedirect("/verCarrinho");
        }else{
            response.sendRedirect("login.html");
        }
    }


    @GetMapping("/verCarrinho")
    public void getCarrinhoCompras(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if(session != null){
            Carrinho carrinho = new Carrinho();
            Cookie[] cookies = request.getCookies();
            Enumeration<String> email_session = session.getAttributeNames();
            String email_validado = email_session.nextElement();

            //guardando
            HashMap<Integer, Integer> idQuantidades = new HashMap<>();
            ProdutoDAO pDAO = new ProdutoDAO();

            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(email_validado.replace("@", "-"))) {
                        String[] idProdutos = cookie.getValue().split("_");
                        for(String idProduto : idProdutos) {
                            if(!idProduto.isEmpty()) {
                                try {
                                    int id = Integer.parseInt(idProduto);
                                    if (idQuantidades.containsKey(id)) {
                                        idQuantidades.put(id, idQuantidades.get(id) + 1);
                                    } else {
                                        idQuantidades.put(id, 1);
                                    }
                                } catch(NumberFormatException exception) {
                                    exception.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

            var writer = response.getWriter();
            writer.println("<html><head><title>Carrinho de produtos</title><head><body style='display: flex; flex-direction: column; align-items: center;'><header 'margin-bottom: 20px;'><h1>Lista Carrinho</h1></header><br><table border='1' style='margin-top: 0px;'>");
            writer.println("<tr>");
            writer.println("<th>Nome</th>");
            writer.println("<th>Descrição</th>");
            writer.println("<th>Preço</th>");
            writer.println("<th>Quantidade</th>");
            writer.println("<th>Remover</th>");
            writer.println("</tr>");

            for(Map.Entry<Integer, Integer> entry : idQuantidades.entrySet()) {
                int id = entry.getKey();
                int quantidade = entry.getValue();
                Produto produto = pDAO.getProdutoById(id);

                if (produto != null) {
                    writer.println("<tr>");
                    writer.println("<td>" + produto.getNome() + "</td>");
                    writer.println("<td>" + produto.getDescricao() + "</td>");
                    writer.println("<td>" + "<p>R$ " + produto.getPreco() + "</p>"+ "</td>");
                    writer.println("<td>" + quantidade + "</td>");
                    writer.println("<td><a href='/removeDoCarrinho/id=" + produto.getId() + " title='http://localhost:8080/MarketSistemApplication/removeDoCarrinho?id= "+ produto.getId() + ">Remover</a></td>");
                    writer.println("</tr>");
                }
            }

            writer.println("</table>");
            writer.println("<a href='/listarProdutosCliente'>Ver Produtos</a>");
            writer.println("<form action='/finalizaCompra'><button type='submit'>Finalizar compra</button></form>");
            writer.println("<br>");
            writer.println("</body></html>");

        } else {
            response.sendRedirect("login.html");
        }
    }

    @GetMapping("/finalizaCompra")
    public void finalizarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            //Carrinho carrinho = new Carrinho();
            Cookie[] cookies = request.getCookies();
            Enumeration<String> email_session = session.getAttributeNames();
            String email_validado = email_session.nextElement();

            // guardando

            ProdutoDAO pDAO = new ProdutoDAO();
            //Produto p = new Produto();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(email_validado.replace("@", "-"))) {
                        String[] idProdutos = cookie.getValue().split("_");
                        for (String idProduto : idProdutos) {
                            if (!idProduto.isEmpty()) {
                                try {
                                    int id = Integer.parseInt(idProduto);
                                    Produto p = pDAO.getProdutoById(id);
                                    p.diminuiEstoque();
                                    pDAO.atualizarProduto(p);
                                } catch (NumberFormatException exception) {
                                    exception.printStackTrace();
                                } 
                            }
                        }
                    }
                }
            }
            response.sendRedirect("/listarProdutosCliente");
        }
    }
}
