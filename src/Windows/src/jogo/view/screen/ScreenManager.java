package jogo.view.screen;

import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.mouse.IMouse;
import jogo.view.ui.IContainer;

public class ScreenManager implements IScreenManager{
	GameFrame frame;
	GameCanvas canvas;
	
	public ScreenManager() {
		this.frame = new GameFrame();
		this.canvas = new GameCanvas(frame.getWidth(),frame.getHeight());
		frame.add(canvas.getCanvas());
	   	frame.setVisible(true);
	}
	
	public void set2D(IContainer container) {
		//container.setDims(canvas.getCanvas().getWidth(), canvas.getCanvas().getWidth());
		canvas.set2DContainter(container);
	}
	
	public void set3D(IBoard3DManager board) {
		canvas.set3DBoard(board);
	}
	
	public void setMouse(IMouse mouse) {
		canvas.getCanvas().addMouseListener(mouse);
		canvas.getCanvas().addMouseMotionListener(mouse);
	}

}
