import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;


public class Utils {
	
	public static String readFromFile(String path){
		StringBuffer res = new StringBuffer();
		try{
			BufferedReader read = new BufferedReader(new FileReader(path));
			String buffer = "";
			while((buffer = read.readLine())!= null){
				res.append(buffer);
				res.append("\n");
			}
			read.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return res.toString();
	}
	
	private static ByteBuffer createByteBuffer(byte[] array){
		ByteBuffer res = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		res.put(array).flip();
		return res;
	}
	
	private static FloatBuffer createFloatBuffer(float[] array){
		FloatBuffer res = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		res.put(array).flip();
		return res;
	}
	
	private static IntBuffer createIntBuffer(int[] array){
		IntBuffer res = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		res.put(array).flip();
		return res;
	}
	

}
