package calc.menu.search;

import java.io.IOException;

import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import calc.textui.search.MenuEntry;
import calc.textui.search.Message;
import calc.textui.Calc;
public class searchValues extends Command<Calc>{
    
    public searchValues(Calc m){
	super(MenuEntry.SEARCH_VALUES, m);
    }
    
    public final void execute() throws InvalidCellRange{
    	int value;
    	Form f = new Form(title());
    	Display d = new Display(title());
    	InputInteger input = new InputInteger(f, Message.searchValue());
    	f.parse();
    	value = input.value();
    	for (Cell c: entity().getSpread().getMatrix().devolveLista()){
    		if (c.getConteudo() == null)
    			continue;
    		if(c.getConteudo().getValue().equals(value))
    			d.addNewLine(c.toString(c.isEmpty()));
    	}
    	d.display();
    }
}