import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class FileManager
{
    //saving weights and workouts
    //we remove static in the methods since we are going to call
    //FileManager as an object rather than the class itself
    //the object handles file operations (more object-oriented)

    //Save WeightsToFile
    public void saveWeightsToFile(ArrayList<WeightEntry> weightEntries)
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


    //save workouts in files
    public void saveWorkoutsToFiles(ArrayList<WorkoutEntry> workoutEntries)
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

    //now for the load for weightEntries and workoutEntries

    //load weights to files
    public void loadWeightsFromFile(ArrayList<WeightEntry> weightEntries)
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
                    LocalDate date = LocalDate.parse(parts[0]);
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


    //load for workoutEntries
    public void loadWorkoutsFromFile(ArrayList<WorkoutEntry> workoutEntries)
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

            fileScanner.close();

        } catch (Exception e)
        {
            System.out.println("Error loading workouts into the program");
        }

    }


}
