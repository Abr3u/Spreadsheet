package calc.core;
import java.util.ArrayList;
import java.util.List;

import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import pt.utl.ist.po.ui.*;

public class Intervalo{
	 private List<Cell> _intervaloCells = new ArrayList<Cell>();
	 private Matrix _mass;
	 private int _l1, _c1, _l2, _c2;
	 
	 public Intervalo(int[] coordenadas, Matrix m) throws InvalidCellRange{
		 setMatrix(m);
		 int orientacao =-1, inicio = 1,fim = 1, constante = -1;
		 int l1 = coordenadas[0];
		 int c1 = coordenadas[1];
		 int l2 = coordenadas[2];
		 int c2 = coordenadas[3];
		 
		 if (l1 == l2){
				inicio = (c1<c2) ? c1 : c2;
				fim = (c1>c2) ? c1 : c2;
				_c1 = inicio;
				_c2 = fim;
				orientacao = 0;
				constante = l1;
				_l1 = constante;
				_l2 = constante;
			}
		else{
			if(c1 == c2){
				inicio = (l1<l2) ? l1 : l2;
				fim = (l1>l2) ? l1 : l2;
				_l1 = inicio;
				_l2 = fim;
				orientacao = 1;
				constante = c1;
				_c1 = constante;
				_c2 = constante;
			}
			else
				throw new InvalidCellRange(coordenadas[0]+";"+coordenadas[1]+":"+coordenadas[2]+";"+coordenadas[3]);
		}		
		if (orientacao == 0){
			for (int j=inicio; j<=fim; j++){
				_intervaloCells.add(getMatrix().getCell(constante, j));
			}
		}
		if (orientacao == 1){
			for (int j=inicio; j<=fim; j++){
				_intervaloCells.add(getMatrix().getCell(j, constante));
			}
		}
	 }
	 
	 public Matrix getMatrix(){
		 return _mass;
	 }
	 
	 public void setMatrix(Matrix m){
		 _mass = m;
	 }
	 
	 public List<Cell> getList(){
		 return _intervaloCells;
	 }
	 
	 public int getL1(){
		 return _l1;
	 }

	 public int getL2(){
		 return _l2;
	 }
	 
	 
	 public int getC1(){
		 return _c1;
	 }
	 
	 public int getC2(){
		 return _c2;
	 }
	 
	 public int[] coordenadasMax(){
		 int res[] = null;
		 res[0] = (_l1<=_l2) ? _l2 : _l2;
		 res[1] = (_c1<=_c2) ? _c1 : _c2;
		 return res;
	 }
}