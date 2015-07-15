package calc.core;
import calc.core.*;
import java.util.Observable;
import java.io.*;
import pt.utl.ist.po.ui.*;

public abstract class Conteudo extends Observable implements Serializable{
	
	private static final long serialVersionUID = 76465L;
	private Cell _celula;
	private Integer _value;
	
	
	public void setCell(Cell c){
		_celula = c;
	}
	
	public Cell getCell(){
		return _celula;
	}
	
	
    public void setValue(Integer v){
    	_value = v;
    }
    
    public Integer getValue(){
    	return _value;
    }
    
    public boolean noValue(){
    	if (_value == null)
    		return true;
    	return false;
    }
    
    
    public abstract String toString();
}
