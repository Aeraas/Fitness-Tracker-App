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
        //this is to load the stuff from the files for the machines memory
        loadWeightsFromFile();
        loadWorkoutsFromFile();
        
        //the choice for the program
        int choice = 0;

        System.out.println("---------------------------");
        System.out.println("Fitness Tracker @Danielius");
        System.out.println("---------------------------");

        //this keeps running until the user inputs a 5 to shut of the whole while loop
        while(choice != 8)
        {
            //the instructions of the algorithm
            printMenu();

            //user inputs a number after the instructions
            choice = UserInput.nextInt();

            //switch statement that checks for all the choices possibilities and gives a response
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
                    viewStats();
                    continue;

                case 8:
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
        System.out.println("7. View Stats");
        System.out.println("8. Exit :>");
    }


    //1
    public static void addWeight()
    {
        UserInput.nextLine();
        System.out.println("Enter a Date: ");
        String date = UserInput.nextLine();

        double weight;

        while(true)
        {
            
            weight = getDoubleInput("Enter a weight in kg: ");

            if(weight > 0)
            {
                break;
            }
            else
            {
                System.out.println("Weight must be greater than 0");
            }

        }

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
            for(WeightEntry i : weightEntries) //for each loop for the array list
            {
                i.display(); //using the display method from the class
            }
        }
    }

    //3
    public static void addWorkout()
    {

        UserInput.nextLine();

        String exercise;
        while(true)
        {
            System.out.print("Enter a exercise: ");
            exercise = UserInput.nextLine();

            if(!exercise.isEmpty())
            {
                break;
            }
            else
            {
                System.out.println("Exercise name cannot be empty");
            }
        }

        int sets;
        while(true)
        {
            sets = getIntInput("Enter a number of sets: ");

            if(sets > 0)
            {
                break;
            }
            else
            {
                System.out.println("Sets must be more than 0");
            }
        }

        int reps;
        while(true)
        {
            reps = getIntInput("Enter a number of reps: ");

            if(reps > 0)
            {
                break;
            }
            else
            {
                System.out.println("Reps must be more than 0");
            }
        }

        //puts the inputs into the WorkoutEntry class and adds into the arrayList
        WorkoutEntry workout = new WorkoutEntry(exercise, sets, reps);
        workoutEntries.add(workout);
        saveWorkoutsToFiles();
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
        delete = getIntInput("Which to delete: ");

        if(delete < 1 || delete > weightEntries.size())
        {
            System.out.println("Invalid entry number.");
            return;
        }

        //delete
        weightEntries.remove(delete - 1);
        saveWeightsToFile();
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
        delete = getIntInput("Which to delete: ");

        if(delete < 1 || delete > workoutEntries.size())
        {
            System.out.println("Invalid entry number.");
            return;
        }

        //delete
        workoutEntries.remove(delete - 1);
        saveWorkoutsToFiles();
    }


    //7
    public static void viewStats()
    {
        System.out.println("\n====== Fitness Stats ======");

        System.out.println("Total weight entries: " + weightEntries.size());
        System.out.println("Total workout entries: " + workoutEntries.size());

        if(weightEntries.isEmpty())
        {
            System.out.println("Latest weight entry: No weight entry");
        }
        else
        {
            WeightEntry latestEntry = weightEntries.getLast();
            System.out.println("Latest weight entry:");
            latestEntry.display();
        }
    }

    //to make sure whatever the user typed int that is valid
    //allows all sorts of inputs just in case
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
    //it gets a user to input an input, and it checks if the input is a double
    //if it's a double it returns the double if not then they have to put another one again
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
        try //to stop any file failing in case
        {
            //this opens or creates a file called weight text
            //PrintWriter lets us write lines with println
            PrintWriter writer = new PrintWriter(new FileWriter("weights.txt"));

            //this goes through each object in the array
            for(WeightEntry entry : weightEntries)
            {
                //this converts the object into text with comas separating the values
                //so that later we can split them with the ","
                writer.println(entry.getDate() + "," + entry.getWeight());
            }

            //file might corrupt if you don't close it always close it
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

            //if the file doesn't exist just stop
            //since first time u run this program the file doesn't exist, and it will just crash it
            if(!file.exists())
            {
                return;
            }

            //this opens the file with a scanner
            //this scanner reads lines instead of user input
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

                    //we rebuild the object for the program
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


//saveWorkoutToFiles
    public static void saveWorkoutsToFiles()
    {
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter("workout.txt"));

            for(WorkoutEntry entry: workoutEntries)
            {
                writer.println(entry.getWorkout()+ "," + entry.getReps() + "," + entry.getSets());
            }

            writer.close();

        } catch (IOException e)
        {
            System.out.println("Error saving workout entries");
        }
    }

    //load the workout files
    public static void loadWorkoutsFromFile()
    {
        try
        {
            File file = new File("workout.txt");

            if(!file.exists())
            {
                return;
            }

            //scanner
            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine();
                String [] parts = line.split(",");

                if(parts.length == 3)
                {
                    //we assign the parts into a variable
                    String Workout = parts[0];
                    int sets = Integer.parseInt(parts[1]);
                    int reps = Integer.parseInt(parts[2]);

                    //we rebuild the object for the program
                    WorkoutEntry entry = new WorkoutEntry(Workout,sets,reps);
                    workoutEntries.add(entry);
                }
            }
        } catch (Exception e)
        {
            System.out.println("Error loading workouts into the program");
        }

    }
}
