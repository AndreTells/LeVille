package jogo.view.boardview3d.camera;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import jogo.view.mouse.IMouseObserver;

public class CameraDraggObserver implements IMouseObserver {
	private GLCamera camera;
	
	public CameraDraggObserver(GLCamera camera) {
		this.camera = camera;
	}

	@Override
	public boolean conditonIsMet(float pos_x, float pos_y) {
		return true;
	}

	@Override
	public void performAction(MouseEvent e, boolean missed) {
		if(!missed) {
			camera.move(e.getX(), e.getY());
		}
	}

	@Override
	public int getRank() {
		return 0;
	}

	    
	
}