package Console;

import Data.Color;
import Data.MovieGenre;
import Data.MpaaRating;
import java.util.Arrays;
/**
 * Класс ReaderManager представляет собой менеджер для чтения данных о фильме из консоли
 */

public class ReaderManager {
    ConsoleManager consoleManager = new ConsoleManager();
    public String readName(){
        System.out.println("Enter the name of the movie: ");
        String name = consoleManager.readLine();
        while (true){
            if (name.isEmpty() || name.isBlank()){
                System.out.println("Name can't be null. Try again: ");
                name = consoleManager.readLine();
            }else{
                return name;
            }
        }
    }
    public float readCoordinateX(){
        System.out.println("Enter the coordinate X: ");
        while (true){
            try{
                return consoleManager.readFloat();
            }catch (NumberFormatException e){
                System.out.println("The number was entered wrong. Try again: ");
            }
        }
    }
    public float readCoordinateY(){
        System.out.println("Enter the coordinate Y: ");
        while (true) {
            try {
                float coordinateY = consoleManager.readFloat();
                if (coordinateY <= 347) {
                    return coordinateY;
                } else {
                    System.out.println("Count of oscars must be less or equal 347. Try again: ");
                }
            } catch(NumberFormatException e){
                    System.out.println("The number was entered wrong. Try again: ");
            }

        }

    }
    public int readOscarsCount(){
        System.out.println("Enter count of oscars: ");
        while (true){
            try{
                int oscarsCount = consoleManager.readInt();
                if(oscarsCount > 0){
                    return oscarsCount;
                }else{
                    System.out.println("Count of oscars must be more, than 0. Try again: ");
                }
            }catch (NumberFormatException e){
                System.out.println("Count of oscars was entered in wrong way. Try again: ");
            }
        }
    }
    public int readBudget(){
        System.out.println("Enter budget of the movie: ");
        while (true){
            try{
                int budget = consoleManager.readInt();
                if(budget > 0){
                    return budget;
                }else{
                    System.out.println("Budget must be more, than 0. Try again: ");
                }
            }catch (NumberFormatException e){
                System.out.println("Budget was entered in wrong way. Try again: ");
            }
        }
    }
    public MovieGenre readMovieGenre(){
        System.out.println("Enter one of presented genre: " + Arrays.toString(MovieGenre.values()));
        while (true){
            try{
                return MovieGenre.valueOf(consoleManager.getValidatedValue("\nEnter genre of the movie: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("Genre was entered wrong. Try again: ");
            }
        }
    }
    public MpaaRating readMpaaRating(){
        System.out.println("Enter one of presented mpaaRatings: " + Arrays.toString(MpaaRating.values()));
        while (true){
            try{
                return MpaaRating.valueOf(consoleManager.getValidatedValue("\nEnter mpaaRating of the movie: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("MpaaRating was entered wrong. Try again: ");
            }
        }
    }
    public String readOperatorName(){
        System.out.println("Enter the name of the operator: ");
        String name = consoleManager.readLine();
        while (true){
            if (name.isEmpty() || name.isBlank()){
                System.out.println("Name can't be null. Try again: ");
                name = consoleManager.readLine();
            }else{
                return name;
            }
        }
    }
    public float readWeight(){
        System.out.println("Enter operator's weight: ");
        while (true) {
            try {
                float weight = consoleManager.readFloat();
                if (weight > 0 ) {
                    return weight;
                } else {
                    System.out.println("Weight must be more than 0. Try again: ");
                }
            } catch(NumberFormatException e){
                System.out.println("The weight was entered wrong. Try again: ");
            }

        }

    }
    public Color readEyeColor(){
        System.out.println("Enter one of presented eye colors: " + Arrays.toString(Color.values()));
        while (true){
            try{
                return Color.valueOf(consoleManager.getValidatedValue("\nEnter eye color of the operator: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("Eye color was entered wrong. Try again: ");
            }
        }
    }
}
