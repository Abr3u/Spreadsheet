package calc.menu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;
import calc.textui.Calc;

import calc.textui.main.MenuEntry;
import calc.menu.main.*;

public class MainMenu extends Menu {

    /**
     * Constructor for class EditMenu
     *
     * @param b the bank this account belongs to.
     */
    public MainMenu(Calc a) {
	super(MenuEntry.TITLE, new Command<?>[] {
	    new newSpread(a),
	    new open(a),
	    new save(a),
	    new save_as(a),
	    new menu_edit(a),
	    new menu_search(a)
	});
	int i;
	if (a.getSpread().getMatrix().getCelulas() == null){
		for(i=2;i<6;i++)
			entry(i).invisible();
    }
    }
}
