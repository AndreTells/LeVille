package jogo.view.ui;

import com.jogamp.opengl.GL2;

public interface IContainer {
	public void draw(GL2 gl);
	public void setDims(float width,float height);
}
