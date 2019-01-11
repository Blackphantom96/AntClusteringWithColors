package View.Entities.Iris;

import Model.Abstraction.Item;
import View.Entities.Abstract.Visual;
import utiles.LAB;

import java.awt.*;

public class VisualIrisItem extends  Visual {

    private static final Color SETOSA = Color.BLUE;
    private static final Color VERSICOLOR = Color.GREEN;
    private static final Color VIRGINICA = Color.RED;

    public VisualIrisItem(Item<double[]> item) {
        super();
        double [] prop = item.getProperties();
        LAB tempRealColor = new LAB(50.0,(prop[3]*256.0/2.5)-128.0,(prop[2]*256.0/7.0)-128.0);
        representationColor = tempRealColor.toRGB();
        switch (item.getRealClass()){
            case "Iris-setosa":
                realColor = SETOSA;
                break;
            case "Iris-versicolor":
                realColor = VERSICOLOR;
                break;
            case "Iris-virginica":
                realColor = VIRGINICA;
                break;
            default:
                realColor = Color.MAGENTA; // si esto aparece algo anda mal
        }
    }

    @Override
    public void draw(int i, int j, int radio, boolean flagRealColor, Graphics g) {
        g.setColor(flagRealColor ? realColor : representationColor);
        g.fillOval(i,j,radio,radio);
    }

}
