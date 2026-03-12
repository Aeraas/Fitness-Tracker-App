import java.util.Scanner;

public class FitnessTracker
{
    //scanner
    static Scanner UserInput = new Scanner(System.in);

    //making an trackerManager object
    static TrackerManager trackerManager = new TrackerManager(UserInput);



    public static void main(String [] args)
    {
        //this is to load the stuff from the files for the machines memory
        trackerManager.loadData();
        
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
            choice = getMenuChoice(UserInput);

            //switch statement that checks for all the choices possibilities and gives a response
            switch (choice)
            {
                //add weight
                case 1:
                    trackerManager.addWeight();
                    continue;

                case 2: //the view WeightEntry
                   trackerManager.viewWeight();
                   continue;

                case 3: //the add workout function where user inputs on instruction
                    trackerManager.addWorkout();
                    continue;

                case 4: //the view workout List
                    trackerManager.viewWorkout();
                    continue;

                case 5: //delete weight Entry
                   trackerManager.deleteWeight();
                   continue;

                case 6: //delete workout entry
                   trackerManager.deleteWorkout();
                   continue;

                case 7:
                    trackerManager.viewStats();
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


    public static int getMenuChoice(Scanner UserInput)
    {
        while(true)
        {
            System.out.println("Enter your choice: ");
            if(UserInput.hasNextInt())
            {
                return UserInput.nextInt();
            }
            else
            {
                System.out.println("Invalid input. please enter a whole number");
                UserInput.next();
            }
        }
    }



}
