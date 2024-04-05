package br.market.market_sistem;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/portal_cliente.html", "/portal_logista.html"}) // adicionar URI que irá passar pelo filtro
public class Filtro implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);


        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect("login.html?msg=precisa_está_logado!");
        }else{
            Boolean logado = (Boolean) session.getAttribute("logado");
            if(!logado || logado == null){
                response.sendRedirect("login.html?msg=precisa_está_logado!");
            }
        }
        
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy(){
        Filter.super.destroy();
    }
}
