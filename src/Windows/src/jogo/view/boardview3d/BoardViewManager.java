package jogo.view.boardview3d;

import java.nio.FloatBuffer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.math.VectorUtil;

import jogo.controller.gamecontroller.CellController;
import jogo.view.boardview3d.camera.CameraDraggObserver;
import jogo.view.boardview3d.camera.CameraMotionObserver;
import jogo.view.boardview3d.camera.GLCamera;
import jogo.view.mouse.IMouseObserver;

public class BoardViewManager implements IBoard3DManager {
	private GLCamera camera;
	private CellView[][] cells;
	private final int map_size = 10;
	private RayPicker picker;
	
	public BoardViewManager() {
		this.setUpCamera();
		this.setUpCellMatrix();
		this.picker = new RayPicker(map_size);
	}	
	
	public void positionCamera(GL2 gl, GLU glu) {
		float[] eye = camera.getEye();
		float[] target = camera.getTarget();
		float[] up = camera.getUp();
		
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, FloatBuffer.wrap(new float[] {0+eye[0], 4f+eye[1], 3+eye[2],1}));
		glu.gluLookAt(eye[0], eye[1], eye[2], target[0], target[1], target[2], up[0], up[1], up[2]);
	}
	
	public void drawBoard(GL2 gl) {
		
		for(int i=0;i<map_size;i++) {
			for(int j=0;j<map_size;j++) {
				cells[i][j].draw(gl);
			}
	   }
		
	}
	
	public void updatePicker(Matrix4 i_view_matrix , Matrix4 i_projection_matrix) {
		picker.setEye(camera.getEye());
		picker.setInverseProjectionMatrix(i_projection_matrix);
		picker.setInverseViewMatrix(i_view_matrix);
		
	}
	
	protected void updateDetectionBox(int i,int j,float[] min,float[] max) {
		this.picker.setBoundsBox(i, j, min, max);
	}
	
	//getter methods
	public IMouseObserver getCameraDraggObserver() {
		return new CameraDraggObserver(camera);
	}
	
	public IMouseObserver getCameraMotionObserver() {
		return new CameraMotionObserver(camera);
	}
	
	public IMouseObserver getCellPicker() {
		return picker;
	}

	public ICellViewController getCell(int i, int j) {
		return cells[i][j];
	}

	//setUp methods
	private void setUpCellMatrix() {
		this.cells = new CellView[10][10];
		
		float dist = 4.0f;
		
		for(int i=0;i<map_size;i++) {
			for(int j=0;j<map_size;j++) {
				
				this.cells[i][j] = new CellView(this,i,j,new float[] {i*dist,0,j*dist}, Game3DObjectManager.getModel("Forest"));
			}
		}
	}
	
	private void setUpCamera() {
		float[] target = new float[] {0,0,0};
		float[] eye = new float[] {0,20,30};
		float[] up = new float[3];
		float [] right = new float[] {1,0,0};
		float [] aux = new float[3]; 
		VectorUtil.scaleVec3(aux, eye, -1);
		VectorUtil.addVec2(aux, aux, target);
		VectorUtil.crossVec3(up, right, aux);
		this.camera = new GLCamera(eye,target,up);
	}

	public void setCellActionObserver(int i,int j,CellController controller) {
		float[] min = this.cells[i][j].getAabbMin();
		float[] max = this.cells[i][j].getAabbMax();
		picker.addClickableObject(i,j,min, max, controller);
	}

	
}
