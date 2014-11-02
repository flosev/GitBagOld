package net.golovach.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PerfFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {/*NOP*/}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        long startTime = System.nanoTime();
        try {
            chain.doFilter(req, res);
        } finally {
            long stopTime = System.nanoTime();
            long dT = stopTime - startTime;
            System.out.printf("uri:%s, dT:%d", ((HttpServletRequest)req).getRequestURI(), dT);
        }
    }

    @Override
    public void destroy() {/*NOP*/}
}
