package br.market.market_sistem;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ListarProdutoController {
    
    @GetMapping("/redirecCadastro")
    public void redirecCadastro(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.sendRedirect("cadastrar_produto.html");
    }

    @PostMapping("/cadastrarProduto")
    public void cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var preco = Float.valueOf(request.getParameter("preco")); 
        var nome = request.getParameter("nome");
        var descricao = request.getParameter("descricao");
        var estoque = Integer.parseInt(request.getParameter("estoque"));
        
        Produto p = new Produto();
        ProdutoDAO pd = new ProdutoDAO();
        p.setPreco(preco);
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setEstoque(estoque);
        pd.addProduto(p);
        response.sendRedirect("portal_logista.html");
    }
}
