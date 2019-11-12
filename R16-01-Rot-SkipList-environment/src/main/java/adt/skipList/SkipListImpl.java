package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int height;
	protected int maxHeight;
	protected double PROBABILITY = 0.5;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SkipListImpl(int maxHeight) {
		this.height = maxHeight;
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}
		
		@SuppressWarnings("unchecked")
		private SkipListNode<T>[] gaps(int key) {
			SkipListNode<T>[] update = new SkipListNode[maxHeight];
			SkipListNode<T> node = root;

			for (int i = (height - 1); i >= 0; i--) {
				while (node.getForward(i) != null && node.getForward(i).getKey() < key)
					node = node.getForward(i);
				update[i] = node;
			}
			return update;
		}

	
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = gaps(key);
		SkipListNode<T> node = update[0].getForward(0);

		if (node.getKey() == key) {
			ContainingKey(update, node, height, key, newValue);
		} else {
			WithoutContainingKey(update, node, key, newValue, height);
		}
	}

	private void ContainingKey(SkipListNode<T>[] update, SkipListNode<T> node, int height, int key, T newValue) {
		if (node.height() == height)
			node.setValue(newValue);
		else {
			remove(key);
			WithoutContainingKey(update, node, key, newValue, height);
		}
	}

	private void WithoutContainingKey(SkipListNode<T>[] update, SkipListNode<T> node, int key, T newValue, int height) {
		if (height > this.height) {
			for (int i = this.height; i < height; i++)
				update[i] = root;
			this.height = height;
		}

		node = new SkipListNode<T>(key, height, newValue);
		for (int i = 0; i < height; i++) {
			if (update[i].getForward(i) == null)
				node.getForward()[i] = NIL;
			else
				node.getForward()[i] = update[i].getForward(i);
			update[i].getForward()[i] = node;
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = gaps(key);
		SkipListNode<T> node = update[0].getForward(0);

		if (node.getKey() == key) {
			for (int i = 0; i < height; i++) {
				if (!update[i].getForward(i).equals(node))
					break;

				update[i].forward[i] = node.forward[i];

				while (height > 1 && root.forward[height - 1].equals(NIL))
					height--;
			}
		}
	}

	@Override
	public int height() {
		return recursiveHeight(0);
	}

	private int recursiveHeight(int height) {
		if (root.forward[height] == NIL)
			return height;
		return recursiveHeight(++height);
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> node = this.root;

		for (int i = (height - 1); i >= 0; i--) {
			while (node.forward[i].getKey() < key) {
				node = node.forward[i];
			}
		}

		node = node.forward[0];

		if (node.getKey() != key) {
			node = null;
		}

		return node;
	}

	@Override
	public int size() {
		return recursiveSize(root.getForward(0), 0);
	}

	private int recursiveSize(SkipListNode<T> node, int size) {
		if (node == NIL)
			return size;
		return recursiveSize(node.getForward(0), ++size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[size() + 2];

		return recursiveToArray(array, root, 0);
	}

	private SkipListNode<T>[] recursiveToArray(SkipListNode<T>[] array, SkipListNode<T> node, int i) {
		if (node == null)
			return array;

		array[i] = node;
		return recursiveToArray(array, node.getForward(0), ++i);
	}
}
