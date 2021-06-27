package jogo.view.boardview3d;

public class ComponenteInvalido extends RuntimeException {
	private static final long serialVersionUID = -1141734889807801432L;

	public ComponenteInvalido() {
	      super();
	   }

	   public ComponenteInvalido(String message) {
	      super(message);
	   }
}