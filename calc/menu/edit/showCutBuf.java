package calc.menu.edit;

import pt.utl.ist.po.ui.*;
import calc.core.*;
import calc.textui.edit.MenuEntry;
import calc.textui.Calc;
public class showCutBuf extends Command<Calc>{
    
    public showCutBuf(Calc m){
    	super(MenuEntry.SHOW_CUT_BUFFER, m);
    }
    
    public final void execute(){
    	Display d = new Display(title());
    	if (entity().getSpread().getMatrix().getCutBuffer().isEmpty()){
		}
    	else{
    		for(Cell celula : entity().getSpread().getMatrix().getCutBuffer()){
    			if (celula.isEmpty())
    				d.addNewLine(celula.toString(true));
    			else
    				d.addNewLine(celula.toString(false));
    		}
    	}
    	d.display();
    }
}
