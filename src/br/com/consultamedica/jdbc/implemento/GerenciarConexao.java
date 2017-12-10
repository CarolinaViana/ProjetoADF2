package br.com.consultamedica.jdbc.implemento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.consultamedica.excecao.ControleConexao;
import br.com.consultamedica.jdbc.IGerenciarConexao;
import br.com.consultamedica.jdbc.implemento.GerenciarConexao;



public class GerenciarConexao implements br.com.consultamedica.jdbc.IGerenciarConexao {

	private static final String URL = "jdbc:mysql://localhost/consulta_medica";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private static IGerenciarConexao iGerenciarConexao;
	private static Connection conexao;

	private GerenciarConexao() {

	}

	/**
	 * Este método permite que esta classe só seja instanciada apenas uma vez
	 * Utilizando o conceito do <i>Design Pattern <b>Singleton</b></i>
	 * 
	 * @return instância da classe {@link GerenciadorConexao}
	 * @see <a href=
	 *      "https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples">Journal
	 *      Dev - Java Singleton</a>
	 */
	public static IGerenciarConexao getInstancia() {

		if (iGerenciarConexao == null) {
			synchronized (GerenciarConexao.class) {
				if (iGerenciarConexao == null)
					iGerenciarConexao = new GerenciarConexao();
			}
		}

		return iGerenciarConexao;
	}

	@Override
	public void abrirConexao() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(URL, USER, PASSWORD);
			conexao.setAutoCommit(Boolean.FALSE);
		} catch (ClassNotFoundException e) {
			throw new ControleConexao("Classe do Driver com.mysql.jdbc.Driver não encontrada");
		}
	}

	@Override
	public Connection getConexao() throws SQLException {
		if (conexao.isClosed())
			this.abrirConexao();

		return conexao;
	}

}
