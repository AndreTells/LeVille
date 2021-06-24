package jogo.view.boardview3d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.Matrix4;

import jogo.controller.gamecontroller.CellController;
import jogo.view.mouse.IMouseObserver;

public interface IBoard3DManager {
	public void positionCamera(GL2 gl, GLU glu);
	
	public void drawBoard(GL2 gl);
	
	public void updatePicker(Matrix4 i_view_matrix , Matrix4 i_projection_matrix);
	
	public ICellViewController getCell(int i, int j);
	
	public void setCellActionObserver(int i,int j,CellController controller);
	
	public IMouseObserver getCameraDraggObserver();
	
	public IMouseObserver getCameraMotionObserver();
	
	public IMouseObserver getCellPicker();
}
