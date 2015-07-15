package calc.menu.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.main.MenuEntry;
import calc.textui.main.Message;
import calc.textui.Calc;
public class save_as extends Command<Calc>{
	
    public save_as(Calc s){
		super(MenuEntry.SAVE_AS,s);
    }
    
public final void execute()throws InvalidOperation{
	String file;
	Form f = new Form(title());
	InputString fileaux = new InputString(f, Message.saveAs());
	f.parse();
	file = fileaux.value();
	entity().getSpread().setNome(file);
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