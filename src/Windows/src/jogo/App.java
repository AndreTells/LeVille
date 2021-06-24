package jogo;

import jogo.view.boardview3d.BoardViewManager;
import jogo.view.mouse.GLMouse;
import jogo.view.screen.GameFrame;
import jogo.view.screen.ScreenManager;
import jogo.view.ui.UIManager;
import jogo.view.ui.leaf.GLRectangle;
import jogo.controller.builder.Builder;
import jogo.model.boardmodel.BoardManager;
import jogo.model.boardmodel.BoardModel;
import jogo.model.events.EventManager;

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
