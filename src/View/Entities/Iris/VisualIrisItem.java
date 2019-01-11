package View.Entities.Iris;

import Model.Abstraction.Item;
import View.Entities.Abstract.Visual;
import utiles.LAB;

import java.awt.*;

public class VisualIrisItem extends  Visual {

    public VisualIrisItem(Item<double[]> item) {
        super();
        representationColor = new LAB(50.0,item.getProperties()[0]*item.getProperties()[1],item.getProperties()[2]).toRGB();
        realColor = Color.BLUE; //TODO Anzola tiene que implementar el getRealClass
    }

    @Override
    public void draw(int i, int j, int radio, boolean flagRealColor, Graphics g) {
        g.setColor(flagRealColor ? realColor : representationColor);
        g.fillOval(i,j,radio,radio);
    }

}
