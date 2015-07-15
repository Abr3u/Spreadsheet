package calc.menu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;

import calc.textui.edit.MenuEntry;
import calc.core.*;
import calc.menu.edit.*;
import calc.textui.Calc;
public class EditMenu extends Menu {

    /**
     * Constructor for class EditMenu
     *
     * @param b the bank this account belongs to.
     */
    public EditMenu(Calc a) {
	super(MenuEntry.TITLE, new Command<?>[] {
	    new visualizarCell(a),
	    new addCell(a),
	    new copyCell(a),
	    new deleteCell(a),
	    new cutCell(a),
	    new pasteCell(a),
	    new showCutBuf(a)
	});
    }
}
