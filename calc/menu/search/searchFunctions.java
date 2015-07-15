package calc.menu.search;

import java.io.IOException;

import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import calc.textui.search.MenuEntry;
import calc.textui.search.Message;
import calc.textui.Calc;
public class searchFunctions extends Command<Calc>{
    
    public searchFunctions(Calc m){
	super(MenuEntry.SEARCH_FUNCTIONS, m);
    }
    
    public final void execute() throws InvalidCellRange{
    	Form f = new Form(title());
    	Display d = new Display(title());
    	InputString input = new InputString(f, Message.searchFunction());
    	f.parse();
    	for (Cell c: entity().getSpread().getMatrix().devolveLista()){
    		if (c.getConteudo() == null)
    			continue;
    		if((c.getConteudo() instanceof Funcao)){
    			if (((Funcao)c.getConteudo()).functionName().contains(input.value()))
    				d.addNewLine(c.toString(c.isEmpty()));
    		}
    	}
    	d.display();
    }
}