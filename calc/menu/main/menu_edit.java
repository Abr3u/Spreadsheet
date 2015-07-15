package calc.menu.main;

import java.io.IOException;

import pt.utl.ist.po.ui.*;

import calc.core.*;
import calc.textui.main.MenuEntry;
import calc.textui.main.Message;
import calc.menu.*;
import calc.textui.Calc;
public class menu_edit extends Command<Calc>{
	
    public menu_edit(Calc m){
		super(MenuEntry.MENU_CALC, m);
    }
    
public final void execute(){
	Menu men = new EditMenu(entity());
	men.open();
	}
}