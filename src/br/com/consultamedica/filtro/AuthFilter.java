package br.com.consultamedica.filtro;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.consultamedica.util.GerenciarSessao;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/usuario" })
public class AuthFilter implements Filter {

	GerenciarSessao gerenciarsessao;

	public AuthFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (!gerenciarsessao.isSessaoValida((HttpServletRequest) request)) {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher("usuario/listar-usuario.jsp").forward(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		gerenciarsessao = new GerenciarSessao();
	}

}
