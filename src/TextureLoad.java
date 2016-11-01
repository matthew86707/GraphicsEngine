import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;


public class TextureLoad {
	
	public int texID;
	
	public TextureLoad(String path) throws IOException{
		PNGDecoder decoder = new PNGDecoder(
			     TextureLoad.class.getResourceAsStream(path));
		
		ByteBuffer buf = ByteBuffer.allocateDirect(
			    4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
			buf.flip();
			
			// Create a new OpenGL texture 
			texID = GL11.glGenTextures();
			// Bind the texture
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
			//Unpack texture
			GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(),
				    decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

			
			
	}
	}


