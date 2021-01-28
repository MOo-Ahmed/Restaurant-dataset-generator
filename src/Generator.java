import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Generator {
    public Generator() {
    }

    public static void generateBasketAnalysis() throws IOException {
        String outputFile = "restaurants_sales-1.txt" , outputFile2 = "restaurants_sales-2.txt" ;
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        BufferedWriter output2 = new BufferedWriter(new FileWriter(outputFile2));
        for (int i = 0; i < 2000000; i++) {
            String itemset = getListOfItems();
            String line = (i+1) + "," + itemset  + "\n";
            if(i >= 1000000)    output2.write(line);
            else    output.write(line);
        }
        output.close();
        output2.close();
    }

    private static String getListOfItems(){
        String res = "" ;
        int Max = 13, Min = 2 ;
        int numberOfBoughtItems = Min + (int)(Math.random() * ((Max - Min) + 1));
        int numOfItems = 697 ;
        Max = numOfItems ;
        Min = 1 ;
        ArrayList<Integer> IDs = new ArrayList<>();
        int i = 0 ;
        while(i < numberOfBoughtItems){
            int rand = Min + (int)(Math.random() * ((Max - Min) + 1));
            if(IDs.indexOf(rand) == -1){
                IDs.add(rand);
                res += rand ;
                i++ ;
                if(i != numberOfBoughtItems){
                    res += "|" ;
                }
            }
        }
        return res ;
    }

    public static void generateRestaurantDataWithCategories()
            throws IOException {

        String inputFile = "restaurants names.txt" , outputFile = "restaurants_data.txt" ;
        String allCategories[] = {"vegan","chinese","thai","gulf","indian", 
            "oriental","fast food", "syrian", "moroccan","italian","mexican","british"};
        int Max = allCategories.length;
        RandomAccessFile input = new RandomAccessFile(inputFile, "r");
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        input.seek(0);
        for (int i = 0; i < 15000; i++) {
            String line = input.readLine();
            int numberOfCategories = 2 + (int)(Math.random() * ((5 - 0) + 1));
            String combinedCategories = "" ;
            ArrayList<String> choices = new ArrayList<>();
            int j = 0 ;
            while(j < numberOfCategories){
                int idx =  0 + (int)(Math.random() * ((Max - 1 - 0) + 1));
                if(choices.indexOf(allCategories[idx]) == -1){
                    choices.add(allCategories[idx]);
                    j++ ;
                    combinedCategories += allCategories[idx] ;
                    if(j < numberOfCategories) combinedCategories += "|" ;
                }

            }
            String location = generateRestaurantCoordinates();
            output.write((i+1) + "," + line + "," + combinedCategories + "," + location + "\n");
        }
        input.close();
        output.close();
    }

    public static String generateRestaurantCoordinates()
            throws IOException {

        int random1 = 28 + (int)(Math.random() * ((31 - 28) + 1)),
            random2 = 28 + (int)(Math.random() * ((31 - 28) + 1));;
        double fraction1 = random1 + Math.sqrt(Math.random()),
            fraction2 = random2 + Math.sqrt(Math.random());
        
        return fraction1 + "," + fraction2 ;
    }

    public static void generateUserPreferences()
            throws IOException {

        String outputFile = "user_preferences.txt" ;
        String allPreferences[] = {"noodles","sea food","sweets","soup",
        "salad","burgers","hot dogs","ice cream","pizza","chicken","beverages","barbecue","country food","waffles","pancakes"};
        int Max = allPreferences.length;
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        for (int i = 0; i < 20000; i++) {
            int numberOfPreferences = 4 + (int)(Math.random() * ((9 - 4) + 1));
            String combinedPreferences = "" ;
            ArrayList<String> choices = new ArrayList<>();
            int j = 0 ;
            while(j < numberOfPreferences){
                int idx =  0 + (int)(Math.random() * ((Max - 1 - 0) + 1));
                if(choices.indexOf(allPreferences[idx]) == -1){
                    choices.add(allPreferences[idx]);
                    j++ ;
                    combinedPreferences += allPreferences[idx] ;
                    if(j < numberOfPreferences) combinedPreferences += "|" ;
                }
            }
            String location = generateRandomCities();
            String gender = generateGender();
            int age = generateUserAge(); 
            output.write((i+1) + "," + location + "," + age + "," + gender + "," + combinedPreferences  + "\n");
        }
        output.close();
    }

    private static String generateRandomCities() {
        String cities[] = {"Giza","Ismailia","Monufia","New Valley","Port Said","Qalyubia","Behira","Suez","Alexandria",
        "Red Sea","Sharqia","Sohag","Cairo","Alexandria","Aswan","Asyut","Beni Suef","Dakahlia",
        "Damietta","Faiyum","Gharbia","Kafr El Sheikh","Luxor","Matruh","Minya","North Sinai","South Sinai" };
        int idx = 0 + (int)(Math.random() * ((25 - 0) + 1));
        return cities[idx];
    }

    private static int generateUserAge(){
        int age = 18 + (int)(Math.random() * ((60 - 18) + 1));
        return age;
    }

    private static String generateGender(){
        String genders[] = {"F", "M"};
        int idx = 0 + (int)(Math.random() * ((1 - 0) + 1));
        return genders[idx];
    }

}
