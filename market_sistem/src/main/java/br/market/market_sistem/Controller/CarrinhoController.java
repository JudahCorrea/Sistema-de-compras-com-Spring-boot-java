package br.market.market_sistem.Controller;

import java.io.IOException;
import java.util.ArrayList;

import br.market.market_sistem.Model.Carrinho;
import br.market.market_sistem.Model.Produto;
import br.market.market_sistem.Model.ProdutoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CarrinhoController {

    //    @Autowired
    private Carrinho carrinho = new Carrinho();

    @GetMapping("/addNoCarrinho/{id}")
    public void addNoCarrinho(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        int idInt = Integer.parseInt(id);
//        Carrinho carrinho = new Carrinho();
//        ProdutoDAO pDAO = new ProdutoDAO();
//        carrinho.addProduto(pDAO.getProdutoById(idInt));
//
//        salvarCarrinhoNoCookie(carrinho, request, response);
        // pegando apenas o valor que está vindo do PathVariable
        String idS = id.replaceAll("[^0-9]", "");


        // ou criamos um setId ou um construtor que apenas recebe o id
        //Produto produto = new Produto(id);
        //pega os ids dos produtos e adiciona no carrinho
        // carrinho.addProduto(produto);


        System.out.println("Estou no CarrinhoController id " + idS);
        Produto produto = carrinho.getProduto(Integer.parseInt(idS));
        System.out.println("Usando os metodos do carrinho " + produto);
        ProdutoDAO pDAO = new ProdutoDAO();
        produto = pDAO.getProdutoById(Integer.parseInt(idS));
        System.out.println("Usando os metodos do produtoDAO " + produto);
        if (produto != null) { // Verifica se o produto não é nulo antes de adicioná-lo
            carrinho.addProduto(produto);
        }else{
            carrinho.addProduto(produto);
        }
        response.sendRedirect("/verCarrinho");


        //System.out.println(carrinho.getProdutos());

    }


    public void salvarCarrinhoNoCookie(Carrinho carrinho, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cookie c = null;
        Cookie[] cookies = request.getCookies();
        //HttpSession session = request.getSession(false);


        // conversao do carrinho list para string


        if (cookies == null) {
            c = new Cookie("carrinho", carrinho.toString());
            c.setMaxAge(172800);
            response.addCookie(c);
            response.sendRedirect("/listarProdutosCliente");
        }
        if (cookies != null) {

            ArrayList<Produto> cookiesList;

            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("carrinho")){
                    //String valorCookie = cookies[i].getValue();
                    //return new Gson().fromJson(cookies[i].getValue(), Carrinho.class);
                }
            }
            response.sendRedirect("/listarProdutosCliente");
        } else {
            response.sendRedirect("login.html");
        }
    }

    @RequestMapping(value = "/verCarrinho", method = RequestMethod.GET)
    public void verCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Cookie[] cookies = request.getCookies();
//
//        if (cookies != null) {
//            // mostra carrinho criando html
//
//            var writer = response.getWriter();
//            writer.println("<html><head><title>Página Carrinho</title><head><body style='display: flex; flex-direction: column; align-items: center;'><header 'margin-bottom: 20px;'><h1>Lista Carrinho</h1></header><br><table border='1' style='margin-top: 0px;'>");
//            writer.println("<tr>");
//            writer.println("<th>Nome</th>");
//            writer.println("<th>Descrição</th>");
//            writer.println("<th>Preço</th>");
//            writer.println("<th>Quantidade</th>");
//            writer.println("<th>Remover</th>");
//            writer.println("</tr>");
//
//
//            // mostrar os produtos apartir do cookie
//            for (int i = 0; i < cookies.length; i++) {
//
//                if (cookies[i].getName().equals("carrinho")) {
//                    String valorCookie = cookies[i].getValue();
//                    int id = Integer.parseInt(valorCookie);
//                    ProdutoDAO pDAO = new ProdutoDAO();
//                    Produto p = pDAO.getProdutoById(id);
//                    writer.println("<tr>");
//                    writer.println("<td>" + p.getNome() + "</td>");
//                    writer.println("<td>" + p.getDescricao() + "</td>");
//                    writer.println("<td>" + p.getPreco() + "</td>");
//                    writer.println("<td>" + p.getEstoque() + "</td>");
//                    writer.println("<td><a href=/removerDoCarrinho/id=" + p.getId() + ">Remover</a></td>");
//                }
//                writer.println("</tr>");
//            }
//
//            writer.println("</table>");
//            writer.println("<a href='/listarProdutosCliente'>Ver Produtos</a>");
//            writer.println("<br>");
//            writer.println("<a href='/redirectPortalCliente'>Retornar<a>");
//            writer.println("</body></html>");
//
//        } else {
//            response.sendRedirect("/listarProdutosCliente");
//        }


        // fazendo sozinha

        ArrayList<Produto> listaCarrinho = carrinho.getProdutos();

        var writer = response.getWriter();
        writer.println("<html><head><title>Página Carrinho</title><head><body style='display: flex; flex-direction: column; align-items: center;'><header 'margin-bottom: 20px;'><h1>Lista Carrinho</h1></header><br><table border='1' style='margin-top: 0px;'>");
        writer.println("<tr>");
        writer.println("<th>Nome</th>");
        writer.println("<th>Descrição</th>");
        writer.println("<th>Preço</th>");
        writer.println("<th>Quantidade</th>");
        writer.println("<th>Remover</th>");
        writer.println("</tr>");

        // mostrar os produtos apartir do cookie
        for (Produto produto : listaCarrinho) {
            if(produto != null){
                writer.println("<tr>");
                writer.println("<td>" + produto.getNome() + "</td>");
                writer.println("<td>" + produto.getDescricao() + "</td>");
                writer.println("<td>" + produto.getPreco() + "</td>");
                writer.println("<td>" + produto.getEstoque() + "</td>");
                writer.println("<td><a href=/removerDoCarrinho/id=" + produto.getId() + ">Remover</a></td>");
            }



            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.println("<a href='/listarProdutosCliente'>Ver Produtos</a>");
        writer.println("<br>");
        writer.println("</body></html>");

//            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarProdutosCliente");
//            dispatcher.forward(request, response);
    }

//        else {
//            response.sendRedirect("/listarProdutosCliente");
//        }

}