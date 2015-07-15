package calc.core;
import calc.core.*;
import java.util.Observable;
import pt.utl.ist.po.ui.*;
import java.io.*;

public class Cell extends Observable implements Serializable{
	
	private static final long serialVersionUID = 76465L;
    private Conteudo _conteudo;
    private int _linha,_coluna;
    private Matrix _mass;

    public Cell(int l, int c){
	_conteudo = null;
	_linha = l;
	_coluna = c;
    }
    
    public Cell(int l, int c, Matrix m){
    	_conteudo = null;
    	_linha = l;
    	_coluna = c;
    	_mass = m;
        }

    public String toString(boolean empty){
    	if(empty)
    		return _linha+";"+_coluna+"|";
    	return _linha+";"+_coluna+"|"+_conteudo.toString();
    }
    public boolean equals(Object o){
    	return (o instanceof Cell) && ((Cell)o).getConteudo() == _conteudo;
	}

    public void warnObservers(){
    	 setChanged();
    	 notifyObservers();
    }
    public boolean isEmpty(){
    	if (_conteudo == null)
    		return true;
    	else
    		return false;
    }
    
    public void removeConteudo(){
	_conteudo = null;
	setChanged();
    notifyObservers();
    }
    
    public void setEndereco(int l, int c){
		_linha = l;
		_coluna = c;
	}
	
    public int getLinha(){
    	return _linha;
    }
    
    public int getColuna(){
    	return _coluna;
    }
    
	public void setConteudo(Conteudo cont){
		_conteudo = cont;
		 setChanged();
		 notifyObservers();
	}
	public Conteudo getConteudo(){
		return _conteudo;
	}
	
	public Matrix getMatrix(){
		return _mass;
	}
}

