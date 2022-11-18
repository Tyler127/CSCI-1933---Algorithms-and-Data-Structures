package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OwlPopulation {
    private String fileName;
    private Owl[] data;


    public int populateData() throws FileNotFoundException {
        File f = new File(fileName);
        Scanner scanner = new Scanner(f);

        int numLines = 0;
        while(scanner.hasNextLine()){
            numLines++;

            scanner.nextLine();
        }
        scanner.close();

        data = new Owl[numLines];   //data is is allocated the exact amount of space it needs
        scanner = new Scanner(f);
        int counta = 0;

        //Populates the data with owls constructed from the lines of the file given
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            String[] stuff = s.split(",");
            String owlName = stuff[0];
            int age = Integer.parseInt(stuff[1]);
            double weight = Double.parseDouble(stuff[2]);
            Owl toAdd = new Owl(owlName, age, weight);
            data[counta] = toAdd;
            counta ++;
        }
        scanner.close();
        return data.length;
    }

    public OwlPopulation(String fileName) throws FileNotFoundException {
        // Populates the class variables in OwlPopulation
        this.fileName = fileName;
        populateData();
    }

    public double averageAge(){
        double ageTotal = 0;
        for(int i = 0; i < data.length; i++){
            ageTotal += data[i].getAge();
        }
        return ageTotal / data.length;
    }

    public Owl getYoungest(){
        if(data.length == 0){
            return null;
        }
        Owl currYoung = data[0];
        for(int i = 1; i < data.length; i++){
            if(data[i].getAge() < currYoung.getAge()){
                currYoung = data[i];
            }
        }

        return currYoung;
    }

    public Owl getHeaviest(){
        if(data.length == 0){
            return null;
        }
        Owl currChungus = data[0];
        for(int i = 1; i < data.length; i++){
            if(data[i].getWeight() > currChungus.getWeight()){
                currChungus = data[i];
            }
        }

        return currChungus;
    }

    public String toString(){
        String youngestString = "The youngest owl is " + this.getYoungest().getName() + ", which is " + this.getYoungest().getAge() + " years old. \n";
        String heaviestString = "The heaviest owl is " + this.getHeaviest().getName() + ", which weighs " + this.getHeaviest().getWeight() + "pounds. \n";
        String popAvgString = "The average age of the population is " + this.averageAge();

        return youngestString + heaviestString + popAvgString;
    }

    public boolean containsOwl(Owl other){
        // Tests if any element in this.data is equal to any item in other.data
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(other)) {
                return true;
            }
        }
        return false;
    }

    // public void scanDupes(OwlPopulation small, OwlPopulation, big){

    // }
	
    public void merge(OwlPopulation other){

        // Creates a new array to store the owls in
        Owl[] owlStorage = new Owl[this.data.length + other.data.length];

        // Used to count the current working index of owlStorage
        int usedSpaceCounter = 0;

        // Used to count the amount of duplicates found between the two arrays
        int dupes = 0;

        // Searches for dupes between the two arrays being merged and 
        // adds the non-dupes to owlStorage
        for (int i = 0; i < other.data.length; i++){
            if (containsOwl(other.data[i])){
                //System.out.println("Truers in chat " + i);
                dupes += 1;
            } 
            else {
                //System.out.println("falsers at " + i);
                // Subtract dupes from i to prevent nulls being added
                // to owlStorage from skipping indexes when a dupe is found
                owlStorage[i-dupes] = other.data[i];
                usedSpaceCounter += 1;
            }
        }
        //System.out.println("owlStorage length: " + usedSpaceCounter);

        // Renamed to make more sense
        int indexofStorage = usedSpaceCounter;

        // Adds all elements of this.data to owlStorage starting at the length of 
        // the usedSpaceCounter of owlStorage which is the index of the first open spot in the array
        for (int i = 0; i < this.data.length; i++){
            owlStorage[indexofStorage + i] = this.data[i];
            //System.out.println("index " + i + " of this.data added to index " + (indexofStorage + i) + " of owlStorage");
        }

        // Counts the amount of nulls that appear at the end of the owlStorage array
        int nullCounter = 0;
        for(int i = 0; i < owlStorage.length; i++) {
            if(owlStorage[i] == null) {
                nullCounter++;
                //System.out.println("finalOutput null at: " + i);
            }
        }
        //System.out.println("finalOutput nulls: " + nullCounter);

        // Therefore the actual space used in owlStorage that is not nulls is this:
        int finalSpaceUsed = owlStorage.length - nullCounter;

        // Creates a new output array that will be the exact length of used space
        Owl[] finalOutput = new Owl[finalSpaceUsed];

        // Loops through owlStorage only for the length of finalSpacedUsed
        // so that there is no nulls
        for (int i = 0; i < finalSpaceUsed; i++){
            finalOutput[i] = owlStorage[i];
        }
        //System.out.println("finalOutput array length: " + finalOutput.length);

        // Reassigns the input array to the output
        this.data = finalOutput;
    }

    public int popSize(){
        return data.length;
    }
	
    public static void main(String[] args) {
        try {

            //The following should run when you are complete. Feel free to comment out as you see fit while you work.
            OwlPopulation pop1 = new OwlPopulation("owlPopulation1.csv");
            //System.out.println(pop1);
            //System.out.println(pop1.popSize());
            // System.out.println("pop1 avg. age: " + pop1.averageAge());
            // System.out.println("pop1 youngest: " + pop1.getYoungest().getAge());
            // System.out.println("pop1 Heaviest: " + pop1.getHeaviest().getWeight();
            //System.out.println(pop1.toString());

            OwlPopulation pop2 = new OwlPopulation("owlPopulation2.csv");
            //System.out.println(pop2);
            //System.out.println(pop2.popSize());
            // System.out.println("pop2 avg. age: " + pop2.averageAge());
            //System.out.println(pop2.toString());

            //Owl[] ssu = new Owl[2];
            //System.out.println(Arrays.toString(ssu));


            pop1.merge(pop2);
            System.out.println(pop1);
            System.out.println(pop1.popSize());

        }

        catch (FileNotFoundException f){
            System.out.println("File not found.");
        }
    }


}
