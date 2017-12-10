package br.com.consultamedica.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consultamedica.dao.implemento.ReservaDAO;
import br.com.consultamedica.modelo.implemento.Reserva;


@WebServlet("/reserva")
public class ReservaS extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ReservaS() {
    }
    
    	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {

    	}

    	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {

    		Reserva reserva;

    	    reserva = new Reserva();
    	    reserva.setData((request.getParameter(Reserva.NM_ATR_DATA)));
    	    reserva.setEspecialidade((request.getParameter(Reserva.NM_ATR_ESPECIALIDADE)));
    	    reserva.setHospitalbairro((request.getParameter(Reserva.NM_ATR_HOSPITALBAIRRO)));
    		
    		

    		ReservaDAO.getInstancia().inserir(reserva);

    		request.setAttribute(Reserva.NM_ATR_DATA, reserva);
    		

    		request.getRequestDispatcher("reservas/finalReserva.jsp").forward(request, response);

    	}

}
