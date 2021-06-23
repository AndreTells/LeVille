package jogo.view.ui.leaf;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import jogo.view.ui.GLElementComposite;
import jogo.view.ui.GLElementLeaf;

public class GLLabel extends GLElementLeaf{
	//<TODO> Text bounding box
	private String[] text;
	
	private final static float line_spacing = 0.05f;
	private final int font = GLUT.BITMAP_HELVETICA_12;
	private GLUT glut;
	
	public GLLabel(String id,GLElementComposite parent,
			float pos_x,float pos_y,
			String text,float[] color) {
		
		super(id,parent,pos_x,pos_y,0,0,color,1f);
		
		this.text =text.split("\n");
		
		this.glut = new GLUT();
		
		float width = glut.glutBitmapLength(font,"");
		for(String sentence: this.text) {
			float sentence_width = glut.glutBitmapLength(font,sentence);
			if(width < sentence_width) {
				width = sentence_width;
			}
		}
		float[] top_dims = this.getTop().getDims();
		
		this.setDims(width*2/top_dims[0], line_spacing*this.text.length);
	}
	
	public GLLabel(String id,GLElementComposite parent,
			float pos_x,float pos_y,
			String text,float[] color,float z_index) {
		
		super(id,parent,pos_x,pos_y,0,0,color,z_index);
		
		this.text =text.split("\n");
		
		this.glut = new GLUT();
		
		float width = glut.glutBitmapLength(font,"");
		
		for(String sentence: this.text) {
			float sentence_width = glut.glutBitmapLength(font,sentence);
			if(width < sentence_width) {
				width = sentence_width;
			}
		}
		
		float[] top_dims = this.getTop().getDims();
		this.setDims(width*2/top_dims[0], line_spacing*this.text.length);
	}
	
	public void draw(GL2 gl) {
		gl.glColor4f(color[0],color[1],color[2],color[3]);
		
		float y = this.pos_y+(line_spacing)*(text.length -1);
		for(String sentence: text) {
			gl.glPushMatrix();
			gl.glTranslatef(pos_x, y, 0);
			gl.glRasterPos2f(0, 0);
			
			glut.glutBitmapString(font, sentence);
			gl.glPopMatrix();
			
			y-=line_spacing;
		}
		
	}
	
	public void setText(String text) {
		this.text = text.split("\n");
		
		float width = glut.glutBitmapLength(font,"");
		for(String sentence: this.text) {
			float sentence_width = glut.glutBitmapLength(font,sentence);
			if(width < sentence_width) {
				//System.out.println(sentence_width);
				width = sentence_width;
			}
		}
		float[] top_dims = this.getTop().getDims();
		this.setDims(width/top_dims[0], line_spacing*this.text.length);
	}
	
}
