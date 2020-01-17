/**	@element : type BigInteger, name value
 *	@structure : 
 *	@domain : 	
 *	@constructor - Identifier();
 *	<dl>
 *		<dt><b>PRE-condition</b><dd>		-
 *		<dt><b>POST-condition</b><dd> 	value is set to zero
 * </dl>
 **/
public interface IdentifierInterface extends Comparable<Identifier>{
	/**	Add char by char to our BigInt value
	 * @precondition -
     *  @postcondition - 
     **/
	public void add (char x);
	/**	
	 * @precondition -
     *  @postcondition - returns 1 if value is greater than x, 0 if they are equal, -1 if value is less than x
     **/
	public int compareTo(Identifier x);
	/**	
	 * @precondition -
     *  @postcondition - FALSE: objects are not equal
     *  				TRUE:  objects are equal
     **/
	public boolean equals(Object o);

}
