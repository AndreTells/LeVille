package jogo.exceptions;

public class AtributoInvalido extends RuntimeException {
	   public AtributoInvalido() {
	      super();
	   }

	   public AtributoInvalido(String message) {
	      super(message);
	   }
	}