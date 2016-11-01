import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.glfw.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Model {
	
	private static final float FOV = 70;
	private static final float nearPlane = 0.001f;
	private static final float farPlane = 1000f;
	private static Matrix4f projectionMatrix;
	
	private Matrix4f transformation = MathStuff.createTransformationMatrix(new Vector3f(0, 0, - 7), 90.0f, 90f, 0f, 1f);
	
	private int drawCount;
	private int vboPosID;
	private int vboNormID;
	private int vaoID;
	private int prog;
	public static float offset = -2f;
	float[] mesh;
	
	private TextureLoad texture;
	
	public Model(float[] verts, int program){
	
		drawCount = verts.length / 3;
		this.prog = program;
		FloatBuffer fb = BufferUtils.createFloatBuffer(verts.length);
		fb.put(verts);
		fb.flip();
		mesh = verts;
		vaoID = setupVBOs();
		
		
		
	}
	
	public int setupVBOs(){
		
		int vao = glGenVertexArrays();
		glBindVertexArray(vao);	

		vboPosID = glGenBuffers();

		glBindBuffer(GL_ARRAY_BUFFER, vboPosID);
		glBufferData(GL_ARRAY_BUFFER, mesh, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

		 glBindBuffer(GL_ARRAY_BUFFER, 0);
		 
		 vboNormID = glGenBuffers();

			glBindBuffer(GL_ARRAY_BUFFER, vboNormID);
			glBufferData(GL_ARRAY_BUFFER, mesh, GL_STATIC_DRAW);
			glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

			 glBindBuffer(GL_ARRAY_BUFFER, 0);
		 
		glBindVertexArray(0);
		return vao;
		
	}
	
	public void render(){
		glBindVertexArray(vaoID);
		glUseProgram(this.prog);
		
		int locTrans = glGetUniformLocation(this.prog, "trans");
		ShaderProgram.loadMatrix(locTrans, transformation);
		
		int locProjection = glGetUniformLocation(this.prog, "projection");
		ShaderProgram.loadMatrix(locProjection, projectionMatrix);
		
		int locView = glGetUniformLocation(this.prog, "view");
		ShaderProgram.loadMatrix(locView, Camera.viewMatrix);
		
		int toFrag = glGetUniformLocation(this.prog, "col");
		glUniform1f(toFrag, offset);
		
		int lightPosLoc = glGetUniformLocation(this.prog, "lightPos");
		glUniform3f(lightPosLoc, 1, -6, 0);  
		
		offset += 0.01f;
		
		
		//transformation.translate(new Vector3f(0f, 0.01f, 0.007f));
		//transformation.translate(new Vector3f(0f, 0f, 0f), transformation);
		transformation.rotate(0.006f, new Vector3f(0, 1, 0));
		
		
		glEnableVertexAttribArray(0);
		
		
		
		glDrawArrays(GL_TRIANGLES, 0, drawCount);
		
		glDisableVertexAttribArray(0);
		glUseProgram(0);
		glBindVertexArray(0);
	}
	
	  public static void createProjectionMatrix(){
	        float aspectRatio = (float) Main.WIDTH / (float) Main.HEIGHT;
	        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
	        float x_scale = y_scale / aspectRatio;
	        float frustum_length = farPlane - nearPlane;
	 
	        projectionMatrix = new Matrix4f();
	        projectionMatrix.m00 = x_scale;
	        projectionMatrix.m11 = y_scale;
	        projectionMatrix.m22 = -((farPlane + nearPlane) / frustum_length);
	        projectionMatrix.m23 = -1;
	        projectionMatrix.m32 = -((2 * nearPlane * farPlane) / frustum_length);
	        projectionMatrix.m33 = 0;
	    }

}
