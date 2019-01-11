package View.Entities.Iris;
import Model.Abstraction.Ant;
import View.Entities.Abstract.Visual;

import java.awt.*;

public class VisualIrisAnt extends Visual {

    private VisualIrisItem irisItem;

    public VisualIrisAnt(Ant<double[]> ant) {
        super();
        realColor = Color.BLACK;
        representationColor = Color.BLACK;
        if (ant.hasPayload()){
            irisItem = new VisualIrisItem(ant.getItem());
            realColor = irisItem.getRealColor().brighter().brighter().brighter();
            representationColor = irisItem.getRepresentationColor().brighter().brighter().brighter();
        }
    }

    @Override
    public void draw(int i, int j, int radio, boolean flagRealColor, Graphics g) {
        g.setColor(flagRealColor ? realColor : representationColor);
        g.fillRect(i,j,radio,radio);
    }
}
