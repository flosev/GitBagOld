package net.golovach.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PerfFilterShort extends BaseFilter {
    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        long startTime = System.nanoTime();
        try {
            chain.doFilter(req, res);
        } finally {
            long stopTime = System.nanoTime();
            long dT = stopTime - startTime;
            System.out.printf("uri:%s, dT:%d", req.getRequestURI(), dT);
        }
    }
}
