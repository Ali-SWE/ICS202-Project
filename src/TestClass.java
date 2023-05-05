import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) throws Exception{
        // Asking for the filename
        Scanner input = new Scanner(System.in);
        
        // offering type of dictionary
        
        // Creating a dictionary
        Dictionary dictionary;
        boolean saved = false;
        while(true){
            try{

                System.out.println("Type the number of the dictionary you want: ");
                System.out.println("1. A dictionary with no words. ");
                System.out.println("2. A dictionary with single word. ");
                System.out.println("3. A dictionary from a file>  ");
                int type = input.nextInt();
            
                if(type == 1){
                    dictionary = new Dictionary();
                    break;
                    
                }
                else if(type == 2){
                    System.out.println("Add a word: ");
                    dictionary = new Dictionary(input.next());
                    break;       
                }
                else if(type == 3){
                    System.out.print("Enter filename> ");
                    String fileName = input.next();
                    dictionary = new Dictionary(new File(fileName));
                    break;
                }
                else{
                    System.out.println("Choose a valid option, please.");
                }
            }
            catch(IOException ex){
                System.out.println("This file does not exist.\n");
            }
        }

        // Suggesting operations
        while(true){ // keep asking until the user chooses to save the file or exit
            try{
                System.out.println("Type the number of the operation you want to do:");
                System.out.println("1. Adding a word into the dictionary.");
                System.out.println("2. Deleting a word from the dictionary.");
                System.out.println("3. Searching for a word in the dictionary.");
                System.out.println("4. Searching for similar words to a specific word.");
                System.out.println("5. Exit.");
                int option = input.nextInt();

                if(option == 1){ // Adding a word
                    while(true){
                        System.out.print("add new word> ");
                        dictionary.addWord(input.next());

                        System.out.println("Do you want to add another word? (Y/N)");
                        String addAgain = input.next();
                        if(addAgain.equals("N") | addAgain.equals("n"))
                            break;
                    }
                }
                
                else if(option == 2){ // Deleting a word
                    while(true){
                        System.out.print("remove word>  ");
                        dictionary.deleteWord(input.next());

                        System.out.println("Do you want to delete another word? (Y/N)");
                        String deleteAgain = input.next();
                        if(deleteAgain.equals("N") | deleteAgain.equals("n"))
                            break;
                    }
                }

                else if(option == 3){ // Searching for a word
                    while(true){
                        System.out.print("check word> ");
                        boolean found = dictionary.findWord(input.next());
                        if(found)
                            System.out.println("Word found. ");
                        else
                            System.out.println("Word not found. ");

                        System.out.println("Do you want to search for another word? (Y/N)");
                        String searchAgain = input.next();
                        if(searchAgain.equals("N") | searchAgain.equals("n"))
                            break;
                    }
                }

                else if(option == 4){ // Searching for similar words
                    while(true){

                        System.out.print("search for similar words>  ");
                        String word = input.next();
                        String[] array = dictionary.findSimilar(word);
                        if(array.length == 0)
                            System.out.println("There are no words similar to ' " + word + " '");
                        else
                            System.out.println("Words similar to " + word + " are " + Arrays.toString(array));
                        System.out.println("Do you want to search for another word? (Y/N)");
                        String searchSimilarAgain = input.next();
                        if(searchSimilarAgain.equals("N") | searchSimilarAgain.equals("n"))
                            break;  
                    }
                }


                else if(option == 5){ // Exit
                    if(!saved){
                        System.out.println("Save Updated Dictionary (Y/N)> ");
                        String choice = input.next();
                        if(choice.equals("Y") | choice.equals("y")){
                            System.out.print("Enter filename> ");
                            dictionary.saveFile(input.next());
                        }
                        else if(choice.equals("N") | choice.equals("n"))
                            break;
                        else{
                            System.out.println("Wrong input. Please, choose a valid option.");
                        }
                    }
                    break;
                }

                else{ // wrong option
                    System.out.println("Please choose a valid option.");
                }
            
            }
            catch(Exception ex){

            }
        }
        
    

    }
}
