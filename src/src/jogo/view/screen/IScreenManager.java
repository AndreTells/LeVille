package jogo.view.screen;

import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.mouse.IMouse;
import jogo.view.ui.IContainer;

public interface IScreenManager {
	public void set2D(IContainer container);
	
	public void set3D(IBoard3DManager board);
	
	public void setMouse(IMouse mouse);
}
