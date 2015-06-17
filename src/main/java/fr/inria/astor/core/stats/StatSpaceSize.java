package fr.inria.astor.core.stats;
/**
 * 
 * @author Matias Martinez,  matias.martinez@inria.fr
 *
 */
public class StatSpaceSize {

	int id = -1;
	int operations = 0;
	int ingredients = 0;
	String genType = "";
	String ingredientType = "";
	public TYPES states; 
	
	public enum TYPES { compiles, notcompiles, alreadyanalyzed }
	
	
	public StatSpaceSize(String type, int ingredients) {
		super();
		this.genType = type;
		this.ingredients = ingredients;
	}
	
	public StatSpaceSize(String type, int ingredients, String ingType) {
		super();
		this.genType = type;
		this.ingredients = ingredients;
		this.ingredientType = ingType;
	}
	
	public StatSpaceSize(
			int id,String type, int ingredients, String ingType) {
		super();
		this.id = id;
		this.genType = type;
		this.ingredients = ingredients;
		this.ingredientType = ingType;
	}
	
	public StatSpaceSize(int operations, int ingredients) {
		super();
		this.operations = operations;
		this.ingredients = ingredients;
	}
	
	public int size(){
		
		if(operations == 0)return ingredients;
		
		if(ingredients == 0) return operations;
		
		return operations * ingredients;
	}

	@Override
	public String toString() {
		return "(id:"+id+", "+genType+", size: "+Integer.toString(this.size())/*+" -op "+operations+" ing "+ingredients+*/+", "+states+") " ;
	}
}
