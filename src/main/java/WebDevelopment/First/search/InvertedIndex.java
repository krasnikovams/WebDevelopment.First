package WebDevelopment.First.search;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class InvertedIndex {
    public static void main(String[] args) throws IOException {
        new InvertedIndex().work();
    }

    private void work() throws IOException {

        Map<String,List<String>> result = new DirParser().parseDirectory("D:\\webdev\\text");
        System.out.println(wordSet(result));
    }

    private Map<String, Map<String, Integer>> index(Map<String,List<String>> data){
        Map<String, Map<String, Integer>> wordToDocumentMap = new HashMap<>();
        return wordToDocumentMap;
    }

    private Set<String> wordSet(Map<String,List<String>> data){
        Set<String> result = new HashSet<>();
        for(String key : data.keySet()){
            result.addAll(data.get(key).stream().map(String::toLowerCase).collect(Collectors.toList()));
        }

        return result;
    }


}
