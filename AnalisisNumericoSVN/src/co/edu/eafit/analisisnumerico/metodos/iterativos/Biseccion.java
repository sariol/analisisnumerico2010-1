package co.edu.eafit.analisisnumerico.metodos.iterativos;

import co.edu.eafit.analisisnumerico.framework.*;

/**
 * Metodo de la biseccion. Utiliza el punto medio para acercarse a la raiz
 * Categoria: Ecuaciones Lineales
 * * @author Sebastian
 *
 */
public class Biseccion extends MetodoUnidad1 implements MetodoInterfaz {

	/**
	 * Funcion Main. Ejecuta Biseccion
	 * 
	 */
	public static void main(String[] args){
		GestorMetodos.ejecutar(Constantes.BISECCION, Constantes.MODOGRAFICOINTERFAZ1, "Metodo de Biseccion", "f", null, null, "Xi", "Xs", "Cifras significativas", "iteraciones");
	}

	@Override
	public String metodo(double... entradas) throws AnalisisException{
		this.adicionarFilaTitulos("iteracion","xi","xs","xm","f(xm)","error");
		double xi = entradas[0];
		double xs = entradas[1];
		double cifrasSignificativas = entradas[2];
		double iteraciones = entradas[3];
		//CONVIERTO LAS CIFRAS A TOLERANCIA (5->0,000001)
		double tolerancia = UtilConsola.getTolerancia(cifrasSignificativas);
		double yi =f(xi);
		double ys =f(xs);
		if(yi==0)return "Xi es raiz";
		else if(ys==0)return "Xs es raiz";
		else if(ys*yi>0)return "Intervalo inadecuado";
		else{
			double xm= (xi+xs)/2;
			double xAux;
			double error= tolerancia+1;
			double cont =1;
			double ym=f(xm);
			adicionarFilaResultados(cont-1,xi,xs,xm,ym,-1.0);
			while(ym!=0&&error>tolerancia&&cont<iteraciones){
				if(yi*ym<0){
					xs=xm;
					ys=ym;
				}
				else{
					xi=xm;
					yi=ym;
				}
				xAux=xm;
				xm=(xi+xs)/2;
				ym=f(xm);
				error = Math.abs(xm-xAux);
				adicionarFilaResultados(cont,xi,xs,xm,ym,error);
				cont++;
			}
			if(ym==0)return xFormat(xm)+"es raiz. Hallado en "+cont+" iteraciones.";
			else if(error<=tolerancia)return xFormat(xm)+" es raiz con un error relativo de "+eFormat(error)+". Hallado en "+cont+" iteraciones.";
			else return "Se ha fracasado con "+cont+" iteraciones";
		}
	}
}