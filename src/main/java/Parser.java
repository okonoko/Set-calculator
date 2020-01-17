import java.util.*;

public class Parser {
	
	char c;
	int cursor;
	String input;
	String mainSet;
	boolean after = false;
	Stack<Set>stackVal = new Stack<Set>();
	Stack<Character> stackOp = new Stack <Character>();
	HashMap<String, Set> map = new HashMap<String, Set>();
			
	Parser(){
		
	}
	void nextChar(){
		if(cursor != input.length() - 1) {
			cursor++;
			c = input.charAt(cursor);
		}
		else {
			c = '.';
		}
		while(c == ' ') {
			if(cursor == input.length() - 1) {
				c = '.';
				break;
			}
			cursor++;
			c = input.charAt(cursor);
		}
	}
	void nextCharSpace() {
		if(cursor != input.length() - 1) {
			cursor++;
			c = input.charAt(cursor);
		}
		else {
			c = '.';
		}
	}
	
	void calculations () {
		switch (stackOp.pop()) {
		case '+':
			stackVal.push(stackVal.pop().Union(stackVal.pop()));
			break;
		case '|':
			stackVal.push(stackVal.pop().SymetricDifference(stackVal.pop()));
			break;
		case '-':
			stackVal.push(stackVal.pop().Difference(stackVal.pop()));
			break;
		default:
			break;
		}
	}
	boolean natural_number(){
		return positive_number() || zero();
	}

	boolean positive_number(){
		return (number() && not_zero());
		
	}

	boolean number(){
		return (zero() || not_zero());
	}

	boolean zero(){
		return c == '0';
	}

	boolean not_zero(){
		return ('0' < c && c <= '9');
	}
	
	boolean letter(){
		return Character.isLetter(c);
	}
	
	boolean additive_operator() throws APException{
		if(c == ' ') {
			nextChar();
		}
		if (c == '+' || c == '|' || c == '-') {
			stackOp.push(c);
			nextChar();
			return true;
		}else {
			return false;
		}
	}

	boolean multiplicative_operator() throws APException{
		if(c == ' ') {
			nextChar();
		}
		if(c == '/') {
			map.remove(mainSet);
			throw new APException ("No end of line");
		}
		if (c == '*') {
			nextChar();
			return true;
		}
		if(letter() || natural_number()) {
			throw new APException ("No end of line");
		}else {
			return false;
		}
	}
	
	boolean complex() throws APException{
		int backtrack = cursor;
		if (c == '(') {
			//System.out.println("Complex");
			nextChar();
			expression();
		}else
			return false;
		if(c == ')'){
			nextChar();
			return true;
		}else
			cursor = backtrack;
			throw new APException("Missing parenthesis");
	}
	
	boolean identifier () throws APException{
		int backtrack = cursor;
		String identifier = null;
		if(c == ' ') {
			nextChar();
		}
		if(letter()) {
			identifier = Character.toString(c);
			nextChar();
			while(letter() || natural_number()){
				identifier += c;
				nextCharSpace();
			}
			if(c == ' ') {
				nextChar();
			}
			if(letter() || natural_number()) {
				throw new APException("Spaces not allowed within identifiers ");
			}else {
				if(after) {
					if(map.get(identifier) == null) {
						throw new APException("Set does not exist");
					}else {
						stackVal.push(map.get(identifier).copy());
					}
				}else {
					if(map.get(identifier) == null) {
						mainSet = identifier;
						Set set = new Set();
						map.put(identifier, set);
					}else {
						mainSet = identifier;
					}
				}
				return true;
			}
		}
		else if(c == '(' || c == '{') {
			cursor = backtrack;
			return false;
		}
		else {
			cursor = backtrack;
			throw new APException("Identifier must start with a letter");
		}
	}
	
