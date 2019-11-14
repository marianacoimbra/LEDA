package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			DoubleLinkedListNode<T> newHeadNode = new DoubleLinkedListNode<T>();
			newHeadNode.setData(element);
			newHeadNode.setNext(this.getHead());
			newHeadNode.setPrevious(new DoubleLinkedListNode<T>());
			((DoubleLinkedListNode<T>) head).setPrevious(newHeadNode);
			if (head.isNIL()) {
				setLast(newHeadNode);
			}

			setHead(newHeadNode);
		}
	}

	@Override
	public void removeFirst() {
		if(!getHead().isNIL()) {
			head = head.next;
		}
		if(head.isNIL()) {
			last = (DoubleLinkedListNode<T>) head;
		}	
		((DoubleLinkedListNode<T>) head).setPrevious(new DoubleLinkedListNode<T>());
	}

	@Override
	public void removeLast() {
		if(!last.isNIL()) {
			last = last.getPrevious();
		}
		if(last.isNIL()) {
			head = last;
		}
		last.setNext(new DoubleLinkedListNode<T>());
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
	
	@Override
	public void remove (T element) {
		if (element != null) {
			if (this.getHead().getData().equals(element)) this.removeFirst();
			else if (this.last.getData().equals(element)) this.removeLast();
			else {
				DoubleLinkedListNode<T> node = (DoubleLinkedListNode) this.getHead();

				 while (!node.isNIL() && !node.getData().equals(element)) {
				 	node = (DoubleLinkedListNode<T>) node.getNext();
				 }

				 if (!node.isNIL()) {
				 	node.getPrevious().setNext(node.getNext());
					 ((DoubleLinkedListNode<T>)node.getNext()).setPrevious(node.getPrevious());
				 }
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode();

			newLast.setData(element);
			newLast.setNext(new DoubleLinkedListNode());
			newLast.setPrevious(this.getLast());

			this.getLast().setNext(newLast);

			if (this.getLast().isNIL()) {
				this.setHead(newLast);
			}

			this.setLast(newLast);
		}
	}
}
