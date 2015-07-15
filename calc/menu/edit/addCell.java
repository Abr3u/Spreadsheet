package calc.menu.edit;


import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;
import calc.textui.Calc;
public class addCell extends Command<Calc>{
    
    public addCell(Calc a){
		super(MenuEntry.INSERT, a);
    }
    
    public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		int[] coordenadas;
		Conteudo cont;
		InputString endereco = new InputString(f, Message.addressRequest());
		InputString conteudo = new InputString(f, Message.contentsRequest());
		f.parse();
		coordenadas = Parser.parseEndereco(endereco.value());
		if (coordenadas.length == 2){
			if(entity().getSpread().getMatrix().isCellValid(coordenadas)){
				cont = Parser.parseConteudo(entity().getSpread().getMatrix().getCell(coordenadas[0], coordenadas[1]), conteudo.value());
				entity().getSpread().getMatrix().addCell(coordenadas[0], coordenadas[1],cont);
			}
			else
				throw new InvalidCellRange(endereco.value());
		}
		else{
			if (entity().getSpread().getMatrix().isIntervalValid(coordenadas)){
				Intervalo interval = new Intervalo(coordenadas, entity().getSpread().getMatrix());
				for (Cell c : interval.getList()){
					cont = Parser.parseConteudo(c, conteudo.value());
					entity().getSpread().getMatrix().addCell(c.getLinha(), c.getColuna(), cont);
				}
			}
			else
				throw new InvalidCellRange(endereco.value());
		}
	}
}	
