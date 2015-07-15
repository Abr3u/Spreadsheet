package calc.core;
import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import pt.utl.ist.po.ui.*;

public class DIV extends FuncaoBin{
	
	public DIV(Cell c, Parametro p1, Parametro p2) throws InvalidCellRange{
		super(c, p1, p2);
	}

	public void compute(){
		Integer param1, param2;
		param1 = getp1().getValue();
		param2 = getp2().getValue();
		if (param1 == null || param2 == null)
			setValue(null);
		else
			setValue(getp1().getValue()/getp2().getValue());
		}
	
	public String toString(){
		if (noValue())
			return "#VALUE"+"=DIV("+getp1().stringContent()+","+getp2().stringContent()+")";
		return getValue()+"=DIV("+getp1().stringContent()+","+getp2().stringContent()+")";
	}
	
	public String functionName(){
		return "DIV";
	}
}