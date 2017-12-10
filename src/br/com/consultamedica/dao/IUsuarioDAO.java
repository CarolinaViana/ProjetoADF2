package br.com.consultamedica.dao;

import java.util.List;

import br.com.consultamedica.modelo.implemento.Usuario;

public interface IUsuarioDAO {

	public void inserir(Usuario usuario);

	public void alterar(Usuario usuario);

	public void excluir(Usuario usuario);

	public Usuario consultar(Usuario usuario);

	public List<Usuario> listar();

	public boolean validarLogin(Usuario usuario);
}
