package calc.core;
import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import pt.utl.ist.po.ui.*;

public class MUL extends FuncaoBin{
	
	public MUL(Cell c, Parametro p1, Parametro p2) throws InvalidCellRange{
		super(c, p1, p2);
		
	}

	public void compute(){
		Integer param1, param2;
		param1 = getp1().getValue();
		param2 = getp2().getValue();
		if (param1 == null || param2 == null)
			setValue(null);
		else
			setValue(getp1().getValue()*getp2().getValue());
		}
	
	public String toString(){
		if (noValue())
			return "#VALUE"+"=MUL("+getp1().stringContent()+","+getp2().stringContent()+")";
		return getValue().toString()+"=MUL("+getp1().stringContent()+","+getp2().stringContent()+")";
	}
	
	public String functionName(){
		return "MUL";
	}
}
