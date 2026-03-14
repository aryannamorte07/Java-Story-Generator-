public class Main {

  public static void main(String[] args) {

    Story story = new Story(
        "data/books.txt",
        "data/names.txt",
        "data/settings.txt",
        "data/synopsis.txt",
        "data/genres.txt",
        "data/fillerWords.txt",
        "data/randomWords.txt"
    );

    story.createStory();

  }

}
