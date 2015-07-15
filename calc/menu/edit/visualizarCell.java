package calc.menu.edit;

import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;
import calc.textui.Calc;
public class visualizarCell extends Command<Calc>{
    
    public visualizarCell(Calc a){
		super(MenuEntry.SHOW, a);
    }
    
    public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		Display d = new Display(title());
		int[] endereco;
		InputString address = new InputString(f, Message.addressRequest());
		f.parse();
		endereco = Parser.parseEndereco(address.value());
		if (endereco.length == 4){
			if (entity().getSpread().getMatrix().isIntervalValid(endereco)){
				Intervalo interval = new Intervalo(endereco,entity().getSpread().getMatrix());
				for(Cell c : interval.getList())
					d.addNewLine(c.toString(c.isEmpty()));
			}
			else
				throw new InvalidCellRange(address.value());
		}
		else{
			if (entity().getSpread().getMatrix().isCellValid(endereco)){
				Cell c = entity().getSpread().getMatrix().getCell(endereco[0],endereco[1]);
				d.add(c.toString(c.isEmpty()));
			}
			else
				throw new InvalidCellRange(address.value());
		}
		d.display();
    }
}
