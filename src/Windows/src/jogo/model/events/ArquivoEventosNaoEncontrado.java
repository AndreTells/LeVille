package jogo.model.events;

import jogo.exceptions.ArquivoNaoEncontrado;

public class ArquivoEventosNaoEncontrado extends ArquivoNaoEncontrado {
	private static final long serialVersionUID = -6215700773802937907L;

	public ArquivoEventosNaoEncontrado() {
	      super();
	   }

	   public ArquivoEventosNaoEncontrado(String message) {
	      super(message);
	   }
}