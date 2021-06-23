package jogo.exceptions;

public class ArquivoNaoEncontrado extends RuntimeException {
	   public  ArquivoNaoEncontrado() {
	      super();
	   }

	   public ArquivoNaoEncontrado(String message) {
	      super(message);
	   }
}