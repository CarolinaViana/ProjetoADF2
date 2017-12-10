package br.com.consultamedica.dao;

import java.util.List;

import br.com.consultamedica.modelo.implemento.Medico;

public interface IMedicoDAO {

	public int inserir(Medico medico);

	public void alterar(Medico medico);

	public void excluir(Medico medico);

	public Medico consultar(Medico medico);

	public List<Medico> listar();

	public boolean validarLogin(Medico medico);
}
