package renderEngine.model;

/**
 * Created by sebi on 018 18/12/2016.
 */
public class RawModel {
    private int vaoID,vertexCount;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
