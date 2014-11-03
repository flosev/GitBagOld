package net.flosev.quiz.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Базовый удобный предок для моих реализаций Filter
// 1. Реализует пустые init()/destroy()
// 2. Реализует Template Method (GoF pattern) для приведения
// ServletRequest -> HttpServletRequest
// ServletResponse -> HttpServletResponse
public abstract class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // NOP
    }

    // Template Method: реализую doFilter через не существующий пока doHttpFilter
    // Удобство: потомок будет работать уже с HTTP-сущностями
    // Удобство: Убрал из всех потомков надоедливое приведение
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doHttpFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    protected abstract void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    public void destroy() {
        // NOP
    }
}
