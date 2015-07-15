package calc.menu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;

import calc.textui.search.MenuEntry;
import calc.core.*;
import calc.menu.search.*;
import calc.textui.Calc;
public class SearchMenu extends Menu {

    /**
     * Constructor for class EditMenu
     *
     * @param b the bank this account belongs to.
     */
    public SearchMenu(Calc a) {
	super(MenuEntry.TITLE, new Command<?>[] {
	    new searchValues(a),
	    new searchFunctions(a)
	});
    }
}
