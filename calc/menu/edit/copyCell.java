package calc.menu.edit;

import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;
import calc.textui.Calc;
public class copyCell extends Command<Calc>{
    
    public copyCell(Calc m){
	super(MenuEntry.COPY, m);
    }
    
    public final void execute() throws InvalidOperation {
	Form f = new Form(title());
	int[] coordenadas;
	InputString endereco = new InputString(f, Message.addressRequest());
	f.parse();
	coordenadas = Parser.parseEndereco(endereco.value());
	if (coordenadas.length == 2){
		if(entity().getSpread().getMatrix().isCellValid(coordenadas))
			entity().getSpread().getMatrix().copyCellToBuffer(entity().getSpread().getMatrix().getCell(coordenadas[0],coordenadas[1]));
		else
			throw new InvalidCellRange(endereco.value());
	}
	else{
		if (entity().getSpread().getMatrix().isIntervalValid(coordenadas)){
			Intervalo aux = new Intervalo(coordenadas, entity().getSpread().getMatrix());
			entity().getSpread().getMatrix().copyIntervalToBuffer(aux, false);
		}
		else
			throw new InvalidCellRange(endereco.value());
	}	
    }
}
