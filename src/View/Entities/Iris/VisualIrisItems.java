package View.Entities.Iris;

import Model.Abstraction.Ant;
import Model.Abstraction.Item;
import Model.Impl.Iris.IrisItem;
import View.Entities.Abstract.Visual;
import View.Entities.Abstract.VisualGroup;

public class VisualIrisItems implements VisualGroup {
    private Visual [][] visualIrisItems;

    public VisualIrisItems(IrisItem[][] items) {
        visualIrisItems = new VisualIrisItem[items.length][items[0].length];
        for(int i=0;i<items.length;i++){
            for(int j=0;j<items[i].length;j++) if(items[i][j]!=null){
                visualIrisItems[i][j] = (Visual) new VisualIrisItem(items[i][j]);
            }
        }
    }

    @Override
    public Visual getElement(int x, int y) {
        return visualIrisItems[x][y];
    }

}
