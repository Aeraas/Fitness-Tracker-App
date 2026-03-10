import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FitnessTracker
{
    //scanner
    static Scanner UserInput = new Scanner(System.in);

    //array list
    static ArrayList<WeightEntry>weightEntries = new ArrayList<>();
    static ArrayList<WorkoutEntry>workoutEntries = new ArrayList<>();

    public static void main(String [] args)
    {
        //this is to load the stff from the files for the machines memory
        loadWeightsFromFile();
        //loadWorkoutsFromFile();
        
        //the choice for the program
        int choice = 0;

        System.out.println("---------------------------");
        System.out.println("Fitness Tracker @Danielius");
        System.out.println("---------------------------");

        //this keeps running until the user inputs a 5 to shut of the whole while loop
        while(choice != 7)
        {
            //the instructions of the algorithm
            printMenu();

            //user inputs a number after the instructions
            choice = UserInput.nextInt();

            //switch statement that checks for all the choices posibilites and gives a response
            switch (choice)
            {
                //add weight
                case 1:
                    addWeight();
                    continue;

                case 2: //the view WeightEntry
                   viewWeight();
                   continue;

                case 3: //the add workout function where user inputs on instruction
                    addWorkout();
                    continue;

                case 4: //the view workout List
                    viewWorkout();
                    continue;

                case 5: //delete weight Entry
                   deleteWeight();
                   continue;

                case 6: //delete workout entry
                   deleteWorkout();
                   continue;

                case 7:
                    System.out.println("Thank you for your time! GOODBYE");
                    continue;

                default:
                    System.out.println("Invalid entry please try again");
            }

        }

        UserInput.close();

    }

    public static void printMenu()
    {
        System.out.println("You have a few Options");
        System.out.println("1. Add Weight");
        System.out.println("2. View Weight");
        System.out.println("3. Add Workout");
        System.out.println("4. View Workout");
        System.out.println("5. Delete WeightEntry");
        System.out.println("6. Delete WorkoutEntry");
        System.out.println("7. Exit :>");
    }


    //1
    public static void addWeight()
    {
        UserInput.nextLine();
        System.out.println("Enter a Date: ");
        String date = UserInput.nextLine();

        double weight = getDoubleInput("Enter a weight in kg: ");

        //makes a WeightEntry item from the inputs then adds it to its arrayList
        WeightEntry entry = new WeightEntry(date, weight);
        weightEntries.add(entry);
        saveWeightsToFile();

    }

    //2
    public static void viewWeight()
    {
        if(weightEntries.isEmpty())
        {
            System.out.println("No entries entered yet");
        }
        else
        {
            System.out.println("Valid entries being printed: ");
            for(WeightEntry i : weightEntries) //for each loop for the array lsit
            {
                i.display(); //using the display method from the class
            }
        }
    }

    //3
    public static void addWorkout()
    {

        UserInput.nextLine();
        System.out.print("Enter a excersise: ");
        String excersise = UserInput.nextLine();

        int sets = getIntInput("Enter a number of sets: ");

        int reps = getIntInput("Enter a number of reps: ");

        //puts the inputs into the WorkoutEntry class and adds into the arrayList
        WorkoutEntry workout = new WorkoutEntry(excersise, sets, reps);
        workoutEntries.add(workout);
    }

    //4
    public static void viewWorkout()
    {
        if(workoutEntries.isEmpty()) //none
        {
            System.out.println("No workouts recorded yet.");
        }
        else
        {
            System.out.println("Printing out workouts logged:");

            for(WorkoutEntry i : workoutEntries) //for each loop that calls the display of the class
            {
                i.display();
            }
        }
    }

    //5
    public static void deleteWeight()
    {
        int delete;
        //first print all the entries for the user
        for(int i = 0; i < weightEntries.size(); i++)
        {

            System.out.print((i + 1) + ". ");
            weightEntries.get(i).display();
        }

        //ask which to delete
        System.out.println("Which to delete: ");
        delete = UserInput.nextInt();

        //delete
        weightEntries.remove(delete - 1);
    }

    //6
    public static void deleteWorkout()
    {
        int delete;
        //first print all the entries for the user
        for(int i = 0; i < workoutEntries.size(); i++)
        {

            System.out.print((i + 1) + ". ");
            workoutEntries.get(i).display();
        }

        //ask which to delete
        System.out.println("Which to delete: ");
        delete = UserInput.nextInt();

        //delete
        workoutEntries.remove(delete - 1);
    }

    //to make sure whatever the user typed a int that is valid
    //allows all sorts of inputs just incase
    public static int getIntInput(String message)
    {
        while(true)
        {
            System.out.println(message);
            if(UserInput.hasNextInt())
            {
                return UserInput.nextInt();
            }
            else
            {
                System.out.println("Input a valid input, enter a valid number");
                UserInput.next();
            }
        }
    }

    //to make sure what they type is a valid double
    //it gets a user to input a input and it checks if the input is a double
    //if its a double it returns the double if not then they have to put another one again
    public static double getDoubleInput(String message)
    {
        while(true)
        {
            System.out.println(message);
            if(UserInput.hasNextDouble())
            {
                return UserInput.nextDouble();
            }
            else
            {
                System.out.println("Input a valid input, enter a valid number");
                UserInput.next();
            }
        }
    }



    //Save WeightsToFile
    public static void saveWeightsToFile()
    {
        try //to stop the any file failing in case
        {
            //this opens or creates a file called weight text
            //PrintWriter lets us write lines with println
            PrintWriter writer = new PrintWriter(new FileWriter("weights.txt"));

            //this goes through each object in the array
            for(WeightEntry entry : weightEntries)
            {
                //this converts the object into text with comas seperating the valeus
                //so that later we can split them with the ,
                writer.println(entry.getDate() + "," + entry.getWeight());
            }

            //file might corrurpt if you dont close it always close it
            writer.close();
        }catch (IOException e)
        {
            System.out.println("Error saving weight entries.");
        }
    }


    //load weights to files
    public static void loadWeightsFromFile()
    {
        try
        {
            //this creates a file object that represents the file
            File file = new File("weights.txt");

            //if the file doesnt exist juststop
            //since first time u run this program the file doesnt exist and it will just crash it
            if(!file.exists())
            {
                return;
            }

            //this opens the file with a scanner
            //this scanner reades lines instead of user input
            Scanner fileScanner = new Scanner(file);

            //runs once per line in file
            while(fileScanner.hasNextLine())
            {
                //gets the line
                String line = fileScanner.nextLine();
                //splits the parts between the comma
                String [] parts = line.split(",");

                if(parts.length == 2)
                {
                    //we assign the parts into a variable
                    String date = parts[0];
                    double weight = Double.parseDouble(parts[1]);

                    //we rebuild the object for the progtam
                    WeightEntry entry = new WeightEntry(date,weight);
                    weightEntries.add(entry);
                }
            }

            fileScanner.close();
        } catch(IOException e)
        {
            System.out.println("Error loading weight entries.");
        }
    }














}