	boolean set() throws APException{
		
		int backtrack = cursor;
		boolean correctSet = false;
		Set set = new Set();
		
		if(c == '{'){
			nextChar();
			
			if(!row_natural_numbers(set)) {
				correctSet = false;
				throw new APException ("Bad set");
			}
		}else {
			cursor = backtrack;
			return false;
		}
		if(c == '}'){
			stackVal.push(set);
			nextChar();
			correctSet = true;
		}else {
			cursor = backtrack;
			correctSet = false;
			throw new APException("Missing '}'");
		}
		return correctSet;
	}
	
	
	boolean row_natural_numbers(Set set){
		boolean isCorrect = false;
		boolean expectNr = false;
		Identifier ID;
		while(true) {
			isCorrect = false;
			ID = new Identifier();
			if (natural_number()){
				expectNr = false;
				if(c == '0') {
					ID.add(c);
					nextChar();
					if(c == '}') {
						break;
					}
					else if(c == ',') {
						expectNr = true;
						set.addIdentifier(ID);
						nextChar();
					}else {
						isCorrect = true;
						break;
					}
				}
				else {
					ID.add(c);
					nextCharSpace();
				
					if(c == ',') {
						expectNr = true;
						set.addIdentifier(ID);
						nextChar();
					}
					else if(natural_number()){
						isCorrect = true;
						if(!next_number(ID, set)) {
							isCorrect = false;
							break;
						}
					}
					else if(c == ' ') {
						nextChar();
						if ( c ==',') {
							expectNr = true;
							set.addIdentifier(ID);
							nextChar();
						}
						else if(natural_number()){
							isCorrect = false;
							break;
						}
						else if(!natural_number()) {
							break;
						}
					}
					else if(!natural_number()) {
						break;
					}
				}
			}else {
				isCorrect = true;
				break;
			}
		}
		if(expectNr) {
			return false;
		}
		if(c == '}') {
			if(!isCorrect) {
				set.addIdentifier(ID);
			}
			isCorrect = true;
		}
		else if (letter()){
			isCorrect = false;
		}
		else {
			isCorrect = false;
		}
		
		return isCorrect;
	}
	boolean next_number(Identifier ID, Set set) {
		boolean isCorrect = true;
		while(isCorrect){
			ID.add(c);
			nextCharSpace();
			if(!natural_number()) {
				if ( c ==',') {
					nextChar();
					set.addIdentifier(ID);
					break;
				}
				else if(c == '}'){
					set.addIdentifier(ID);
					break;
				}else if(c == ' ') {
					nextChar();
					if ( c ==',') {
						set.addIdentifier(ID);
						nextChar();
						break;
					}
					else if(c == '}'){
						set.addIdentifier(ID);
						break;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		return isCorrect;
	}
	
	boolean expression() throws APException{
		boolean isCorrect = term();
		if(isCorrect) {
			while(additive_operator()){
				if(term()) {
					calculations ();
				}else {
					isCorrect = false;
					break;
				}
			}
		}
		return isCorrect;
	}

	boolean term() throws APException{
		boolean isCorrect = factor();
		if(isCorrect) {
			while(multiplicative_operator()){
				if(factor()) {
					stackVal.push(stackVal.pop().Intersection(stackVal.pop()));
				}else {
					isCorrect = false;
					break;
				}
			}
		}
		return isCorrect;
	}


	boolean factor() throws APException{
		return complex() || set () || identifier();
	}
	boolean print_statement() throws APException{
		  int backtrack = cursor;
		  boolean correct = false;
		  if(c == ' ') {
			  nextChar();
		  }
		  if (c == '?') {
			after = true;
			nextChar();
		    correct = expression();
		    
		    if(input.indexOf(')') >= 0) {
				if(input.indexOf('(') < 0){
					throw new APException ("Missing parenthesis");
				}
			}
		    
		    if(correct) {
		    	stackVal.pop().copy().printSet();
		    }
		    
		  }else {
			cursor = backtrack;
		    correct = false;
		  }
		  return correct;
	}
	
	boolean assignment() throws APException{
		int backtrack = cursor;
		boolean correct = false;
		if(c == ' ') {
			nextChar();
		}
		if (c == '?' || c == '/') {
			return false;
		}
		if ( identifier() ) {
			if (input.charAt(cursor) == '=') {
				after = true;
				nextChar();
				correct = expression();
				if(input.indexOf(')') >= 0) {
					if(input.indexOf('(') < 0){
						throw new APException ("Missing parenthesis");
					}
				}
				if(correct) {
					map.replace(mainSet, stackVal.pop().copy());
				}
			}else {
				cursor = backtrack;
				correct = false;
				throw new APException("Missing '='");
			}
		}else {
			cursor = backtrack;
			correct = false;
		}
		return correct;
	}
	
	boolean comment(){
		  int backtrack = cursor;
		  if(c == ' ') {
			  nextChar();
		  }
		  if (c == '/') {
		    return true;
		  }else {
			  cursor = backtrack;
			  return false;
		  }
		}
	boolean statement() throws APException{
		return assignment () || print_statement() || comment();
	}
	
	void program(Scanner in) {
		while(in.hasNextLine()){
			try {
				input = in.nextLine();
				if(!input.isEmpty()) {
					cursor = 0;
					c = input.charAt(cursor);
					statement();
					while(!stackVal.empty()) {
						stackVal.pop();
					}
					after = false;
					mainSet = "";
				}else {
					throw new APException("Empty line");
				}
			}catch (APException e) {
				System.out.println(e);
			}
		}
	}
	
	public static void main(String[] arg) {
		
	}
}
