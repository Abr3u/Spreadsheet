package calc.core;
import calc.core.*;
import calc.textui.edit.InvalidCellRange;

import java.util.Observer;
import java.util.Observable;

import pt.utl.ist.po.ui.*;

public class Referencia extends Parametro implements Observer{
    private int _linha;
    private int _coluna;
    private Observable _observing;

    public Referencia(Cell c, int linha, int coluna) throws InvalidCellRange{
	Conteudo contaux;
	Cell caux;
    _linha = linha;
	_coluna = coluna;
	setCell(c);
	caux = getCell().getMatrix().getCell(_linha,_coluna);
	contaux = caux.getConteudo();
	if (contaux == null)
		setValue(null);
	else
		setValue(contaux.getValue());
	setObserving();
    }
    
    public void setObserving() throws InvalidCellRange{
    	_observing = getCell().getMatrix().getCell(_linha,_coluna);
    	_observing.addObserver(this);
    }


    public String toString(){
    	if(getValue() == null)
    		return "#VALUE="+_linha+";"+_coluna;
    	else
    		return getValue()+"="+_linha+";"+_coluna;
    }
    
    public String stringContent(){
    	return _linha+";"+_coluna;
    }
    
    public void defValue() throws InvalidCellRange{
    	Conteudo contaux;
    	contaux = getCell().getMatrix().getCell(_linha,_coluna).getConteudo();
    	if (contaux == null)
    		setValue(null);
    	else
    		setValue(contaux.getValue());
    	setChanged();
    	notifyObservers();
    }
    

    public int getLinha(){
    	return _linha;
    }
    
    public int getColuna(){
    	return _coluna;
    }

	public void update(Observable obs, Object arg){
		if (obs instanceof Cell){
			try {
				defValue();
			} catch (InvalidCellRange e) {
				e.printStackTrace();
			}
			setChanged();
	    	notifyObservers();
	    	getCell().warnObservers();
		}
	}
}
