import java.util.Vector;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;


public class MathStuff {
	
	public static Matrix4f createTransformationMatrix(Vector3f trans, float rx, float ry, float rz, float scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(trans, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}
	
	 public static Matrix4f createViewMatrix(Vector3f pos, float pitch, float roll, float yaw) {
	        Matrix4f viewMatrix = new Matrix4f();
	        viewMatrix.setIdentity();
	        Matrix4f.rotate((float) Math.toRadians(pitch), new Vector3f(1, 0, 0), viewMatrix,
	                viewMatrix);
	        Matrix4f.rotate((float) Math.toRadians(yaw), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
	        Vector3f cameraPos = pos;
	        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
	        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
	        return viewMatrix;
	    }

}
