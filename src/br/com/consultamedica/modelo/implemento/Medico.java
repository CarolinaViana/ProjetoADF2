package br.com.consultamedica.modelo.implemento;

import br.com.consultamedica.excecao.DadosInvalidos;
import br.com.consultamedica.modelo.IModelo;

public class Medico implements IModelo {

	public static final String NM_ENTIDADE = "br.com.consultamedica.modelo.implemento.Medico";
	public static final String NM_ATR_Nome = "nome";
	public static final String NM_ATR_Email = "email";
	public static final String NM_ATR_Senha = "senha";
	public static final String NM_ATR_Hospital = "hospital";
	public static final String NM_ATR_Crm = "crm";
	public static final String NM_ATR_Especialidade = "especialidade";

	private long id;
	private String nome;
	private String email;
	private String senha;
	private String hospital;
	private String crm;
	private String especialidade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;

	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	// Convertendo para String
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", hospital="
				+ hospital + ", crm=" + crm + ",especialidade= " + especialidade +  "]";
	}

	// Validar para o login
	@Override
	public void validarDados() {

		if (this.getNome() == null || this.getNome().isEmpty()) {
			throw new DadosInvalidos("Nome está vazio ou incompleto");
		} else if (this.getEmail() == null || this.getEmail().isEmpty()) {
			throw new DadosInvalidos("E-mail está nulo ou vazio");
		} else if (this.getSenha() == null || this.getSenha().isEmpty()) {
			throw new DadosInvalidos("Senha está vazia ou incompleta");
		}

	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}


}
