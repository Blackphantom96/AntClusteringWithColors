package Model.Impl.Color;

import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import Model.Abstraction.Function;
import Model.Abstraction.Particle;
import Model.Impl.CoreFactoryCreator;

public class ColorFunction implements Function<int[]> {

	@Override
	public int probPick(int x, int y, Particle<int[]> p) { // Eq 5.14
		double k1 = CoreFactoryCreator.getFactory().getInstance().getK1();
		return (int) Math.pow(k1 / (k1 + itemAverageSimilarity(x, y, CoreFactoryCreator.getFactory().getInstance().getRadio(), p)), 2) * 100;
	}

	@Override
	public int probDeposit(int x, int y, Particle<int[]> p) { // Eq 5.15
		double fx = itemAverageSimilarity(x, y, CoreFactoryCreator.getFactory().getInstance().getRadio(), p);
		double k2 = CoreFactoryCreator.getFactory().getInstance().getK2();
		return (int) (fx < k2 ? 2.0 * fx + 0.5 : 1.0);
	}

	@Override
	public double itemPerceivedFraction(int x, int y, int r, Particle<int[]> p) { //FIXME verificar si esto es necesario .
		int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();		
		for(int i = x-r;i<x+r;i++){
			for(int j = y-r;j<y+r;j++) if (0<=i && i<maxX && 0<=j && j<maxY ){
				
			}
		}
		
		return 0;
	}

	@Override
	public double itemAverageSimilarity(int x, int y, int r, Particle<int[]> p) { // Eq 5.13 o mirar Eq 5.16 en caso de querer meter velocidad
		int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();	
		double alpha = 0.2 ; 
		double sum = 0.0 ;
		for(int i = x-r-1;i<x+r;i++){ //XXX verificar (x-r-1) ya que tiene que ser en un rango (x-r : x+r) y lo mismo para y 
			for(int j = y-r-1;j<y+r;j++) if (0<=i && i<maxX && 0<=j && j<maxY ){
				sum += 1-( p.euclideanDistance( (ColorParticle) CoreFactoryCreator.getFactory().getInstance().getParticles()[j][i])/alpha ); 
			}
		}
		double f = sum/(r*r) ;
		return f>0 ? f : 0;
	}

}
