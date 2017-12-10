package br.com.consultamedica.dao;

import java.util.List;

import br.com.consultamedica.modelo.implemento.Reserva;

public interface IReservaDAO {

	public void inserir(Reserva reserva);

	public void alterar(Reserva reserva);

	public void excluir(Reserva reserva);

	public Reserva consultar(Reserva reserva);

	public List<Reserva> listar();

}
