package hu.neuron.webapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = "LoginServlet")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("authenticated").equals(true)){

            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            RequestDispatcher rd = servletRequest.getRequestDispatcher("login.html");
            rd.forward(servletRequest,servletResponse);

        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
