public class List<E extends Comparable<E>> implements ListInterface<E>{

    private class Node {

        E data;
        Node prior,
                next;

        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }

    }
    public Node header;
    private int size;
    
    public List() {
    	header = new Node(null);
    	size = 0;
    }

    @Override
    public boolean isEmpty() {
    	if (header.data == null) {
    		return true;
    	}
    	else
    		return false;
    }

    @Override
    public ListInterface<E> init() {
    	header = new Node(null);
    	size = 0;
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListInterface<E> insert(E d) {
    	goToFirst();
        Node new_Node = new Node(d); 
        
        if(isEmpty()) {
        	header = new_Node; 
        }
        else {
        	Node x = header;
        	if(size == 1) {
        		if(new_Node.data.compareTo(x.data) > 0) {
        			x.prior = new_Node;
        			new_Node.next = x;
        		}else {
        			x.next = new_Node;
        			new_Node.prior = x;
        		}
        	}else {
        		while(x.prior != null && new_Node.data.compareTo(x.data) > 0) {
        		
        			x = x.prior;
        		}
        	
        		if(x.next != null && x.prior != null) {
        			x.next.prior = new_Node;
        			new_Node.next = x.next;
        			new_Node.prior = x;
        			x.next = new_Node;
        		}
        	
        		else if(x.prior == null){
        			new_Node.prior = null;
        			x.prior = new_Node;
        			new_Node.next = x;
        		}
        		else {
        			new_Node.prior = x;
        			x.next = new_Node;
        		}
        	}
        }
    	header = new_Node;
    	size++;
        return this;
    }

    @Override
    public E retrieve() {
    	if(isEmpty()) {
    		return null;
    	}
    	else {
    		return header.data;
    	}
        
    }

    @Override
    public ListInterface<E> remove() {
    	if(header.prior != null && header.next != null) {
    		header.prior.next = header.next;
    		header.next.prior = header.prior;
    		header = header.prior;
    	}
    	else if(header.prior == null && header.next != null) {
    		header.next.prior = null;
    		header = header.next;
    	}
    	else if(header.prior != null && header.next == null) {
    		header.prior.next = null;
    		header = header.prior;
    	}
    	else {
    		header.data = null;
    	}
    	size--;
        return this;
    }

    @Override
    public boolean find(E d) {
    	Node previous = header;
    	goToFirst();
    	Node x = header;
    	while (x.prior != null) {
    		if(d.equals(x.data)) {
    			header = previous;
    			return true;
    		}
    		x = x.prior;
    	}
    	if(d.equals(x.data)) {
    		header = previous;
    		return true;
    	}
    	else {
    		header = previous;
    		return false;
    	}
    	
    }

    @Override
    public boolean goToFirst() {
    	if(isEmpty()) {
    		return false;
    	}
    	else {
    		while(header.next != null){
    			header = header.next;
    		}
    		return true;
    	}
    }

    @Override
    public boolean goToLast() {
    	if(isEmpty()) {
    		return false;
    	}
    	else {
    		while(header.prior != null){
    			header = header.prior;
    		}
    		return true;
    	}
    }

    @Override
    public boolean goToNext() {
    	if(isEmpty()) {
    		return false;
    	}
    	else if(header.prior == null) {
    		return false;
    	}
    	else {
    		header = header.prior;
    		return true;
    	}
    }

    @Override
    public boolean goToPrevious() {
    	if(isEmpty()) {
    		return false;
    	}
    	else if(header.next == null) {
    		return false;
    	}
    	else {
    		header = header.next;
    		return true;
    	}
    }

    @Override
    public ListInterface<E> copy(){
    	List<E> listClone = new List<E> ();
    	goToFirst();
    	Node x = header;
    	if(!isEmpty()) {
    		while(x.prior != null) {
    			listClone.insert(x.data);
    			x = x.prior;
    		}
    		listClone.insert(x.data);
    		return listClone;
    	}
    	else {
    		return null;
    	}
    }
}
