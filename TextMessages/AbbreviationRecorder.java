package TextMessages;
/**
 * This program takes input from the user to record texting abbreviations and their meanings.
 * All information is saved to the file, abbreviations.txt.
 * Appends pre-existing data saved to the file.
 * Checks if term is on the list so no term is duplicated.
 *
 * @author James Byrne
 * @version 0.1
 * @since 04/12/2023
 * @email jbyrne03@nyit.edu
 */
import java.io.*;
import java.util.Scanner;

public class AbbreviationRecorder
{
    public static void main(String[] args)
    {
        Scanner keyboard= new Scanner(System.in);
        String choice= "yes";
        String abbrv= null;
        String def= null;
        String compiledInfo= null;

        File abbrvFile= new File("abbreviations.txt");
        PrintWriter outputStream = null;
        Scanner inputStream = null;

        try
        {
            outputStream = new PrintWriter( new FileOutputStream(abbrvFile, true));

            // Adding abbreviations and definitions to abbreviations.txt
            while(!(choice.equalsIgnoreCase("no")))
            {
                int check= 0;

                System.out.println("Please enter text abbreviation: ");
                abbrv= keyboard.nextLine();

                //Checks if abbreviation is already on the list
                inputStream = new Scanner(new FileInputStream(abbrvFile));
                while (inputStream.hasNextLine())
                {
                    String line = inputStream.nextLine();

                    String[] abbrvInfo= line.split(" : ");
                    String abbrvTerm= abbrvInfo[0];
                    String abbrvDef= abbrvInfo[1];

                    if (abbrvTerm.equalsIgnoreCase(abbrv))
                    {
                        check++;
                        break;
                    }
                }
                if (check == 1)
                {
                    System.out.println("That abbreviation is already recorded.");
                    System.out.println();
                    System.out.println("Would you add another word? ");
                    choice= keyboard.nextLine();
                    System.out.println();
                    continue;
                }
                System.out.println("What does it mean?");
                def= keyboard.nextLine();
                compiledInfo= abbrv+" : "+def;
                System.out.println();

                System.out.println(compiledInfo);
                System.out.println("Is that correct? (Write yes to confirm)");
                choice= keyboard.nextLine();

                if (choice.equalsIgnoreCase("yes"))
                {
                    outputStream.println(compiledInfo);
                    System.out.println("Information saved to "+ abbrvFile);
                    System.out.println();
                }
                else
                {
                    System.out.println("Information not saved.");
                    System.out.println();
                }

                System.out.println("Would you add another word? ");
                choice= keyboard.nextLine();
                System.out.println();
            }

            outputStream.close();
            inputStream.close();

        }

        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            System.out.println("File not found.");
            System.exit(0);
        }
        System.out.println("The program is over.");
    }
}
