package br.market.market_sistem;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter // adicionar URI que irá passar pelo filtro
public class Filtro implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // trata a URI identificada pelo filtro
        
    }

    @Override
    public void destroy(){
        Filter.super.destroy();
    }
}
