package br.com.consultamedica.dao.implemento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.consultamedica.dao.IMedicoDAO;
import br.com.consultamedica.excecao.BancosDeDadosExcecao;
import br.com.consultamedica.excecao.ControleConexao;
import br.com.consultamedica.jdbc.IGerenciarConexao;
import br.com.consultamedica.jdbc.implemento.GerenciarConexao;
import br.com.consultamedica.modelo.implemento.Medico;

public class MedicoDAO implements IMedicoDAO {

	private static IMedicoDAO iMedicoDAO;
	private IGerenciarConexao iGerenciarConexao;

	private MedicoDAO() {
		iGerenciarConexao = GerenciarConexao.getInstancia();

	}
	
	

	public static IMedicoDAO getInstancia() {
		if (iMedicoDAO== null) {
			synchronized (MedicoDAO.class) {
				if (iMedicoDAO == null) {
					iMedicoDAO = new MedicoDAO();
				}
			}
		}
		return iMedicoDAO;
	}

	@Override
	public int inserir(Medico medico) {
		PreparedStatement preparedStatement = null;
		String sqlInsert = "INSERT INTO MEDICO (MED_NOME, MED_EMAIL, MED_SENHA, MED_HOSPITAL, MED_CRM, MED_ESPECIALIDADE) VALUES (?,?,?,?,?,?)";
		

		
		try {

			this.iGerenciarConexao.abrirConexao();

			preparedStatement = this.iGerenciarConexao.getConexao().prepareStatement(sqlInsert,
					PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, medico.getNome());
			preparedStatement.setString(2, medico.getEmail());
			preparedStatement.setString(3, medico.getSenha());
			preparedStatement.setString(4, medico.getHospital());
			preparedStatement.setString(5, medico.getCrm());
			preparedStatement.setString(6, medico.getEspecialidade());
			

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(PreparedStatement.RETURN_GENERATED_KEYS);
			
			//return resultSet.getInt(PreparedStatement.RETURN_GENERATED_KEYS);

		} catch (SQLException eSqlException) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(eSqlException.getMessage());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
			}

		} finally {

			try {
				this.iGerenciarConexao.getConexao().commit();
				preparedStatement.close();
				this.iGerenciarConexao.fecharConexao(this.iGerenciarConexao.getConexao());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
			}

		}

	}

	@Override
	public void alterar(Medico medico) {

		PreparedStatement pstmt = null;
		String sql = "UPDATE MEDICO SET MED_NOME = ?, MED_EMAIL = ? WHERE MED_ID = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setString(1, medico.getNome());
			pstmt.setString(2, medico.getEmail());
			pstmt.setLong(3, medico.getId());

			pstmt.execute();

		} catch (SQLException eSqlException) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(eSqlException.getMessage());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
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
	public void excluir(Medico medico) {

		PreparedStatement pstmt = null;
		String sql = "DELETE MEDICO WHERE MED_ID = ?";

		try {
			((IGerenciarConexao) this.iGerenciarConexao).abrirConexao();


			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setLong(1, medico.getId());

			pstmt.execute();

		} catch (SQLException eSqlException) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(eSqlException.getMessage());
			} catch (SQLException e) {
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
	public Medico consultar(Medico medico) {

		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM MEDICO WHERE MED_ID = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setLong(1, medico.getId());

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				medico.setNome(resultSet.getString("MED_NOME"));
				medico.setEmail(resultSet.getString("MED_EMAIL"));
				medico.setSenha(resultSet.getString("MED_SENHA"));
				medico.setHospital(resultSet.getString("MED_HOSPITAL"));
				medico.setCrm(resultSet.getString("MED_CRM"));
				medico.setEspecialidade(resultSet.getString("MED_ESPECIALIDADE"));

			}

		} catch (SQLException eSqlException) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(eSqlException.getMessage());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
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

		return medico;
	}

	
	@Override
	public List<Medico> listar() {

		PreparedStatement pstmt = null;
		List<Medico> medicos = null;
		Medico medico = null;
		String sql = "SELECT * FROM MEDICO AS R INNER JOIN RESERVA AS M ON R.MED_RES_ID = RES_ID";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();

			medicos = new ArrayList<Medico>();

			while (resultSet.next()) {

				medico = new Medico();
				medico.setId(resultSet.getLong("R.USU_ID"));
				medico.setNome(resultSet.getString("R.USU_NOME"));
				medico.setEmail(resultSet.getString("R.USU_EMAIL"));
				medico.setSenha(resultSet.getString("R.USU_SENHA"));

				

				medicos.add(medico);
			}

		} catch (SQLException eSqlException) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(eSqlException.getMessage());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
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

		return medicos;
	}

	@Override
	public boolean validarLogin(Medico medico) {

		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM MEDICO WHERE MED_EMAIL = ? AND MED_SENHA = ?";

		try {

			this.iGerenciarConexao.abrirConexao();

			pstmt = this.iGerenciarConexao.getConexao().prepareStatement(sql);

			pstmt.setString(1, medico.getEmail());
			pstmt.setString(2, medico.getSenha());

			ResultSet resultSet = pstmt.executeQuery();

			if (resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					medico.setId(resultSet.getLong("MED_ID"));
					medico.setNome(resultSet.getString("MED_NOME"));
					medico.setEmail(resultSet.getString("MED_EMAIL"));
					medico.setSenha(resultSet.getString("MED_SENHA"));
					medico.setHospital(resultSet.getString("MED_HOSPITAL"));
					medico.setCrm(resultSet.getString("MED_CRM"));
					medico.setEspecialidade(resultSet.getString("MED_ESPECIALIDADE"));


					
				}				
			} else {
				medico = null;
			}

		} catch (SQLException eSqlException) {

			try {
				this.iGerenciarConexao.getConexao().rollback();
				throw new BancosDeDadosExcecao(eSqlException.getMessage());
			} catch (SQLException e) {
				throw new ControleConexao(e.getMessage());
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

		return (medico != null);
	}

}
