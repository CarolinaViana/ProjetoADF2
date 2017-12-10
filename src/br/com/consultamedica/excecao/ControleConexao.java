package br.com.consultamedica.excecao;

public class ControleConexao extends RuntimeException {
	 
	private static final long serialVersionUID = 1L;
 
	public ControleConexao(String mensagem) {
		// super(mensagem);
		System.out.println(mensagem);
	}

}
