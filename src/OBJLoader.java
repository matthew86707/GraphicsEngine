import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;


public class OBJLoader {
	
	public static float[] loadObj(String modelPath) throws Exception{
		int lineNum = 0;
		BufferedReader reader = new BufferedReader(new FileReader(modelPath));
		
		ArrayList<Vector3f> initVerts = new ArrayList<>();
		ArrayList<Vector3f> finalVerts = new ArrayList<>();
		
		String line = reader.readLine(); lineNum++;
		while(line.startsWith("v")){
			//line.replaceFirst("v ", "");
		
			String[] coords = line.split(" ");
			System.out.println(lineNum);
			initVerts.add(new Vector3f((Float.parseFloat(coords[1])), (Float.parseFloat(coords[2])), (Float.parseFloat(coords[3]))));
			System.out.println("Loading : " + coords[1] + " , " + coords[2] + " , " + coords[3]);
			line = reader.readLine(); lineNum++;
		}
		while(!line.startsWith("f")){
			line = reader.readLine(); lineNum++;
		}
		
		while(line != null && line.startsWith("f")){
			//line.replaceFirst("f ", "");
			String[] faces = line.split(" ");
			System.out.println(lineNum);
			finalVerts.add(initVerts.get(Integer.parseInt(faces[1]) - 1));
			finalVerts.add(initVerts.get(Integer.parseInt(faces[2]) - 1));
			finalVerts.add(initVerts.get(Integer.parseInt(faces[3]) - 1));
			
			line = reader.readLine(); lineNum++;
		}
		
		float[] verts = new float[finalVerts.size() * 3];
		int vertsIndex = 0;
		for(int i = 0; i < finalVerts.size(); i++){
			verts[vertsIndex] = finalVerts.get(i).x;
			verts[vertsIndex + 1] = finalVerts.get(i).y;
			verts[vertsIndex + 2] = finalVerts.get(i).z;
			
			vertsIndex += 3;
		}
		
		return verts;
	}
}
