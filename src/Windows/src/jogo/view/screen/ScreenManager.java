package jogo.view.screen;

import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.mouse.IMouse;
import jogo.view.ui.IContainer;

public class ScreenManager implements IScreenManager{
	private GameFrame frame;
	private GameCanvas canvas;
	private int set_up_progress;
	
	public ScreenManager() {
		this.frame = new GameFrame();
		this.canvas = new GameCanvas(frame.getWidth(),frame.getHeight());
		while(canvas.getCanvas()==null);
		frame.add(canvas.getCanvas());
	   	frame.setVisible(true);
	}
	
	public void set2D(IContainer container) {
		canvas.set2DContainter(container);
		set_up_progress+= 1;
		updateSetUpStatus();
	}
	
	public void set3D(IBoard3DManager board) {
		canvas.set3DBoard(board);
		set_up_progress+= 1;
		updateSetUpStatus();
	}
	
	public void setMouse(IMouse mouse) {
		canvas.getCanvas().addMouseListener(mouse);
		canvas.getCanvas().addMouseMotionListener(mouse);
		set_up_progress+= 1;
		updateSetUpStatus();
	}

	private void updateSetUpStatus() {
		if(set_up_progress == 3) {
			canvas.setUpFinished();
		}
	}
	
}
