package TextMessages;
/**
 *This program reads text messages inputted by the user and replaces text messages abbreviations with their meanings.
 *Checks every word inputted against a list of abbreviations contained in the file, abbreviations.txt.
 *
 * @version 0.1
 * @since 04/14/2023
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextTranslator
{
    public static void main(String[] args)
    {
        File abbrvFile= new File("abbreviations.txt");
        Scanner keyboard = new Scanner(System.in);
        Scanner inputStream = null;
        String message= null;
        String choice= "yes";

        System.out.println("Welcome to the Text Message Translator.");
        System.out.println("I will translate abbreviations with their meanings.");
        System.out.println();

        while(!(choice.equalsIgnoreCase("no")))
        {
            String translation= "";


            System.out.println("Enter the text message you would like to translate: ");
            message= keyboard.nextLine();
                String[] msgArray= message.split(" "); //Splits string into words

                //Cycles through each word and compares them to abbreviations in file
                for (String m: msgArray)
                {
                    try
                    {
                        inputStream = new Scanner(new FileInputStream(abbrvFile));

                        while (inputStream.hasNextLine())
                        {

                            String line = inputStream.nextLine();

                            String[] abbrvInfo = line.split(" : ");
                            String abbrvTerm = abbrvInfo[0];
                            String abbrvDef = abbrvInfo[1];

                            //If an abbreviation is found and replaced, the program will stop comparing that word to the file.
                            if (m.equalsIgnoreCase(abbrvTerm))
                            {
                                m = abbrvDef;
                                break;
                            }
                        }
                        translation = translation + m + " ";
                        inputStream.close();
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.println(e.getMessage());
                        System.out.println("File not found.");
                        System.exit(0);
                    }
                }

            System.out.println();
            System.out.println("This message means:");
            System.out.println(translation);
            System.out.println();

            //Asks user if they want to translate another message
            System.out.println("Would you like to translate another message? (Enter 'no' to end program)");
            choice= keyboard.nextLine();
            System.out.println();
        }
        System.out.println("The program has ended.");
    }
}
