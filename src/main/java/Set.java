
public class Set {
	
	List <Identifier> IDs;
	
	Set(){
		IDs = new List<>(); // creates an empty list
	}
	public void addIdentifier(Identifier d) {
		if(d.value != null && !elementExist(d)) {
			IDs.insert(d); // add one element to set
		}
	}
	public boolean elementExist(Identifier d) {
		return IDs.find(d); // return true if element was found in the list, false if not
	}
	public Set Union(Set set) {
		
		Set UnionSet = new Set();
		IDs.goToFirst();
		set.IDs.goToFirst();
		
		for(int i = 0; i < set.IDs.size();i++) {
			UnionSet.addIdentifier(set.IDs.retrieve());	
			set.IDs.goToNext();
		}
        for(int i = 0; i < IDs.size(); i++) {
        	UnionSet.addIdentifier(IDs.retrieve());
        	IDs.goToNext();
        }
		return UnionSet;
	}
	public Set Intersection(Set set) {
		
		Set InterSet = new Set();
		IDs.goToFirst();
		set.IDs.goToFirst();
		
		for(int i = 0; i < set.IDs.size();i++) {
			if(elementExist(set.IDs.retrieve())) {
				InterSet.addIdentifier(set.IDs.retrieve());
			}
			set.IDs.goToNext();
		}
		
		return InterSet;
	}
	public Set Difference(Set set) {
		
		Set DiffSet = new Set();
		set.IDs.goToFirst();
		IDs.goToFirst();
		
		for(int i = 0; i < set.IDs.size();i++) {
			if(!elementExist(set.IDs.retrieve())) {
				DiffSet.addIdentifier(set.IDs.retrieve());
				
			}
			set.IDs.goToNext();
		}
		return DiffSet;
	}
	public Set SymetricDifference(Set set) {
		
		return Intersection(set).Difference(Union(set));
	}
	public void printSet() {
		IDs.goToFirst();
		for(int i = 0; i < IDs.size(); i++) {
			System.out.print(IDs.retrieve().value + " ");
			IDs.goToNext();
		}		
		System.out.print("\n");
	}
	public Set copy() {
		Set copySet = new Set();
		if(!IDs.isEmpty()) {
			IDs.goToFirst();
			for(int i = 0; i < IDs.size(); i++) {
				copySet.addIdentifier(IDs.retrieve());
				if(IDs.header != null) {
					IDs.goToNext();
				}
			}
		}
		return copySet;
	}

}
