package calc.core;
import calc.core.*;
import calc.textui.edit.InvalidCellRange;

import java.util.Observer;
import java.util.Observable;

import pt.utl.ist.po.ui.*;
public abstract class FuncaoBin extends Funcao implements Observer{

private Parametro _p1;
private Parametro _p2;
protected Observable _observing1, _observing2;

public FuncaoBin(Cell c, Parametro p1, Parametro p2) throws InvalidCellRange{
	_p1 = p1;
	_p2 = p2;
	setCell(c);
	setObservers();
	compute();
}


public void setp1(Parametro p){
	_p1 = p;
}

public void setObservers() throws InvalidCellRange{
	if (_p1 instanceof Referencia){
		_observing1 = ((Referencia)_p1).getCell().getMatrix().getCell(((Referencia)_p1).getLinha(), ((Referencia)_p1).getColuna());
		_observing1.addObserver(this);
	}
	if (_p2 instanceof Referencia){
		_observing2 = ((Referencia)_p2).getCell().getMatrix().getCell(((Referencia)_p2).getLinha(), ((Referencia)_p2).getColuna());
		_observing2.addObserver(this);
	}
}
public void setp2(Parametro p){
	_p2 = p;
}

public Parametro getp1(){
	return _p1;
}

public Parametro getp2(){
	return _p2;
}

public void update(Observable obs, Object arg){
	if (obs instanceof Cell){
		if (_p1 instanceof Referencia)
			((Referencia)_p1).update(obs, arg);
		if (_p2 instanceof Referencia)
			((Referencia)_p2).update(obs, arg);
	}
	compute();
}
}
