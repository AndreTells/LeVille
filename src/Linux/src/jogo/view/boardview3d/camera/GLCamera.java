package jogo.view.boardview3d.camera;

import com.jogamp.opengl.math.VectorUtil;

import jogo.view.mouse.GLMouse;

public class GLCamera{
	private float[] eye;
	private float[] target;
	private float[] up;

	private float old_mouse_x;
	private float old_mouse_y;
	
	public GLCamera(float[] eye, float[] target, float[] up) {
		this.eye = eye;
		this.target = target;
		this.up = up;
	}
	
	public float[] getEye() {
		return eye;
	}

	public float[] getTarget() {return target;}
	
	public float[] getUp() {return up;}
	
	protected void setOldMousePosition(float x, float y) {
		this.old_mouse_x = x;
		this.old_mouse_y = y;
	}
	
	public void move(float new_x, float new_y) {
		float[] delta = new float[] {(old_mouse_x-new_x)/50 , 0,  (old_mouse_y-new_y)/50};
		
		VectorUtil.addVec3(eye, delta, eye);
		VectorUtil.addVec3(target, delta, target);
		
		this.old_mouse_x = new_x;
		this.old_mouse_y = new_y;
	}

	public void setCameraMover(GLMouse mouse) {
		mouse.addMotionObservers("camera", new CameraMotionObserver(this));
		mouse.addDraggObservers("camera", new CameraDraggObserver(this));
	}

}