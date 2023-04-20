import java.io.File;

public class Dictionary {
    AVLTree<String> tree;
    
    public Dictionary(){
        this.tree = new AVLTree<>();
    }
    
    public Dictionary(String string){
        this.tree = new AVLTree<>();
        tree.root = new BTNode<String>(string);
    }
    
    public Dictionary(File file){
        this.tree = new AVLTree<>();
    }
}
