package WebDevelopment.First.search;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class InvertedIndex {
    public static void main(String[] args) throws IOException {
        new InvertedIndex().work();
    }

    private void work() throws IOException {

        Map<File,List<String>> result = new DirParser().parseDirectory("D:\\webdev\\text");
        System.out.println(wordSet(result));
    }

    private Map<String, Map<File, Integer>> index(Map<File,List<String>> filesToWords){
        Map<String, Map<File, Integer>> wordToDocumentMap = new HashMap<>();
        for (String word : wordSet(filesToWords)){
            Map<File, Integer> documentToCountMap = getOrCreate(wordToDocumentMap, word);
            for(File currentDocument : documentToCountMap.keySet()){
                Integer currentCount = documentToCountMap.get(currentDocument);
                if(currentCount == null) {
                    // This word has not been found in this document before, so
                    // set the initial count to zero.
                    currentCount = 0;
                }
                documentToCountMap.put(currentDocument, currentCount + 1);
            }
        }
        return wordToDocumentMap;
    }

    private Map<File, Integer> getOrCreate(Map<String, Map<File, Integer>> wordToDocumentMap, String word) {
        Map<File, Integer> documentToCountMap = wordToDocumentMap.get(word);
        if (documentToCountMap != null) {
            return documentToCountMap;
        }
        documentToCountMap = new TreeMap<>();
        wordToDocumentMap.put(word, documentToCountMap);
        return documentToCountMap;
    }

    private Set<String> wordSet(Map<File,List<String>> filesToWords){
        Set<String> result = new HashSet<>();
        for(File key : filesToWords.keySet()){
            result.addAll(filesToWords.get(key).stream()
                    .map(String::toLowerCase) //ignore case
                    .collect(Collectors.toList()));
        }

        return result;
    }


}
