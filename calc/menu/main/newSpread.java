package calc.menu.main;

import java.io.IOException;

import calc.textui.Calc;
import pt.utl.ist.po.ui.*;
import calc.core.*;
import calc.textui.main.MenuEntry;
import calc.textui.main.Message;

public class newSpread extends Command<Calc>{
	
    public newSpread(Calc a){
		super(MenuEntry.NEW, a);
    }
	
	@SuppressWarnings("static-access")
	public final void execute() throws InvalidOperation{
		Form f = new Form(title());
		InputBoolean gravar = new InputBoolean(f, Message.saveBeforeExit());
		f.parse();
		if (gravar.value())
			menu().entry(2).execute();
		f = new Form(title());
		InputInteger linha = new InputInteger(f, Message.linesRequest());
		InputInteger coluna = new InputInteger(f, Message.columnsRequest());
		f.parse();
		entity().setSpread(new Spread(linha.value(),coluna.value()));
		int i;
		for(i=2;i<6;i++)
			menu().entry(i).visible();
		}
	}
