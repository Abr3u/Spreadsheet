package calc.core;
import java.util.*;

import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import pt.utl.ist.po.ui.*;
public abstract class FuncaoInt extends Funcao implements Observer{

private Intervalo _intervalo;
private List<Observable> _observing = new ArrayList<Observable>();

public FuncaoInt(Intervalo intervalo, Cell c){
	_intervalo = intervalo;
	setCell(c);
	setObservers();
	compute();
	}

public void setObservers(){
	for (Cell c : _intervalo.getList()){
		_observing.add(c);
		c.addObserver(this);
	}
}

public void update(Observable obs, Object arg){
	if (obs instanceof Cell)
		for (Cell c : getIntervalo().getList()){
			if (c.getConteudo() instanceof Referencia)
				try {
					((Referencia)c.getConteudo()).defValue();
				} catch (InvalidCellRange e) {
					e.printStackTrace();
				}
			if (c.getConteudo() instanceof Funcao)
				((Funcao)c.getConteudo()).compute();
		}
		compute();
}

public Intervalo getIntervalo(){
	return _intervalo;
} 

public void setIntervalo(Intervalo inter){
	_intervalo = inter;
}
}
