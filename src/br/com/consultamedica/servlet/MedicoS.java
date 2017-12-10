package br.com.consultamedica.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consultamedica.dao.implemento.MedicoDAO;
import br.com.consultamedica.modelo.implemento.Medico;
import br.com.consultamedica.util.Criptografia;

@WebServlet("/medico")
public class MedicoS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MedicoS() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Medico medico;

		try {

			medico = new Medico();
			medico.setNome(request.getParameter(Medico.NM_ATR_Nome));
			medico.setEmail(request.getParameter(Medico.NM_ATR_Email));
			medico.setSenha(Criptografia.criptografar(request.getParameter(Medico.NM_ATR_Senha)));
			medico.setHospital(request.getParameter(Medico.NM_ATR_Hospital));
			medico.setCrm(request.getParameter(Medico.NM_ATR_Crm));
			medico.setEspecialidade(request.getParameter(Medico.NM_ATR_Especialidade));
			medico.validarDados();
			
			MedicoDAO.getInstancia().inserir(medico);

			request.setAttribute(Medico.NM_ENTIDADE, medico);

			request.getRequestDispatcher("medico/consultas.jsp").forward(request, response);

		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}

	}
	}
