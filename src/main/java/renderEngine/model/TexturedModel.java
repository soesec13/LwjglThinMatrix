package renderEngine.model;

import renderEngine.entities.Entity;

/**
 * Created by sebi on 023 23/12/2016.
 */
public class TexturedModel {
    private Entity ent;
    private Texture tex;

    public TexturedModel(Entity ent, Texture tex) {
        this.ent = ent;
        this.tex = tex;
    }

    public Entity getEnt() {
        return ent;
    }

    public Texture getTex() {
        return tex;
    }
}
