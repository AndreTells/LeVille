package jogo.exceptions;

public class ComponenteInvalido extends RuntimeException {
	   public ComponenteInvalido() {
	      super();
	   }

	   public ComponenteInvalido(String message) {
	      super(message);
	   }
	}