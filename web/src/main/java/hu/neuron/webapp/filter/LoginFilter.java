package hu.neuron.webapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session =((HttpServletRequest)servletRequest).getSession();
        if(session.getAttribute("user") != null){

            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            ((HttpServletResponse)servletResponse).sendRedirect("login.jsp");

        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
