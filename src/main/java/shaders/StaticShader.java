package shaders;

import org.joml.Matrix4f;
import renderEngine.entities.Camera;
import util.Maths;

/**
 * Created by sebi on 021 21/12/2016.
 */
public class StaticShader extends ShaderProgram{
    private static final String VERTEX_FILE = "src/main/java/shaders/vertexShader.vert";
    private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShader.frag";
    private int loc_transformationMatrix;
    private int loc_projectionMatrix;
    private int loc_viewMatrix;
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"position");
        super.bindAttribute(1,"textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        loc_transformationMatrix = super.getUniformLocation("transformationMatrix");
        loc_projectionMatrix = super.getUniformLocation("projectionMatrix");
        loc_viewMatrix = super.getUniformLocation("viewMatrix");
    }
    public void loadTransformationMatrix(Matrix4f matrix)
    {
        super.loadMatrix(loc_transformationMatrix,matrix);
    }
    public void loadProjectionMatrix(Matrix4f matrix){super.loadMatrix(loc_projectionMatrix,matrix);}
    public void loadViewMatrix(Camera cam){
        Matrix4f matrix = Maths.createViewMatrix(cam);
        super.loadMatrix(loc_viewMatrix,matrix);
    }
}
