package jogo.exceptions;

public class ArquivoNaoEncontrado extends RuntimeException {
	private static final long serialVersionUID = 5124134266526397317L;

	public  ArquivoNaoEncontrado() {
	      super();
	   }

	   public ArquivoNaoEncontrado(String message) {
	      super(message);
	   }
}