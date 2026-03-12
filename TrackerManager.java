import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class TrackerManager
{
    //variables my arrayList holding the inputs
    private ArrayList<WeightEntry>weightEntries = new ArrayList<>();
    private ArrayList<WorkoutEntry>workoutEntries = new ArrayList<>();
    private Scanner UserInput;
    private FileManager fileManager;

    //----------------------------------------------------------------------------------


    //constructor to get the task of the function what they want to do
    public TrackerManager(Scanner UserInput)
    {
        this.UserInput = UserInput;
        this.fileManager = new FileManager();
    }


    //----------------------------------------------------------------------------------


    //loading data
    public void loadData()
    {
        fileManager.loadWeightsFromFile(weightEntries);
        fileManager.loadWorkoutsFromFile(workoutEntries);
    }


    //----------------------------------------------------------------------------------


    //now for adding into the array lists
    //weightEntries
    public void addWeight()
    {
        UserInput.nextLine();

        LocalDate date;
        while(true)
        {
            System.out.println("Enter date in format (YYYY-MM-DD): ");
            String input = UserInput.nextLine();

            try
            {
                date = LocalDate.parse(input);
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid format. Use (YYYY-MM-DD)");
            }
        }

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
        fileManager.saveWeightsToFile(weightEntries);
        System.out.println("Entry added:");
    }

    //workoutEntries
    public void addWorkout()
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
        fileManager.saveWorkoutsToFiles(workoutEntries);
        System.out.println("Entry added:");
    }


    //----------------------------------------------------------------------------------


    //now for the view methods
    //viewWeights
    public void viewWeight()
    {
        if(weightEntries.isEmpty())
        {
            System.out.println("No entries entered yet");
        }
        else
        {
            System.out.println("Valid entries being printed: ");
            for(int i = 0; i < weightEntries.size(); i++)
            {

                System.out.print((i + 1) + ". ");
                weightEntries.get(i).display();
            }
        }
    }

    //viewWorkouts
    public void viewWorkout()
    {
        if(workoutEntries.isEmpty()) //none
        {
            System.out.println("No workouts recorded yet.");
        }
        else
        {
            System.out.println("Printing out workouts logged:");

            for(int i = 0; i < workoutEntries.size(); i++)
            {

                System.out.print((i + 1) + ". ");
                workoutEntries.get(i).display();
            }
        }
    }



    //----------------------------------------------------------------------------------


    //now for the delete methods
    //deleteWeight
    public void deleteWeight()
    {
        if(weightEntries.isEmpty())
        {
            System.out.println("No entries to delete");
            return;
        }

        int delete;
        //first print all the entries for the user
        viewWeight();

        //ask which to delete
        delete = getIntInput("Which to delete: ");

        if(delete < 1 || delete > weightEntries.size())
        {
            System.out.println("Invalid entry number.");
            return;
        }

        //delete
        weightEntries.remove(delete - 1);
        fileManager.saveWeightsToFile(weightEntries);
        System.out.println("Weight entry deleted");
    }


    //deleteWorkout
    public void deleteWorkout()
    {
        if(workoutEntries.isEmpty()) //none
        {
            System.out.println("No workouts recorded yet.");
        }

        int delete;

        //first print all the entries for the user
        viewWorkout();

        //ask which to delete
        delete = getIntInput("Which to delete: ");

        if(delete < 1 || delete > workoutEntries.size())
        {
            System.out.println("Invalid entry number.");
            return;
        }

        //delete
        workoutEntries.remove(delete - 1);
        fileManager.saveWorkoutsToFiles(workoutEntries);
        System.out.println("Workout entry deleted");
    }


    //----------------------------------------------------------------------------------

    //view stats method
    public void viewStats()
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

    //----------------------------------------------------------------------------------

    //for the getIntInput and getDoubleInput

    //to make sure whatever the user typed int that is valid
    //allows all sorts of inputs just in case
    private int getIntInput(String message)
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

    //getDoubleInput
    //to make sure what they type is a valid double
    //it gets a user to input an input, and it checks if the input is a double
    //if it's a double it returns the double if not then they have to put another one again
    private double getDoubleInput(String message)
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

    //----------------------------------------------------------------------------------
}
