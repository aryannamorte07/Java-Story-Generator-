import java.util.ArrayList;
import java.util.Scanner;

public class Story {

  private ArrayList<String> bookTitles;
  private ArrayList<String> characters;
  private ArrayList<String> setting;
  private ArrayList<String> synopsis;
  private ArrayList<String> genres;

  private ArrayList<String> fillerWords;
  private ArrayList<String> randomWords;

  public Story(String bookTitles, String characters, String setting, String synopsis,
               String genres, String fillerWords, String randomWords) {

    this.bookTitles = FileReader.toStringList(bookTitles);
    this.characters = FileReader.toStringList(characters);
    this.setting = FileReader.toStringList(setting);
    this.synopsis = FileReader.toStringList(synopsis);
    this.genres = FileReader.toStringList(genres);
    this.fillerWords = FileReader.toStringList(fillerWords);
    this.randomWords = FileReader.toStringList(randomWords);
  }

  public void createStory() {

    Scanner input = new Scanner(System.in);

    String userInput = "";
    String genre = "";
    String chosenSynopsis = "";
    String book = "";
    int index = 0;

    TextProcessor processor = new TextProcessor("data/books.txt");

    System.out.println("Hello! I am CeCe, your specialized AI story generator.");
    System.out.println("What kind of story would you like to read today?");

    userInput = input.nextLine().toLowerCase();

    ArrayList<String> matchingGenres = findGenres(userInput);

    if (matchingGenres.size() > 0) {
      index = (int)(Math.random() * matchingGenres.size());
      genre = matchingGenres.get(index);
    }

    chosenSynopsis = chooseStory(userInput);

    System.out.println("How are you feeling today? Please describe your mood.");

    userInput = input.nextLine().toLowerCase();

    book = processor.getSentimentValue(getSentiment(userInput));

    System.out.println();
    System.out.println("Here is your recommended story.");

    System.out.println(
        "Once upon a time in " +
        chooseSetting() +
        ", " +
        chooseCharacter() +
        " " +
        chosenSynopsis +
        ". The book is called " +
        book +
        "."
    );
  }

  public ArrayList<String> findGenres(String userInput) {

    ArrayList<String> newGenres = new ArrayList<String>();

    for(int i = 0; i < genres.size(); i++) {

      if(userInput.contains(genres.get(i).substring(0, TextProcessor.getSemicolon(genres.get(i))))) {

        newGenres.add(genres.get(i));

      }
    }

    return newGenres;
  }

  public String chooseStory(String userInput){

    ArrayList<String> words = findGenres(userInput);

    ArrayList<String> possibleScenarios = new ArrayList<String>();

    for(int i = 0; i < words.size(); i++){

      String[] phrase = words.get(i).split(",");

      for(int j = 0; j < synopsis.size(); j++){

        for(int k = 0; k < phrase.length; k++){

          if(synopsis.get(j).contains(phrase[k])){

            possibleScenarios.add(synopsis.get(j));

          }

        }

      }

    }

    if(possibleScenarios.size() == 0){
      return "goes on a mysterious adventure";
    }

    int index = (int)(Math.random() * possibleScenarios.size());

    return possibleScenarios.get(index);
  }

  public double getSentiment(String userResponse) {

    double totalSentiment = 0.0;

    userResponse = removeFillerWords(userResponse).toLowerCase();

    for(int i = 0; i < randomWords.size(); i++){

      int index = TextProcessor.getComma(randomWords.get(i)) + 1;

      double value = Double.parseDouble(randomWords.get(i).substring(index));

      String word = randomWords.get(i).substring(0, index - 1);

      if(userResponse.contains(word)){

        totalSentiment += value;

      }

    }

    return totalSentiment;
  }

  public String removeFillerWords(String userInput){

    for(int i = 0; i < fillerWords.size(); i++){

      userInput = userInput.replace(fillerWords.get(i), "");

    }

    return userInput;
  }

  public String chooseCharacter(){

    int number = (int)(Math.random() * characters.size());

    return characters.get(number);
  }

  public String chooseSetting(){

    int number = (int)(Math.random() * setting.size());

    return setting.get(number);
  }
}
