package jogo.view.boardview3d.camera;

import java.awt.event.MouseEvent;

import jogo.view.mouse.IMouseObserver;

public class CameraMotionObserver implements IMouseObserver{
	private GLCamera camera;
	
	
	public CameraMotionObserver(GLCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public boolean conditonIsMet(float pos_x, float pos_y) {
		return true;
	}

	@Override
	public void performAction(MouseEvent e, boolean missed) {
		camera.setOldMousePosition(e.getX(), e.getY());
	}

	@Override
	public int getRank() {
		return 0;
	}

}
