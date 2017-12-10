package br.com.consultamedica.dao.implemento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.consultamedica.dao.IUsuarioDAO;
import br.com.consultamedica.jdbc.IGerenciarConexao;
import br.com.consultamedica.jdbc.implemento.GerenciarConexao;
import br.com.consultamedica.modelo.implemento.Usuario;
import br.com.consultamedica.excecao.BancosDeDadosExcecao;
import br.com.consultamedica.excecao.ControleConexao;


public class UsuarioDAO implements IUsuarioDAO {

	private static IUsuarioDAO iUsuarioDAO;
	private IGerenciarConexao iGerenciarConexao;

	private UsuarioDAO() {
		iGerenciarConexao = GerenciarConexao.getInstancia();
		
		
	}

	public static IUsuarioDAO getInstancia() {
		if (iUsuarioDAO== null) {
			synchronized (UsuarioDAO.class) {
				if (iUsuarioDAO == null) {
					iUsuarioDAO = new UsuarioDAO();
				}
			}
		}
		return iUsuarioDAO;
	}

	@Override
	public void inserir (Usuario usuario) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String sqlInsert;

		sqlInsert = "INSERT INTO USUARIO (USU_NOME, USU_EMAIL, USU_SENHA, USU_STATUS, USU_CARTAOSUS) VALUES (?, ?, ?, ?, ?)";

		try {

			this.iGerenciarConexao.abrirConexao();

			preparedStatement = this.iGerenciarConexao.getConexao().prepareStatement(sqlInsert,
					PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getSenha());
			preparedStatement.setBoolean(4, usuario.isStatus());
			preparedStatement.setString(5, usuario.getCartaoSus());
			
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();

			resultSet.next();

			usuario.setId(resultSet.getLong(PreparedStatement.RETURN_GENERATED_KEYS));

			this.iGerenciarConexao.getConexao().commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void alterar(Usuario usuario) {

		PreparedStatement pstmt = null;
		String sql = "UPDATE USUARIO SET USU_NOME = ?, USU_EMAIL = ? WHERE USU_ID = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getEmail());
			pstmt.setLong(3, usuario.getId());

			pstmt.execute();

		} catch (SQLException e) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(e.getMessage());
			} catch (SQLException eSqlException) {
				throw new ControleConexao(eSqlException.getMessage());
			}

		} finally {

			try {
				this.iGerenciarConexao.getConexao().commit();
				pstmt.close();
				this.iGerenciarConexao.fecharConexao(this.iGerenciarConexao.getConexao());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
			}

		}

	}

	@Override
	public void excluir(Usuario usuario) {

		PreparedStatement pstmt = null;
		String sql = "DELETE USUARIO WHERE USU_ID = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setLong(1, usuario.getId());

			pstmt.execute();

		} catch (SQLException e) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(e.getMessage());
			} catch (SQLException eSqlException) {
				throw new BancosDeDadosExcecao(eSqlException.getMessage());
			}

		} finally {

			try {
				this.iGerenciarConexao.getConexao().commit();
				pstmt.close();
				this.iGerenciarConexao.fecharConexao(this.iGerenciarConexao.getConexao());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
			}

		}

	}

	@Override
	public Usuario consultar(Usuario usuario) {

		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM USUARIO WHERE USU_ID = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setLong(1, usuario.getId());

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				usuario.setNome(resultSet.getString("USU_NOME"));
				usuario.setEmail(resultSet.getString("USU_EMAIL"));
				usuario.setSenha(resultSet.getString("USU_SENHA"));
				usuario.setStatus(resultSet.getBoolean("USU_STATUS"));
				usuario.setCartaoSus(resultSet.getString("USU_CARTAOSUS"));
			}

		} catch (SQLException e) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(e.getMessage());
			} catch (SQLException eSqlException) {
				throw new ControleConexao(eSqlException.getMessage());
			}

		} finally {

			try {
				this.iGerenciarConexao.getConexao().commit();
				pstmt.close();
				this.iGerenciarConexao.fecharConexao(this.iGerenciarConexao.getConexao());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
			}

		}

		return usuario;
	}


	@Override
	public boolean validarLogin(Usuario usuario) {

		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM USUARIO WHERE USU_EMAIL = ? AND USU_SENHA = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setString(1, usuario.getEmail());
			pstmt.setString(2, usuario.getSenha());

			ResultSet resultSet = pstmt.executeQuery();

			if (resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					usuario.setId(resultSet.getLong("USU_ID"));
					usuario.setNome(resultSet.getString("USU_NOME"));
					usuario.setEmail(resultSet.getString("USU_EMAIL"));
					usuario.setSenha(resultSet.getString("USU_SENHA"));
					usuario.setStatus(resultSet.getBoolean("USU_STATUS"));
					usuario.setCartaoSus(resultSet.getString("USU_CARTAOSUS"));
				}				
			} else {
				usuario = null;
			}

		} catch (SQLException e) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(e.getMessage());
			} catch (SQLException eSqlException) {
				throw new ControleConexao(eSqlException.getMessage());
			}

		} finally {

			try {
				this.iGerenciarConexao.getConexao().commit();
				pstmt.close();
				this.iGerenciarConexao.fecharConexao(this.iGerenciarConexao.getConexao());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
			}

		}

		return (usuario != null);
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
