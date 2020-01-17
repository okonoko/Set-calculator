import java.math.BigInteger;

public class Identifier implements Comparable<Identifier>, IdentifierInterface{
	
	public BigInteger value;

	
	/*Identifier (String buffer){
		value = new BigInteger(buffer);
	}*/
	Identifier (){
		value = null;
	}
	public void add(char x) {
		String buffer;
		if(value == null) {
			buffer = Character.toString(x);
		}else {
			buffer = value.toString();
			buffer += x;
		}
		value = new BigInteger(buffer);
	}
	
	public int compareTo(Identifier x) {
        return value.compareTo(x.value);
    }
	
	public boolean equals(Object o){

        if(o != null && o.getClass() == getClass()) {
            return this.compareTo((Identifier)o) == 0;
        }
        return false;
	}
}
