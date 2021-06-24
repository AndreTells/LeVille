package jogo.view.boardview3d.customobject;

import java.awt.Color;
import java.nio.FloatBuffer;

import com.jogamp.opengl.GL2;

public class Face3D {
	private float[] vec_1;
	private float[] vec_2;
	private float[] vec_3;
	private Color color;
	private float[] normal;
	public Face3D(float[] vec_1,float[] vec_2,float[] vec_3,Color color,float[] normal) {
		this.vec_1 = vec_1;
		this.vec_2 = vec_2;
		this.vec_3 = vec_3;
		this.color = color;
		this.normal = normal;
	}
	public void addGL(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, FloatBuffer.wrap(new float[] {color.getRed()/256f, color.getGreen()/256f, color.getBlue()/256f,1f}) );
		gl.glNormal3f(normal[0], normal[1], normal[2]);
		
		gl.glVertex3f(vec_1[0], vec_1[1], vec_1[2]);
		gl.glVertex3f(vec_2[0], vec_2[1], vec_2[2]);
		gl.glVertex3f(vec_3[0], vec_3[1], vec_3[2]);
		
		gl.glEnd();
	}
	
	public String toString() {
		return "f "+vec_1+" | "+vec_2+" | "+vec_3;
	}
}
