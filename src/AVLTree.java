public class AVLTree<T extends Comparable<T>> extends BST<T> {
	
   protected int height;
	
	public AVLTree() {
		super();
		height = -1;
	}
	
	public AVLTree(BTNode<T> root) {
		super(root);
		height = 0;
	}
	
	public void purge(){
		super.purge();
	}
	
	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(BTNode<T> node) {
      if(node == null)
         return -1;
      else
         return 1 + Math.max(getHeight(node.left), getHeight(node.right));
   }
	
   private AVLTree<T> getLeftAVL() {
      AVLTree<T> leftsubtree = new AVLTree<T>(root.left);
      return leftsubtree;
   }

   private AVLTree<T> getRightAVL() {
      AVLTree<T> rightsubtree = new AVLTree<T>(root.right);
      return rightsubtree;
    }
    
	protected int getBalanceFactor() {
      if(isEmpty())
         return 0;
      else
         return getRightAVL().getHeight() - getLeftAVL().getHeight();
    }
    
    public void insertAVL(T el)  {
      super.insert(el);
      this.balance();
      
    }
    
    public void deleteAVL(T el) {
      super.deleteByCopying(el);
      this.balance();
	  
    }
    
    protected void balance()
    {
      if(!isEmpty())
      {
         getLeftAVL().balance();
    	   getRightAVL().balance();

         adjustHeight();
        
         int balanceFactor = getBalanceFactor();
        
         if(balanceFactor == -2) {
			// System.out.println("Balance factor = " + balanceFactor);
			// System.out.println("Balancing node with el: "+root.data);
			
			
			if(getRightAVL().getBalanceFactor() == 0 && getLeftAVL().getBalanceFactor() == -1)    /// special case
				  rotateRight();                                
            else if(getLeftAVL().getBalanceFactor() <= 0)     
			      rotateRight();
            else
               rotateLeftRight();
         }
		
         else if(balanceFactor == 2) {
			// System.out.println("Balance factor = " + balanceFactor);
         //    System.out.println("Balancing node with el: "+root.data);
			
			if(getRightAVL().getBalanceFactor() == 0)         /// special case that cannot be done
				  rotateLeft();                                /// by double rotations
            else if(getRightAVL().getBalanceFactor() > 0)   
               rotateLeft();
            else
               rotateRightLeft();
         }
      }
   }
    
   protected void adjustHeight()
   {
      if(isEmpty())
         height = -1;
      else
         height = 1 + Math.max(getLeftAVL().getHeight(), getRightAVL().getHeight());   
   }
    
   protected void rotateRight() {
      if( isEmpty()) throw new UnsupportedOperationException("Empty") ;
      BTNode<T> temp = root.right;
      root.right = root.left;
      root.left = root.right.left;
      root.right.left = root.right.right;
      root.right.right= temp;

      T val = (T)root.data;
      root.data = root.right.data;
      root.right.data =val;

      getRightAVL().adjustHeight();
      adjustHeight();  
   }
    
   protected void rotateLeft() {
      BTNode<T> tempNode = root.left;
      root.left = root.right;
      root.right = root.left.right;
      root.left.right = root.left.left;
      root.left.left = tempNode;
            
      T val = (T) root.data;
      root.data = root.left.data;
      root.left.data = val;
            
      getLeftAVL().adjustHeight();
      adjustHeight();
	}
	
	protected void rotateLeftRight()
   {
 	  getLeftAVL().rotateLeft();
      getLeftAVL().adjustHeight();
      this.rotateRight();
      this.adjustHeight();
  }

   protected void rotateRightLeft()
   {
      getRightAVL().rotateRight();
      getRightAVL().adjustHeight();
      this.rotateLeft();
      this.adjustHeight();
	  
   }
   
   public void levelOrderTraversal(){
	   levelOrderTraversal(root);
   }
      
}