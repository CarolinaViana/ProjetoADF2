package br.com.consultamedica.excecao;

public class DadosInvalidos extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DadosInvalidos(String mensagem) {
		System.out.println(mensagem);
	}

}
