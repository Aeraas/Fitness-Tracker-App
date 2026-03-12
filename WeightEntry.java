import java.time.LocalDate;

public class WeightEntry
{
    //assigning variables to the class
    private LocalDate date;
    private double weight;

    //constructor
    public WeightEntry(LocalDate date, double weight)
    {
        this.date = date;
        this.weight = weight;
    }

    //now for the get methods
    public LocalDate getDate()
    {
        return date;
    }

    public double getWeight()
    {
        return weight;
    }

    //now for display
    public void display()
    {
        System.out.println("Date: " + date + " | Weight: " + weight + " kg");
    }
}
