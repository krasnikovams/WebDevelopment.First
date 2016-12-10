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

    private Map<String, List<File>> index(Map<File,List<String>> filesToWords){
        Map<String, List<File>> wordToDocumentMap = new HashMap<>();
        for (String word : wordSet(filesToWords)){
            List<File> filesWithWord = getOrCreate(wordToDocumentMap, word);
            for(File currentDocument : filesToWords.keySet()){
                if (filesToWords.get(currentDocument).contains(word)){
                    filesWithWord.add(currentDocument);
                }
            }
        }
        return wordToDocumentMap;
    }

    private List<File> getOrCreate(Map<String, List<File>> wordToDocumentMap, String word) {
        List<File> filesWithWord = wordToDocumentMap.get(word);
        if (filesWithWord != null) {
            return filesWithWord;
        }
        filesWithWord = new ArrayList<>();
        wordToDocumentMap.put(word, filesWithWord);
        return filesWithWord;
    }

    private Set<String> wordSet(Map<File,List<String>> filesToWords){
        Set<String> result = new HashSet<>();
        for(File key : filesToWords.keySet()){
            result.addAll(filesToWords.get(key).stream()
                    .collect(Collectors.toList()));
        }

        return result;
    }


}
