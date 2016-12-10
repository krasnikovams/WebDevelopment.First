package WebDevelopment.First.search;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SimpleIndexer {
    private static final SimpleIndexer INSTANCE = new SimpleIndexer();
    private final InvertedIndex index;


    private SimpleIndexer() {
        index = make(System.getenv("MY_SEARCH_DIRECTORY"));
    }


    public static SimpleIndexer getInstance() {
        return INSTANCE;
    }


    public List<File> search(String input) {
        return index.find(parse(input));
    }

    private InvertedIndex make(String dirName){
        System.out.println("make index of [" + dirName + "]");
        try{
            return new InvertedIndex(dirName);
        }catch (IOException ioe){
            return null;
        }
    }

    private List<String> parse(String input){
        return new DirParser().parse(input);
    }
}
