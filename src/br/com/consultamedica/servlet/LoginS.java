package br.com.consultamedica.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consultamedica.dao.IUsuarioDAO;
import br.com.consultamedica.dao.implemento.UsuarioDAO;
import br.com.consultamedica.modelo.implemento.Usuario;
import br.com.consultamedica.servlet.LoginS;
import br.com.consultamedica.util.GerenciarSessao;
import br.com.consultamedica.util.Criptografia;

@WebServlet("/LoginS")
public class LoginS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String NM_MENSAGEM = "mensagemLogin";
	public static final String PATH_INDEX_JSP = "usuario/login.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("reservas/incluirReservas.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario;
		IUsuarioDAO iUsuarioDAO;

		try {
			usuario = new Usuario();
			usuario.setEmail(request.getParameter(Usuario.NM_ATR_Email));
			usuario.setSenha(Criptografia.criptografar(request.getParameter(Usuario.NM_ATR_Senha)));
			iUsuarioDAO = UsuarioDAO.getInstancia();

			if (iUsuarioDAO.validarLogin(usuario)) {
				usuario = iUsuarioDAO.consultar(usuario);

				GerenciarSessao gerenciarSessao = new GerenciarSessao();
				
				gerenciarSessao.novaSessao(request, usuario);

				request.getRequestDispatcher("reservas/reservas.jsp").forward(request, response);

			} else {
				request.setAttribute(LoginS.NM_MENSAGEM, "Login incorreto");
				request.getRequestDispatcher(LoginS.PATH_INDEX_JSP).forward(request, response);
			}

		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}

	}
}
