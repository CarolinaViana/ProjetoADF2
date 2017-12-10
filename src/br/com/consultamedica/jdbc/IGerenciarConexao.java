package br.com.consultamedica.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface IGerenciarConexao {
	/**
	 * Este método abre uma conexão com o banco de dados
	 * 
	 * @throws SQLException
	 *             caso haja um problema durante a abertura da conexão
	 */
	public void abrirConexao() throws SQLException;

	/**
	 * Este método retorna uma conexão com o banco de dados
	 * 
	 * @return conexão com o banco de dados
	 */
	public Connection getConexao() throws SQLException;

	/**
	 * Este método fecha uma conexão com o banco de dados
	 * 
	 * @param conexao
	 *            conexão a ser fechada
	 * @throws SQLException
	 *             caso haja um problema durante o close da conexão
	 */
	default void fecharConexao(Connection conexao) throws SQLException {

		if ((conexao != null) && (!conexao.isClosed()))
			conexao.close();
	}
}


