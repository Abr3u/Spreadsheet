package calc.menu.main;

import java.io.*;

import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.main.MenuEntry;
import calc.textui.main.Message;
import calc.textui.Calc;
public class open extends Command<Calc>{
	
    public open(Calc s){
		super(MenuEntry.OPEN, s);
    }
    
    public final void execute() throws InvalidOperation{
    	Form f = new Form(title());
    	int i;
    	InputString file = new InputString(f, Message.openFile());
    	f.parse();
    	if (file.value() != null){
    		try{
    			Spread s = loadSpread(file.value());
    			entity().setSpread(s);
    			for(i=2; i<6; i++){
    				menu().entry(i).visible();
    			}
    		}
    		catch (IOException io){
    			throw new InvalidOperation(Message.fileNotFound(file.value()));
    		} 
    		catch (ClassNotFoundException cnf) {
    			throw new InvalidOperation(cnf.toString());
    		}	

    	}
    }

	public Spread loadSpread(String file) throws IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		Spread spread = (Spread)in.readObject();
		in.close();
		return spread;
	}
}