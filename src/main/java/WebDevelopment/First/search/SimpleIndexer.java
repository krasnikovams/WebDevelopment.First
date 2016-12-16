package WebDevelopment.First.search;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.*;

public class SimpleIndexer {
    private static final Logger LOG = Logger.getLogger(SimpleIndexer.class.getName());

    private static final SimpleIndexer INSTANCE = new SimpleIndexer();
    private final InvertedIndex index;


    private SimpleIndexer() {
        String envVarName = "MY_SEARCH_DIRECTORY";
        LOG.log(Level.INFO, "using environment variable: [" + envVarName + "]");
        String directory = System.getenv(envVarName);
        index = make(directory);
        LOG.log(Level.INFO, "indexed files in directory: [" + directory + "]");
    }


    public static SimpleIndexer getInstance() {
        return INSTANCE;
    }


    public List<File> search(String input) {
        return index.findFiles(parse(input));
    }

    private InvertedIndex make(String dirName){
        LOG.log(Level.INFO, "make index of [" + dirName + "]");
        try{
            return new InvertedIndex(dirName);
        }catch (IOException ioe){
            LOG.log(Level.WARNING, "Exception indexing dir: [" + dirName + "]", ioe);
            return null;
        }
    }

    private List<String> parse(String input){
        List<String> result = new DirParser().parse(input);
        LOG.log(Level.FINE, "input [" + input + "] parsed to " + result);
        return result;
    }
}
