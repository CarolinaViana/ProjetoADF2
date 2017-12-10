package br.com.consultamedica.modelo.implemento;

import br.com.consultamedica.excecao.DadosInvalidos;
import br.com.consultamedica.modelo.IModelo;

public class Usuario implements IModelo {

	public static final String NM_ENTIDADE = "br.com.consultamedica.modelo.implemento.Usuario";
	public static final boolean ATIVO = Boolean.TRUE;
	public static final boolean INATIVO = Boolean.FALSE;
	public static final String ATIVO_DECRICAO = "ATIVO";
	public static final String INATIVO_DECRICAO = "INATIVO";

	public static final String NM_ATR_Nome = "nome";
	public static final String NM_ATR_Email = "email";
	public static final String NM_ATR_Senha = "senha";
	public static final String NM_ATR_cartaoSus = "cartaoSus";
	
	private long id;
	private String nome;
	private String email;
	private String senha;
	private boolean status;
	private String cartaoSus;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	// Convertendo para String
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", status=" + status
				+ ", cartaoSus=" + cartaoSus + "]";
	}

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

	public String getCartaoSus() {
		return cartaoSus;
	}

	public void setCartaoSus(String cartaoSus) {
		this.cartaoSus = cartaoSus;
	}
}
