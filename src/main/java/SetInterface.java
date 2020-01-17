/**	@element : List of Identifiers
 *	@structure : linear
 *	@domain : 	use created class List in order to store identifiers
 *	@constructor - Set();
 *	<dl>
 *		<dt><b>PRE-condition</b><dd>		-
 *		<dt><b>POST-condition</b><dd> 	new empty list was created;
 * </dl>
 **/
public interface SetInterface {
	/**	add one element to the set
	 * @precondition -
     *  @postcondition - 
     **/
	public void addIdentifier(Identifier d);
	/**	checks if element exist in the set
	 * @precondition -
     *  @postcondition - FALSE - element do not exist
     *  	             TRUE - element exist
     **/
	public boolean elementExist(Identifier d); 
	/**	calculates the union and returns it 
	 * @precondition -
     *  @postcondition - union of to set is returned
     **/
	public Set Union();
	/**	calculates the intersection and returns it
	 * @precondition -
     *  @postcondition - intersection of to set is returned
     **/
	public Set Intersection();
	/**	calculates the Difference and returns it
	 * @precondition -
     *  @postcondition - Difference of to set is returned
     **/
	public Set Difference();
	/**	calculates the Symetric Difference and returns it
	 * @precondition -
     *  @postcondition - Symetric Difference of to set is returned
     **/
	public Set SymetricDifference();
}
