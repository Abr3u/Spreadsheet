package calc.menu.main;

import java.io.IOException;

import pt.utl.ist.po.ui.*;
import calc.core.*;
import calc.textui.main.MenuEntry;
import calc.textui.main.Message;
import calc.menu.*;
import calc.textui.Calc;
public class menu_search extends Command<Calc>{
	
    public menu_search(Calc m){
		super(MenuEntry.MENU_SEARCH, m);
    }
    
public final void execute(){
	Menu men = new SearchMenu(entity());
	men.open();
	}
}