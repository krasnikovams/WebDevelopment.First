package WebDevelopment.First.search;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DirParser {

    private static final Logger LOG = Logger.getLogger(DirParser.class.getName());

    Map<File,List<String>> parseDirectory(final String dirName) throws IOException {
        Map<File,List<String>> result = new HashMap<>();
        if (dirName == null){
            LOG.log(Level.INFO, "dirName is null");
            return result;
        }

        File folder = new File(dirName);
        File[] listOfFiles = folder.listFiles((dir, name)
                -> name!=null && new File(dir,name).isFile() && name.endsWith(".txt"));

        LOG.log(Level.INFO, "indexing files: [" + Arrays.toString(listOfFiles) + "]");

        for (File file: listOfFiles) {
            result.put(file,parseFile(file));
        }

        return result;
    }

    private List<String> parseFile(File file) throws IOException {
        LOG.log(Level.FINE, "parsing file: " + file);
        List<String> result = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while( (line = br.readLine())!= null ){
            result.addAll(parse(line));
        }
        return result;
    }

    List<String> parse(String input){
        List<String> result = new ArrayList<>();
        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(input);

        while ( m.find() ) {
            result.add(input.substring(m.start(), m.end()).toLowerCase());
        }

        return result;
    }
}
