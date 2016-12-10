package WebDevelopment.First.search;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DirParser {

    Map<String,List<String>> parseDirectory(final String dirName) throws IOException {
        Map<String,List<String>> result = new HashMap<String, List<String>>();

        File folder = new File(dirName);
        File[] listOfFiles = folder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name!=null && new File(dir,name).isFile() && name.endsWith(".txt");
            }
        });

        for (File file: listOfFiles) {
            result.put(file.getName(),parseFile(file));
        }

        return result;
    }

    private List<String> parseFile(File file) throws IOException {
        List<String> result = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        while( (line = br.readLine())!= null ){
            result.addAll(parse(line));
        }
        return result;
    }

    private List<String> parse(String input){
        List<String> result = new ArrayList<String>();
        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(input);

        while ( m.find() ) {
            result.add(input.substring(m.start(), m.end()));
        }

        return result;
    }
}
