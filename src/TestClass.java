import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) throws Exception{
        // Asking for the filename
        Scanner input = new Scanner(System.in);
        System.out.print("Enter filename> ");
        String fileName = input.next();
        
        // creating the dictionary
        Dictionary dictionary = new Dictionary(new File(fileName));

        // Suggesting operations
        while(true){ // keep asking the user choose to save the file or exit
            System.out.println("Type the number of the operation you want to do:");
            System.out.print("1. Adding a word into the dictionary.");
            System.out.print("2. Deleting a word from the dictionary.");
            System.out.print("3. Searching for a word in the dictionary.");
            System.out.print("4. Searching for similar words to a specific word.");
            System.out.print("5. Save.");
            System.out.print("6. Exit.");
            int option = input.nextInt();

            if(option == 1){ // Adding a word

            }
            
            else if(option == 2){ // Deleting a word

            }

            else if(option == 3){ // Searching for a word
            
            }

            else if(option == 4){ // Searching for similar words

            }

            else if(option == 5){ // Save

            }

            else if(option == 6){ // Exit
                ///
                ///
                ///
                break;
            }

            else{ // wrong option

            }
        }

    }
}
