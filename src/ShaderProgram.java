import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.*;
import org.lwjgl.util.vector.Matrix4f;

public class ShaderProgram {
	
	public int fragId;
	public int vertId;
	public int programId;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public ShaderProgram(String vertShader, String fragShader){
		String vertRaw = Utils.readFromFile("shaders/vert.txt");
		String fragRaw = Utils.readFromFile("shaders/frag.txt");
		
		programId = glCreateProgram();
		
		vertId = glCreateShader(GL_VERTEX_SHADER);
		fragId = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(vertId, vertRaw);
		glShaderSource(fragId, fragRaw);
		
		glCompileShader(vertId);
		if(glGetShaderi(vertId, GL_COMPILE_STATUS) == GL_FALSE){
			System.out.println(glGetShaderInfoLog(vertId) + " <- Log");
			System.err.println("Failed to compile vertex shader!");
		}
		
		glCompileShader(fragId);
		if(glGetShaderi(fragId, GL_COMPILE_STATUS) == GL_FALSE){
			System.err.println("Failed to compile fragment shader!");
		}
		
		glAttachShader(programId, vertId);
		glAttachShader(programId, fragId);
		
		glLinkProgram(programId);
		glValidateProgram(programId);
		
		System.out.println(glGetShaderInfoLog(programId) + " <- Log");
	}
	
	public static void loadMatrix(int location, Matrix4f matrix){
			matrix.store(matrixBuffer);
			matrixBuffer.flip();
			glUniformMatrix4fv(location, false, matrixBuffer);
	}

}
