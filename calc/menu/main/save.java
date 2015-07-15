package calc.menu.main;

import java.io.*;

import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.main.MenuEntry;
import calc.textui.main.Message;
import calc.textui.Calc;
public class save extends Command<Calc>{
	
    public save(Calc s){
		super(MenuEntry.SAVE, s);
    }
    
    public final void execute() throws InvalidOperation{
    	String file;
    	if (entity().getSpread().getNome() == null){
    		Form f = new Form(title());
    		InputString fileaux = new InputString(f, Message.newSaveAs());
    		f.parse();
    		file = fileaux.value();
    		entity().getSpread().setNome(file);
    	}
    	else{
    		file = entity().getSpread().getNome();
    	}
		try{
			ObjectOutputStream out =  new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(entity().getSpread());
			out.close();
		}
		catch(IOException io){
			throw new InvalidOperation(io.toString());
		}
		
    }
}
