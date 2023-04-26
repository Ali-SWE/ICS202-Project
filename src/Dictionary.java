import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class Dictionary {
    private AVLTree<String> tree;
    
    public Dictionary(){ // This constructor creates an empty AVL tree.
        this.tree = new AVLTree<>();
    }
    
    public Dictionary(String string){ // This constructor creates an AVL tree with a root containing the specified string.
        this.tree = new AVLTree<>();
        tree.insertAVL(string);
    }
    
    public Dictionary(File file){ // This constructor creates an AVL tree with nodes containing the words in a file.
        this.tree = new AVLTree<>();
        try(Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNext()){
                String word = fileScanner.next();
                if(!tree.search(word))
                    tree.insertAVL(word);
            }
            System.out.println("\ndictionary loaded successfully.");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex);
        }
    }

    public void addWord(String s) throws WordAlreadyExistsException{
        if(!findWord(s)){ // The word is not in the dictionary.
            tree.insertAVL(s);
            System.out.println("\nword added successfully.");
        }
        else{ // The word is already in the dictionary
            throw new WordAlreadyExistsException("The word is already in the dictionary.");
        }
    }

    public boolean findWord(String s){
        if(tree.search(s)){
            return true;
        }
        else{
            return false;
        }
    }

    public void deleteWord(String s) throws WordNotFoundException{
        if(findWord(s)){ // The word is in the dictionary
            tree.deleteAVL(s);
            System.out.println("\nword deleted successfully.");
        }
        else{ // // The word is not in the dictionary
            throw new WordNotFoundException("word not found.");
        }
    }

    public String[] findSimilar(String s){
        SLL<String> list = new SLL<String>();
        findSimilar(s, tree.root, list);

        // converting the SLL to array
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.deleteFromHead();
        }
        return array;       
    }

    public void findSimilar(String s, BTNode node, SLL<String> list){
        if(node == null)
            return;
        
        if(Math.abs(s.length() - node.data.toString().length()) == 1){ // the difference of their length is 1
            ////
        }
        else if(s.length() == node.data.toString().length()){ // same length
            int numberOfdifferentLetters = 0;
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) != node.data.toString().charAt(i)){
                    numberOfdifferentLetters += 1;
                }
            }
            if(numberOfdifferentLetters == 1){
                list.addToTail(node.data.toString());
                System.out.println(node.data.toString());
            }
        }
        findSimilar(s, node.right, list);
        findSimilar(s, node.left, list);

    }
    public void saveFile(String fileName){ 
        File newFile = new File(fileName);
        try(PrintWriter writer = new PrintWriter(newFile)){

            writeDictionaryInFile(tree.root, writer);
            System.out.println("Dictionary saved successfully.");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex);
        }
    }

    private void writeDictionaryInFile(BTNode node,PrintWriter writer){ 
        if (node == null) 
            return; 
  
        writeDictionaryInFile(node.left, writer); 
        writer.println(node.data);
        writeDictionaryInFile(node.right, writer); 
    } 


}
