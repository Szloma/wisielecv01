package wisielecv01;

import java.util.HashMap;
import java.util.Map;


public class TextureMap {
	public static Map<Integer, String> createTextureMap() {
		   Map<Integer, String> texturePaths = new HashMap<>();
		   	texturePaths.put(7, "textures/blank.png");
	        texturePaths.put(1, "textures/texture6.png");
	        texturePaths.put(2, "textures/texture5.png");
	        texturePaths.put(3, "textures/texture4.png");
	        texturePaths.put(4, "textures/texture3.png");
	        texturePaths.put(5, "textures/texture2.png");
	        texturePaths.put(6, "textures/texture1.png");
	        return texturePaths;
    }
}
