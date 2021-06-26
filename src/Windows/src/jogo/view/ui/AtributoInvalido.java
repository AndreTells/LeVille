package jogo.view.ui;

public class AtributoInvalido extends RuntimeException {
	private static final long serialVersionUID = -2103637942045892783L;

	public AtributoInvalido() {
	      super();
	   }

	   public AtributoInvalido(String message) {
	      super(message);
	   }
	}