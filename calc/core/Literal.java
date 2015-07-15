package calc.core;
import calc.core.*;
import pt.utl.ist.po.ui.*;

public class Literal extends Parametro{
   

    public Literal(Integer value){
	setValue(value);
    }
    
    public String toString(){
    	return ""+getValue();
    }
    
    public String stringContent(){
    	return toString();
    }
    
}
