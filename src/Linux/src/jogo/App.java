package jogo;

import jogo.controller.builder.Builder;
import jogo.model.boardmodel.BoardManager;
import jogo.model.events.EventManager;
import jogo.view.boardview3d.BoardViewManager;
import jogo.view.mouse.GLMouse;
import jogo.view.screen.ScreenManager;
import jogo.view.ui.UIManager;

public class App {
	public static void main( String[] args ) {
		Builder builder = new Builder();
		
		builder.connect(new BoardManager());
		builder.connect(new ScreenManager());	
		
		builder.connect(new UIManager());
		builder.connect(new GLMouse());
		
		builder.connect(new BoardViewManager());
		builder.connect(new EventManager());
		
		builder.buildGame();

	}
}
