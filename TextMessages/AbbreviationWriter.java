package TextMessages;
/**
 * This program takes input from the user to record texting abbreviations and their meanings.
 * Abbreviations are stored in an array then saved to the file, abbreviations.txt.
 * Appends pre-existing data saved to the file.
 * Checks if term is on the list so no term is duplicated.
 *
 * @version 0.2
 * @since 04/14/2023
 */
import java.io.*;
import java.util.Scanner;

public class AbbreviationWriter
{
    public static void main(String[] args)
    {
        Scanner keyboard= new Scanner(System.in);
        String choice= null;
        String abbrv= null;
        String def= null;
        String[][] abbreviations;
        int numOfAbbrvs=0;
        int count= 0;
        File abbrvFile= new File("abbreviations.txt");
        PrintWriter outputStream = null;
        Scanner inputStream = null;

        //Setting the size of the array
        while((numOfAbbrvs<=0)||(numOfAbbrvs>100))
        {
            System.out.println("How many abbreviations would you like to add?");
            numOfAbbrvs = keyboard.nextInt();
            keyboard.nextLine();
            if(numOfAbbrvs<=0)
            {
                System.out.println("This must be more than one.");
                System.out.println();
            }
            if (numOfAbbrvs>100)
            {
                System.out.println("The maximum you can enter is 100");
                System.out.println();
            }
        }
        abbreviations = new String[numOfAbbrvs][2];

        try
        {
            outputStream = new PrintWriter( new FileOutputStream(abbrvFile, true));

            //Adding abbreviations to the array
            while(count<numOfAbbrvs)
            {
                int check= 0;

                System.out.println("Please enter text abbreviation: ");
                abbrv= keyboard.nextLine();

                //Checks if abbreviation is already in the file
                inputStream = new Scanner(new FileInputStream(abbrvFile));

                while (inputStream.hasNextLine())
                {
                    String line = inputStream.nextLine();

                    String[] abbrvInfo = line.split(" : ");
                    String abbrvTerm = abbrvInfo[0];
                    String abbrvDef = abbrvInfo[1];

                    if (abbrvTerm.equalsIgnoreCase(abbrv))
                    {
                        check++;
                        break;
                    }

                }
                inputStream.close();

                //Checks if abbreviation is already in the array
                for (int i=0; i<numOfAbbrvs; i++)
                {
                    if(!(abbreviations[i][1]==null)&& (abbreviations[i][0].equalsIgnoreCase(abbrv)))
                    {
                        check++;
                        break;
                    }
                }
                if (check >0)
                {
                    System.out.println("That abbreviation is already recorded.");
                    System.out.println();
                    continue;
                }

                System.out.println("What does it mean?");
                def= keyboard.nextLine();
                System.out.println();

                //Confirms that abbreviation and meaning are accurate
                System.out.println(abbrv+" : "+def);
                System.out.println("Is that correct? (Write yes to confirm)");
                choice= keyboard.nextLine();

                if (choice.equalsIgnoreCase("yes")) //will not save unless choice="yes"
                {
                    abbreviations[count][0]= abbrv;
                    abbreviations[count][1]= def;
                    System.out.println("Information saved.");
                    System.out.println();
                    count++;
                    if(count== numOfAbbrvs)
                    {
                        System.out.println("You have entered "+numOfAbbrvs+" abbreviation(s)");
                        continue;
                    }
                    System.out.println();
                }
                else
                {
                    System.out.println("Information not saved.");
                    System.out.println();
                }

                //Asks user if they would like to continue
                System.out.println("Would you add another word? (Enter 'no' to end program)");
                choice= keyboard.nextLine();
                System.out.println();

                if (choice.equalsIgnoreCase("no"))
                {

                    break;
                }

            }

            // Writing abbreviations and definitions to abbreviations.txt
            for(int i=0; i<numOfAbbrvs;i++)
            {
                if (!(abbreviations[i][0]==null))
                {
                    outputStream.println(abbreviations[i][0]+" : "+abbreviations[i][1]);
                }
            }

            outputStream.close();
        }

        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            System.out.println("File not found.");
            System.exit(0);
        }

        System.out.println("The program has ended.");
    }
}
