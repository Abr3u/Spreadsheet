package calc.menu.edit;

import pt.utl.ist.po.ui.*;
import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;
import calc.textui.Calc;
public class pasteCell extends Command<Calc>{
    
    public pasteCell(Calc m){
	super(MenuEntry.PASTE, m);
    }
    
    public final void execute() throws InvalidOperation{
    	Form f = new Form(title());
    	int[] coordenadas;
    	InputString endereco = new InputString(f, Message.addressRequest());
    	f.parse();
    	coordenadas = Parser.parseEndereco(endereco.value());
    	if ((coordenadas.length == 2) && entity().getSpread().getMatrix().getCutBuffer().size() == 1){
    		if (entity().getSpread().getMatrix().isCellValid(coordenadas))
    			entity().getSpread().getMatrix().pasteCell(entity().getSpread().getMatrix().getCell(coordenadas[0], coordenadas[1]), 0);
    		else
    			throw new InvalidCellRange(endereco.value());
    	}
    	else{
    		if (coordenadas.length == 4){
    			if (entity().getSpread().getMatrix().isIntervalValid(coordenadas))
    				entity().getSpread().getMatrix().pasteInterval(coordenadas);
    		}
    		else if (entity().getSpread().getMatrix().isCellValid(coordenadas))
    				entity().getSpread().getMatrix().pasteInterval(coordenadas);
    		else
        		throw new InvalidCellRange(endereco.value());
    		}
    		
    	}
    }
	
