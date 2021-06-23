package jogo.view.ui.leaf;

import com.jogamp.opengl.GL2;

import jogo.view.ui.GLElementComposite;
import jogo.view.ui.GLElementLeaf;

public class GLRectangle extends GLElementLeaf{

	private float border_radius;
	
	//trhow exception in case radius is bigger than width or height
	public GLRectangle(String id,GLElementComposite parent,
			float pos_x,float pos_y,
			float width,float height,
			float border_radius,float[] color) {
		super(id,parent,pos_x,pos_y,width,height,color,1f);
		
		this.border_radius = border_radius;
	}
	
	public GLRectangle(String id,GLElementComposite parent,float pos_x,float pos_y,
			float width,float height,float border_radius,float[] color,float z_index) {
		
		super(id,parent,pos_x,pos_y,width,height,color,z_index);
		
		this.border_radius = border_radius;
	}
	
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(pos_x, pos_y, z_index);
		gl.glColor4f(color[0], color[1], color[2], color[3]);
		
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		
		gl.glVertex2f(width/2.0f, height/2.0f);
		
		gl.glVertex2f(width, border_radius);
		gl.glVertex2f(width, height-border_radius);
		
		gl.glVertex2f((width-border_radius), (height-border_radius));
		gl.glVertex2f((width-border_radius), height);
		
		gl.glVertex2f(border_radius, height);
		gl.glVertex2f(border_radius, (height-border_radius));
		
		gl.glVertex2f(0, (height-border_radius));
		gl.glVertex2f(0, border_radius);
		
		gl.glVertex2f(border_radius,border_radius);
		gl.glVertex2f(border_radius, 0);
		
		gl.glVertex2f((width-border_radius), 0);
		gl.glVertex2f((width-border_radius), border_radius);
		
		gl.glVertex2f(width, border_radius);
		
		gl.glEnd();
		double start_angle = 0;
		double end_angle = (float) Math.PI/2;
		float[] side_x = new float[] {
				(width-border_radius),
				border_radius,
				border_radius,
				(width-border_radius)};
		float[] side_y = new float[] {
				(height-border_radius),
				(height-border_radius),
				border_radius,
				border_radius};
		
		for(int i=0;i<4;i++) {
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			
			gl.glVertex2f(side_x[i], side_y[i]);
			for(double j=start_angle;j<=end_angle;j+=Math.PI/60) {
				gl.glVertex2f(side_x[i]+(border_radius*((float)Math.cos(j))),
							side_y[i]+(border_radius*((float)Math.sin(j))));
			}
			gl.glEnd();
			
			
			start_angle+=Math.PI/2;
			end_angle+=Math.PI/2;
		}
		
		gl.glPopMatrix();
	}


}