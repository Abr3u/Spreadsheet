package calc.core;

public class AVG extends FuncaoInt {
	

	public AVG(Intervalo inter, Cell c){
		super(inter, c);
		
	}
	
	public void compute() {
		Integer res=0;
		for (Cell c : getIntervalo().getList()){
			Conteudo aux = c.getConteudo();
			if (aux != null)
				res+=c.getConteudo().getValue();
			else{
				res = null;
				break;
			}
		}
		if (res != null)
			res=res/getIntervalo().getList().size();
		setValue(res);
	}

	public String toString() {
		if (noValue())
			return "#VALUE"+"=AVG("+getIntervalo().getL1()+";"+getIntervalo().getC1()+":"+getIntervalo().getL2()+";"+getIntervalo().getC2()+")";
		return getValue()+"=AVG("+getIntervalo().getL1()+";"+getIntervalo().getC1()+":"+getIntervalo().getL2()+";"+getIntervalo().getC2()+")";
	}
	
	public String functionName(){
		return "AVG";
	}

}
