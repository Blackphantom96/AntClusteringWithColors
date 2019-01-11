package Model.Abstraction.Factory;

import de.lmu.ifi.dbs.elki.distance.distancefunction.AbstractNumberVectorDistanceFunction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DistanceFunctionFactory {

	private DistanceFunctionFactory(){

	}

	private static Map<String, AbstractNumberVectorDistanceFunction> cache = new HashMap<>();

	public static AbstractNumberVectorDistanceFunction getDistanceFunction(Class c) throws Exception {
		if (c == null || !AbstractNumberVectorDistanceFunction.class.isAssignableFrom(c)) {
			System.out.println("ERROR: No es una funcion de distancia valida: " + c);
			return null;
		}

		if (cache.containsKey(c.getName())) {
			return cache.get(c.getName());
		}

		AbstractNumberVectorDistanceFunction res = null;

		try {
			Field f = c.getField("STATIC");
			res = (AbstractNumberVectorDistanceFunction) f.get(null);
		} catch (NoSuchFieldException ex) {
			System.out.println("ERROR: No se pudo obtener campo STATIC de clase " + c.getName());
			res = instantiateDistanceFunction(c);
		}

		cache.put(c.getName(), res);

		return res;
	}

	private static AbstractNumberVectorDistanceFunction instantiateDistanceFunction(Class c) throws Exception {
		try {
			Constructor ctor = c.getConstructor();
			return (AbstractNumberVectorDistanceFunction) ctor.newInstance();
		}catch (Exception e){
			throw new Exception("Funcion no soportada");
		}
	}

}
