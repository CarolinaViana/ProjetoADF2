package br.com.consultamedica.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.consultamedica.modelo.implemento.Usuario;

public class GerenciarSessao {
	public void novaSessao(HttpServletRequest request, Usuario usuario) {

		HttpSession httpSession;

		httpSession = request.getSession();

		httpSession.setAttribute(Usuario.NM_ENTIDADE, usuario);

	}

	public boolean isSessaoValida(HttpServletRequest request) {
		return (request.getSession().getAttribute(Usuario.NM_ENTIDADE) != null);
	}

	public void destruirSessao(HttpServletRequest request) {
		request.getSession().invalidate();
	}

}
