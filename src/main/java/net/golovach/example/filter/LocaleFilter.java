package net.golovach.example.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

public class LocaleFilter extends BaseFilter {

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req) {
            public Locale getLocale() {
                return Locale.ENGLISH;    
            }
            public Enumeration<Locale> getLocales() {
                return new Vector<>(Collections.singleton(Locale.ENGLISH)).elements();
            }
        };
        chain.doFilter(requestWrapper, res);
    }
}
