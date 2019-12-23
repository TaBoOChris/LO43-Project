package main.utiles;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

public class Input {

    private long window;
    private boolean[] keys;

    public Input(long window){
        this.window = window;
        this.keys = new boolean[GLFW_KEY_LAST];
        for (int i = 0 ; i< GLFW_KEY_LAST ; i++){
            keys[i] = false;
        }
    }

    public boolean isKeyDown(int key){
        return glfwGetKey(window, key) == 1;
    }

    public boolean isKeyPressed(int key) {
        return (isKeyDown(key) && !keys[key]);
    }

    public boolean isKeyReleased(int key) {
        return (!isKeyDown(key) && keys[key]);
    }


    public void update(){
        // obligé de commencé a 32
        for (int i = 32 ; i < GLFW_KEY_LAST  ; i++){
            keys[i] = isKeyDown(i);
        }
    }
}
