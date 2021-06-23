package jogo.view.boardview3d;

import com.jogamp.opengl.GL2;

import jogo.view.boardview3d.customobject.Obj3D;

public class CellView implements ICellViewController{
	private BoardViewManager board;
	private int map_i;
	private int map_j;
	private float pos[];
	private Obj3D obj;
	
	public CellView(BoardViewManager board,int map_i,int map_j,float [] pos, Obj3D obj) {
		this.board = board;
		this.map_i = map_i;
		this.map_j = map_j;
		
		this.pos = pos;
		this.obj = obj;
	}
	
	protected float[] getAabbMin() {
		float[] obj_aabb_min = obj.getAabbMin();
		return new float[] {obj_aabb_min[0]+pos[0],obj_aabb_min[1]+pos[1],obj_aabb_min[2]+pos[2]};
	}
	
	protected float[] getAabbMax() {
		float[] obj_aabb_max = obj.getAabbMax();
		return new float[] {obj_aabb_max[0]+pos[0],obj_aabb_max[1]+pos[1],obj_aabb_max[2]+pos[2]};
	}
	
	public void setObj(String obj_name) {
		Obj3D obj = Game3DObjectManager.getModel(obj_name);
		if(obj==null) {
			System.out.println(obj_name);
		}
		this.obj = obj;
		
		board.updateDetectionBox(map_i, map_j, this.getAabbMin(), this.getAabbMax());
	}

	protected void draw(GL2 gl){
		if(obj == null) {
			return;
		}
		
		gl.glPushMatrix();
		gl.glTranslatef(pos[0],pos[1],pos[2]);
		obj.draw(gl);
		gl.glPopMatrix();
	}

}
