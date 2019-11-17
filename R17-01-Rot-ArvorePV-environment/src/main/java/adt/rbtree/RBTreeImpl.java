package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      if (isEmpty()) {
         return 0;
      }
      return recursiveBlackHeight((RBNode<T>) root);
   }

   private boolean isBlack(RBNode<T> node) {
      return node.getColour() == Colour.BLACK;
   }

   private boolean isRed(RBNode<T> node) {
      return node.getColour() == Colour.RED;
   }

   private boolean isNIL(RBNode<T> node) {
      return node.isEmpty();
   }

   private int recursiveBlackHeight(RBNode<T> node) {
      if (node == null || node.isEmpty())
         return 0;

      int height = 0;

      if (isBlack(node))
         height = 1;

      return height
            + Math.max(recursiveBlackHeight((RBNode<T>) node.getLeft()),
                  recursiveBlackHeight((RBNode<T>) node.getRight()));
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return recursiveVerifyChildrenOfRedNodes((RBNode<T>) this.getRoot());
   }

   private boolean recursiveVerifyChildrenOfRedNodes(RBNode<T> node) {
      if (isNIL(node))
         return true;

      if (isRed(node)) {
         if (isRed((RBNode<T>) node.getLeft()) && isRed((RBNode<T>) node.getRight()))
            return false;
      }

      return recursiveVerifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
            && recursiveVerifyChildrenOfRedNodes((RBNode<T>) node.getRight());
   }

   /**
    * Verifies the black-height property from the root. The method blackHeight
    * returns an exception if the black heights are different.
    * 
    * @throws Exception
    */
   private boolean verifyBlackHeight() {
      if (recursiveBlackHeight((RBNode<T>) root.getLeft()) == recursiveBlackHeight((RBNode<T>) root.getRight()))
         return true;
      throw new RuntimeException();
   }

   @Override
   public void insert(T value) {
      if (value != null)
         recursiveInsert((RBNode<T>) this.getRoot(), value);
   }

   private void recursiveInsert(RBNode<T> node, T value) {
      if (node.isEmpty()) {
         node.setData(value);
         node.setLeft(new RBNode<T>());
         node.getLeft().setParent(node);
         node.setRight(new RBNode<T>());
         node.getRight().setParent(node);
         node.setColour(Colour.RED);
         fixUpCase1(node);
      } else if (value.compareTo(node.getData()) < 0) {
         recursiveInsert((RBNode<T>) node.getLeft(), value);
      } else if (value.compareTo(node.getData()) > 0) {
         recursiveInsert((RBNode<T>) node.getRight(), value);
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public RBNode<T>[] rbPreOrder() {
      RBNode<T>[] array = new RBNode[size()];
      rbPreOrder(array, 0, (RBNode<T>) root);
      return array;
   }

   private int rbPreOrder(RBNode<T>[] array, int index, RBNode<T> node) {
      if (!node.isEmpty()) {
         array[index++] = node;
         index = rbPreOrder(array, index, (RBNode<T>) node.getLeft());
         index = rbPreOrder(array, index, (RBNode<T>) node.getRight());
      }
      return index;
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node.equals(root))
         node.setColour(Colour.BLACK);
      else
         fixUpCase2(node);
   }

   protected void fixUpCase2(RBNode<T> node) {

      if (!isBlack((RBNode<T>) node.getParent()))
         fixUpCase3(node);

   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandParent = (RBNode<T>) parent.getParent();
      RBNode<T> uncle;

      if (isLeftChild(parent))
         uncle = (RBNode<T>) grandParent.getRight();
      else
         uncle = (RBNode<T>) grandParent.getLeft();

      if (!isBlack(uncle)) {
         parent.setColour(Colour.BLACK);
         uncle.setColour(Colour.BLACK);
         grandParent.setColour(Colour.RED);
         fixUpCase1(grandParent);
      } else {
         fixUpCase4(node);
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> next = node;
      RBNode<T> parent = (RBNode<T>) node.getParent();

      if (!((isLeftChild(node) && isLeftChild(parent)) || (!isLeftChild(node) && !isLeftChild(parent)))) {

         if (isLeftChild(node))
            rightRotation(parent);
         else
            leftRotation(parent);

         fixUpCase5(parent);

      } else {
         fixUpCase5(next);
      }
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandParent = (RBNode<T>) parent.getParent();

      parent.setColour(Colour.BLACK);
      grandParent.setColour(Colour.RED);

      if (isLeftChild(node))
         rightRotation(grandParent);
      else
         leftRotation(grandParent);
   }

   private void rightRotation(RBNode<T> node) {
      if (node != null) {
         RBNode<T> aux = (RBNode<T>) Util.rightRotation(node);
         if (root.equals(node)) {
            this.root = aux;
         }
      }
   }

   private void leftRotation(RBNode<T> node) {
      if (node != null) {
         RBNode<T> aux = (RBNode<T>) Util.leftRotation(node);
         if (root.equals(node)) {
            this.root = aux;
         }
      }
   }
}