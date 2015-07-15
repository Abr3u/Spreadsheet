package calc.textui;
import pt.utl.ist.po.ui.*;
import calc.core.Parser;
import calc.core.Spread;
import calc.menu.MainMenu;
import calc.textui.edit.InvalidCellRange;

import java.io.*;


public class Calc implements Serializable{
	
	private Spread _s;

	public Calc(){
		_s = new Spread();
	}
	
	public Spread getSpread(){
		return _s;
	}
	
	public void setSpread(Spread spreadsheet){
		_s = spreadsheet;
	}
	
	public static void main(String[] args) throws InvalidCellRange{
		
		Calc a = new Calc();

		String filename = System.getProperty("import");
		if (filename != null){
			try{
				a.setSpread(Parser.parseFile(filename));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		Menu men = new MainMenu(a);
		men.open();
	}
}
