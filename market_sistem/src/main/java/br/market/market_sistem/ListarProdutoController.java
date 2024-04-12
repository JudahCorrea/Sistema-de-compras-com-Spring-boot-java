package br.market.market_sistem;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



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

        HttpSession session = request.getSession(false);
        if(session != null){
            response.sendRedirect("portal_logista.html");
        }else{
            response.sendRedirect("login.html");
        }
        
    }

    @GetMapping("/listarProdutos")
    public void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProdutoDAO p = new ProdutoDAO();
        List<Produto> listaDeProdutos = p.listar();

        var writer = response.getWriter();

        writer.println("<html><head><title>Produtos Cadastrados</title><head><body><header><h1>Lista de Produtos</h1></header><br><table border='1'>");
        writer.println("<tr>");
        writer.println("<th>PREÇO");
        writer.println("<th>NOME");
        writer.println("<th>DESCRIÇÂO");
        writer.println("<th>ESTOQUE");
        writer.println("</tr>");

        for(Produto produto : listaDeProdutos){
            writer.println("<tr>");
            writer.println("<td>"+ produto.getPreco());
            writer.println("<td>"+ produto.getNome());
            writer.println("<td>"+ produto.getDescricao());
            writer.println("<td>"+ produto.getEstoque());
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("<br>");
        writer.println("<form action='/redirecPortalLojista' method='get'><button type='submit'>Retornar</button></form>");
        writer.println("</body></html>");
    }

    @GetMapping("/redirecPortalLojista")
    public void redirecPortalLojista(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            response.sendRedirect("portal_logista.html");
        }else{
            response.sendRedirect("login.html");
        }
    }
    
    
}
