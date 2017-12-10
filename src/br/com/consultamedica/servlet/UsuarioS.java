package br.com.consultamedica.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consultamedica.dao.implemento.UsuarioDAO;
import br.com.consultamedica.modelo.implemento.Usuario;
import br.com.consultamedica.util.Criptografia;

@WebServlet("/usuario")
public class UsuarioS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioS() {
		super ();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario;

		try {

			usuario = new Usuario();
			usuario.setNome(request.getParameter(Usuario.NM_ATR_Nome));
			usuario.setEmail(request.getParameter(Usuario.NM_ATR_Email));
			usuario.setSenha(Criptografia.criptografar(request.getParameter(Usuario.NM_ATR_Senha)));
			usuario.setStatus(Usuario.ATIVO);
			usuario.setCartaoSus(request.getParameter(Usuario.NM_ATR_cartaoSus));
			usuario.validarDados();

			UsuarioDAO.getInstancia().inserir(usuario);

			request.setAttribute(Usuario.NM_ENTIDADE, usuario);

			request.getRequestDispatcher("usuario/login.jsp").forward(request, response);

		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}

	}
}