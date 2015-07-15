package calc.core;
import calc.core.*;
import calc.textui.main.Message;
import java.io.*;
import pt.utl.ist.po.ui.*;

/**
* classe Spread que alem de implementar os metodos que lhe estao associados, implementa tambem o parseInputFile
*/

public class Spread implements Serializable{
	/** matriz associada a esta spreadsheet*/
	private Matrix _matriz;
	
	/** nome da spreadsheet*/
	private String _nome = null;
	/**
	 * Constructor
	 * 
	 * @param linha, linhas maximas da matriz
	 * @param coluna, colunas maximas da matriz
	 * @param nome, nome da spreadsheet
	 *            
	 */
	public Spread(int linha, int coluna, String nome){
		_matriz = new Matrix(linha,coluna);
	}
	/**
	 * Constructor
	 * 
	 * @param linha, linhas maximas da matriz
	 * @param coluna, colunas maximas da matriz
	 *            
	 */
	public Spread(int linha, int coluna){
		_matriz = new Matrix(linha,coluna);
	}
	/**
	 * Constructor
	 *
	 * @param nome, nome da spreadsheet
	 *            
	 */
	public Spread(String n){
		_matriz = new Matrix();
		_nome = n;
	}
	/**
	 * Constructor
	 */
	public Spread(){
		_matriz = new Matrix();
		_nome = null;
	}
	
	/**
	 * Returns the spreadhseet
	 * @return the spreadsheet        
	 */
	public Spread getSpread(){return this;}
	
	/**
	 * @param m, matriz a ser associada a esta spreadsheet	 
	 */
	public void setMatrix(Matrix m){
		_matriz = m;
	}
	/**
	 * Set the spreadsheet name to n
	 * @param n, nome da spreadhseet        
	 */
	public void setNome(String n){
		_nome = n;
	}
	/**
	 * Returns the name of the spreadsheet
	 * @return the name of the spreadsheet           
	 */
	public String getNome(){
		return _nome;
	}
	/**
	 * Returns the matrix associated to this spreadhseet
	 * @return the matrix associated to this spreadsheet          
	 */
	public Matrix getMatrix(){return this._matriz;}
	
	/**
	 * Check if matrix is new
	 * @return true if matrix only has 1 cell, false otherwise           
	 */
	public boolean isNew(){
		if (this.getMatrix().getLinhas() == 1 && this.getMatrix().getColunas() == 1)
			return true;
		else
			return false;
	}
	/**
	 * Create new spreadsheet
	 * @param linha, numero total de linhas
	 * @param coluna, numero total de colunas
	 */
	public Spread newSpread(int linha, int coluna){
		Spread sp = new Spread(linha, coluna);
		return sp;
	}
}
