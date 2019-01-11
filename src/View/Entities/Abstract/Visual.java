package View.Entities.Abstract;

import java.awt.*;

public abstract class Visual {

    protected Color realColor;
    protected Color representationColor;

    public abstract void draw(int i, int j, int radio, boolean flagRealColor, Graphics g);

    public Color getRealColor(){
        return realColor;
    }

    public Color getRepresentationColor(){
        return representationColor;
    }
}