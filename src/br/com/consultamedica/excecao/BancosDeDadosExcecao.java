package br.com.consultamedica.excecao;

public class BancosDeDadosExcecao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BancosDeDadosExcecao(String mensagem) {
		super(mensagem);
	}

}
