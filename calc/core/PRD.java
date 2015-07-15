package calc.core;

public class PRD extends FuncaoInt {
	
	public PRD(Intervalo inter, Cell c){
		super(inter, c);
		
	}

	public void compute() {
		Integer res=1;
		Conteudo aux = null;
		for (Cell c : getIntervalo().getList()){
			aux = c.getConteudo();
			if (aux!=null)
				res*=aux.getValue();
			else{
				res = null;
				break;
			}
		}
		setValue(res);
	}

	public String toString() {
		if (noValue())
			return "#VALUE"+"=PRD("+getIntervalo().getL1()+";"+getIntervalo().getC1()+":"+getIntervalo().getL2()+";"+getIntervalo().getC2()+")";
		return getValue()+"=PRD("+getIntervalo().getL1()+";"+getIntervalo().getC1()+":"+getIntervalo().getL2()+";"+getIntervalo().getC2()+")";
	}
	
	public String functionName(){
		return "PRD";
	}

}
