import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) throws Exception{
        // Asking for the filename
        Scanner input = new Scanner(System.in);
        System.out.print("Enter filename> ");
        String fileName = input.next();
        
        // creating the dictionary
        try{
        Dictionary dictionary = new Dictionary(new File(fileName));

        boolean saved = false;
        // Suggesting operations
        while(true){ // keep asking until the user chooses to save the file or exit
            
            System.out.println("Type the number of the operation you want to do:");
            System.out.println("1. Adding a word into the dictionary.");
            System.out.println("2. Deleting a word from the dictionary.");
            System.out.println("3. Searching for a word in the dictionary.");
            System.out.println("4. Searching for similar words to a specific word.");
            System.out.println("5. Save.");
            System.out.println("6. Exit.");
            int option = input.nextInt();

            if(option == 1){ // Adding a word
                System.out.print("add new word> ");
                dictionary.addWord(input.next());
            }
            
            else if(option == 2){ // Deleting a word
                System.out.print("remove word>  ");
                dictionary.deleteWord(input.next());
            }

            else if(option == 3){ // Searching for a word
                System.out.print("check word> ");
                boolean found = dictionary.findWord(input.next());
                if(found)
                    System.out.println("Word found. ");
                else
                    System.out.println("Word not found. ");
            }

            else if(option == 4){ // Searching for similar words
                System.out.print("search for similar words>  ");
                String word = input.next();
                String[] array = dictionary.findSimilar(word);
                if(array.length == 0)
                    System.out.println("There are no words similar to ' " + word + " '");
                else
                    System.out.println("Words similar to " + word + " are " + Arrays.toString(array));
            }

            else if(option == 5){ // Save
                System.out.print("Enter filename> ");
                dictionary.saveFile(input.next());
                saved = true;
            }

            else if(option == 6){ // Exit
                if(!saved){
                    System.out.println("Save Updated Dictionary (Y/N)> ");
                    String choice = input.next();
                    if(choice.equals("Y")){
                        System.out.print("Enter filename> ");
                        dictionary.saveFile(input.next());
                    }
                    else if(choice.equals("N"))
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
    }
    catch(Exception ex){
        System.out.println(ex);
    }

    }
}
