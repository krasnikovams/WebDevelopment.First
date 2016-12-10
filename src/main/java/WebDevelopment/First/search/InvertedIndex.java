package WebDevelopment.First.search;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


class InvertedIndex {
    private final Map<String, List<File>> index;

    InvertedIndex(String dirName)throws IOException {
        index = index(new DirParser().parseDirectory(dirName));
    }

    public static void main(String[] args) throws IOException {
        //new InvertedIndex("D:\\webdev\\text").work();
        System.out.println(SimpleIndexer.getInstance().search("brown dog"));
    }

    List<File> find(List<String> words){
        System.out.println("search words: " + words);
        if (words == null || words.size() == 0){
            return new ArrayList<>(); //empty
        }
        return findWithFirst(words.get(0), words.subList(1, words.size()));
    }

    private List<File> findWithFirst(String first, List<String> words){
        List<File> result = find(first);
        for (String word : words){
            result.retainAll(find(word));
        }
        return result;
    }

    private List<File> find(String word){
        return index.get(word.toLowerCase());
    }

    private Map<String, List<File>> index(Map<File,List<String>> filesToWords){
        Map<String, List<File>> wordToDocumentMap = new HashMap<>();
        for (String word : wordSet(filesToWords)){
            List<File> filesWithWord = getOrCreate(wordToDocumentMap, word);
            filesWithWord.addAll(filesToWords.keySet().stream()
                    .filter(currentDocument -> filesToWords.get(currentDocument).contains(word))
                    .collect(Collectors.toList()));
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
