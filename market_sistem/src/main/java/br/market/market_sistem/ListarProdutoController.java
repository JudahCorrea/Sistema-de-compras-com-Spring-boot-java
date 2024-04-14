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
    
    @GetMapping("/redirectCadastro")
    public void redirectCadastro(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.sendRedirect("cadastrar_produto.html");
    }

    @PostMapping("/cadastrarProduto")
    public void cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException{

        var nome = request.getParameter("nome");
        var descricao = request.getParameter("descricao");
        var preco = Float.valueOf(request.getParameter("preco"));
        var estoque = Integer.parseInt(request.getParameter("estoque"));
        
        Produto p = new Produto();
        ProdutoDAO pd = new ProdutoDAO();
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(preco);
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
        List<Produto> listaDeProdutos = p.listarProdutos();

        var writer = response.getWriter();

        writer.println("<html><head><title>Produtos Cadastrados</title><head><body style='display: flex; flex-direction: column; align-items: center;'><header style='margin-bottom: 20px;'><h1>Lista de Produtos</h1></header><br><table border='1' style='margin-top: 0px;'>");
        writer.println("<tr>");
        writer.println("<th>Nome");
        writer.println("<th>Descrição");
        writer.println("<th>Preço Unitário");
        writer.println("<th>Quantidade");
        writer.println("</tr>");

        for(Produto produto : listaDeProdutos){
            writer.println("<tr>");
            writer.println("<td>"+ produto.getNome());
            writer.println("<td>"+ produto.getDescricao());
            writer.println("<td>"+ produto.getPreco());
            writer.println("<td>"+ produto.getEstoque());
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("<br>");
        writer.println("<a href='/redirectPortalLojista'>Retornar<a>");
        writer.println("</body></html>");
    }

    @GetMapping("/redirectPortalLojista")
    public void redirectPortalLojista(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            response.sendRedirect("portal_logista.html");
        }else{
            response.sendRedirect("login.html");
        }
    }

    @GetMapping("/listarProdutosCliente")
    public void listarProdutoCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProdutoDAO pDAO = new ProdutoDAO();
        List<Produto> listaDeProdutos = pDAO.listarProdutos();

        var writer = response.getWriter();
        writer.println("<html><head><title>Página Produtos</title><head><body style='display: flex; flex-direction: column; align-items: center;'><header 'margin-bottom: 20px;'><h1>Lista Produtos</h1></header><br><table border='1' style='margin-top: 0px;'>");
        writer.println("<tr>");
        writer.println("<th>Nome</th>");
        writer.println("<th>Descrição</th>");
        writer.println("<th>Preço</th>");
        writer.println("<th>Estoque</th>");
        writer.println("<th>Carrinho</th>");
        writer.println("</tr>");

        for(Produto produto : listaDeProdutos){
            writer.println("<tr>");
            writer.println("<td>"+ produto.getNome() + "</td>");
            writer.println("<td>"+ produto.getDescricao() + "</td>");
            writer.println("<td>"+ produto.getPreco() + "</td>");
            writer.println("<td>"+ produto.getEstoque() + "</td>");
            if(produto.getEstoque() == 0)
                writer.println("<td>Sem estoque</td>");
            else
                //colocar o href
                writer.println("<td><a href=/addNoCarrinho/id="+produto.getId()+">Adicionar</a></td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.println("<a href='/VerCarrinho'>Ver carrinho</a>");
        writer.println("<br>");
        writer.println("<a href='/redirectPortalCliente'>Retornar<a>");
        writer.println("</body></html>");
    }

    @GetMapping("/redirectPortalCliente")
    public void redirectPortalCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            response.sendRedirect("portal_cliente.html");
        }else{
            response.sendRedirect("login.html");
        }
    }
}
