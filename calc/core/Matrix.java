package calc.core;
import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import pt.utl.ist.po.ui.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Matrix implements Serializable{
	
	
	private static final long serialVersionUID = 76465L;
    private int _linhas; 

    private int _colunas;

    private Cell[][] _celulas;
    
    private List<Cell> _cutBuffer = new ArrayList<Cell>();

    /**
       Constructor

       @param colunas
       indica o numero de colunas que a matriz tem

       @param linhas
       indica o numero de linhas que a matriz tem

    */
    public Matrix(int linhas, int colunas){
	_linhas = linhas;
	_colunas = colunas;
	_celulas = new Cell[linhas][colunas];
	int i,j;
	for (i=1; i<=_linhas; i++){
		for (j=1; j<=_colunas; j++){
			_celulas[i-1][j-1] = new Cell(i,j,this);
		}
	}
}
    
    public int getLinhas(){
    	return _linhas;
    }
    
    public int getColunas(){
    	return _colunas;
    }
    
    public Matrix(){
		_celulas = null;
	}
    
    public Matrix newMatrix(int linhas, int colunas){
    	Matrix m = new Matrix(linhas,colunas);
    	return m;
    }


    public void addCell(int linha, int coluna, Conteudo cont){
		_celulas[linha-1][coluna-1].setConteudo(cont);
		}
    

    public void removeCell(int linha, int coluna){
		_celulas[linha-1][coluna-1].removeConteudo();
		}
    
    public Cell getCell(int l, int c) throws InvalidCellRange{
    	if (l <= _linhas && c <= _colunas)
    		return _celulas[l-1][c-1];
    	else
    		throw new InvalidCellRange(l+";"+c);
    }
    
    public Cell[][] getCelulas(){
    	return _celulas;
    }
    
    public List<Cell> getCutBuffer(){
    	return _cutBuffer;
    }
    
    public boolean isCellValid(int[] coordenadas){
    	if (coordenadas[0] > _linhas || coordenadas[1] > _colunas)
    		return false;
    	return true;
    }
    
    public boolean isIntervalValid(int[] coordenadas){
    	if (coordenadas[0] > _linhas || coordenadas[2] > _linhas || coordenadas[1] > _colunas || coordenadas[3] > _colunas)
    		return false;
    	return true;
    }
    
    public void copyCellToBuffer(Cell c){
    	Cell celula = new Cell(1,1);
    	celula.setConteudo(c.getConteudo());
    	_cutBuffer.clear();
    	_cutBuffer.add(celula);
    }
    
    public void copyIntervalToBuffer(Intervalo i, boolean delete){
    	int l = -1, c = -1;
    	Cell aux;
    	_cutBuffer.clear();
    	for (Cell cel : i.getList()){
    		if (l == -1 || c == -1){
    			l = cel.getLinha()-1;
    			c = cel.getColuna()-1;
    		}
    		aux = new Cell(cel.getLinha()-l, cel.getColuna()-c);
    		aux.setConteudo(cel.getConteudo());
    		_cutBuffer.add(aux);
    		if (delete)
    			removeCell(cel.getLinha(), cel.getColuna());
    	}
    }
    
    public void cutCellToBuffer(Cell c){
    	copyCellToBuffer(c);
    	removeCell(c.getLinha(), c.getColuna());
    }
    
    public void pasteCell(Cell c, int index){
    	c.setConteudo(_cutBuffer.get(index).getConteudo());
    }
    
    public void pasteInterval(int[] cord) throws InvalidOperation{
    	int colunamax, linhamax, i=0;
    	if (cord.length == 2){
    		cord[0]--;
    		cord[1]--;
    		for (Cell c : getCutBuffer()){
    			linhamax = cord[0] + c.getLinha();
	    		colunamax = cord[1] + c.getColuna();
        		if (linhamax <= _linhas && colunamax<=_colunas){
    				pasteCell(getCell(cord[0]+c.getLinha(), cord[1]+c.getColuna()), i);
    				i++;
    			}
    		}
    	}
    	else{
    		if(_cutBuffer.size() == intervalLen(cord)){
    			Intervalo inter = new Intervalo(cord, this);
    			for (Cell c: inter.getList()){
    				pasteCell(getCell(c.getLinha(), c.getColuna()), i);
    				i++;
    			}
    		}
    	}
    		
    }
    
    
    public int intervalLen(int[] cords){
		 if (cords[0] == cords[2]){
			 if (cords[1] < cords[3])
				 return cords[3]-cords[1]+1;
			 return cords[1]-cords[3]+1;
		 }
		 else{
			 if (cords[0] < cords[2])
				 return cords[2]-cords[0]+1;
			 return cords[0]-cords[2]+1;
		 }
	 }
    
    public List<Cell> devolveLista() throws InvalidCellRange{
    	List<Cell> list = new ArrayList<Cell>();
    	int l, c;
    	for (l=1; l<=_linhas; l++){
    		for(c=1; c<=_colunas; c++){
    			list.add(getCell(l, c));
    		}
    	}
    	return list;
    }
}
