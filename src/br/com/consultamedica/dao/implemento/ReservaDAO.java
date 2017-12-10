package br.com.consultamedica.dao.implemento;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.consultamedica.jdbc.IGerenciarConexao;
import br.com.consultamedica.jdbc.implemento.GerenciarConexao;
import br.com.consultamedica.modelo.implemento.Reserva;
import br.com.consultamedica.dao.IReservaDAO;
import br.com.consultamedica.excecao.BancosDeDadosExcecao;
import br.com.consultamedica.excecao.ControleConexao;


public class ReservaDAO implements IReservaDAO {

	private static IReservaDAO iReservaDAO;
	private IGerenciarConexao iGerenciarConexao;

	private ReservaDAO() {
		iGerenciarConexao = GerenciarConexao.getInstancia();
		
		
	}

	public static IReservaDAO getInstancia() {
		if (iReservaDAO== null) {
			synchronized (ReservaDAO.class) {
				if (iReservaDAO == null) {
					iReservaDAO = new ReservaDAO();
				}
			}
		}
		return iReservaDAO;
	}

	@Override
	public void inserir (Reserva reserva) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String sqlInsert;

		sqlInsert = "INSERT INTO RESERVA (RES_DATA, RES_ESPECIALIDADE, RES_HOSPITALBAIRRO) VALUES (?, ?, ?)";

		try {

			this.iGerenciarConexao.abrirConexao();

			preparedStatement = this.iGerenciarConexao.getConexao().prepareStatement(sqlInsert,
					PreparedStatement.RETURN_GENERATED_KEYS);


			
			preparedStatement.setDate(1, (Date) reserva.getData());
			preparedStatement.setString(2, reserva.getEspecialidade());
			preparedStatement.setString(3, reserva.getHospitalbairro());
			
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();

			resultSet.next();

			reserva.setId(resultSet.getLong(PreparedStatement.RETURN_GENERATED_KEYS));

			this.iGerenciarConexao.getConexao().commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void alterar(Reserva reserva) {

		PreparedStatement pstmt = null;
		String sql = "UPDATE RESERVA SET RES_DATA = ?, RES_ESPECIALIDADE = ? WHERE RES_ID = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setDate(1, (Date) reserva.getData());
			pstmt.setString(2, reserva.getEspecialidade());
			pstmt.setLong(3, reserva.getId());

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
	public void excluir(Reserva reserva) {

		PreparedStatement pstmt = null;
		String sql = "DELETE RESERVA WHERE RES_ID = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setLong(1, reserva.getId());

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
	public Reserva consultar(Reserva reserva) {

		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM RESERVA WHERE RES_ID = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setLong(1, reserva.getId());

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				reserva.setData(resultSet.getDate("RES_DATA"));
				reserva.setEspecialidade(resultSet.getString("RES_ESPECIALIDADE"));
				reserva.setHospitalbairro(resultSet.getString("RES_HOSPITALBAIRRO"));
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

		return reserva;
	}


	

	@Override
	public List<Reserva> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
