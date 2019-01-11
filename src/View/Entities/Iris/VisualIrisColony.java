package View.Entities.Iris;

import Model.Abstraction.Colony;
import View.Entities.Abstract.Visual;
import View.Entities.Abstract.VisualGroup;

public class VisualIrisColony implements VisualGroup {

    private Visual [][] visualIrisAnts;

    public VisualIrisColony(Colony colony) {
        visualIrisAnts = new VisualIrisAnt[colony.getAnts().length][colony.getAnts()[0].length];
        for(int i=0;i<colony.getAnts().length;i++){
            for(int j=0;j<colony.getAnts()[i].length;j++) if(colony.getAnts()[i][j]!=null){
                visualIrisAnts[i][j] = new VisualIrisAnt(colony.getAnts()[i][j]);
            }
        }
    }

    @Override
    public Visual getElement(int x, int y) {
        return visualIrisAnts[x][y];
    }

}
