package net.golovach.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {/*NOP*/}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        doHttpFilter((HttpServletRequest)req, (HttpServletResponse) res, chain);
    }

    public abstract void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException;

    @Override
    public void destroy() {/*NOP*/}
}
