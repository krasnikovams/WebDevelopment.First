package WebDevelopment.First.search;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;


class InvertedIndex {
    private static final Logger LOG = Logger.getLogger(InvertedIndex.class.getName());

    private final Map<String, List<File>> index;

    InvertedIndex(String dirName)throws IOException {
        LOG.log(Level.INFO, "indexing directory: [" + dirName + "]");
        index = index(new DirParser().parseDirectory(dirName));
        LOG.log(Level.INFO, "completed indexing directory: [" + dirName + "], total words found (ignore case) "
                + (index == null ? "null" : index.size()));
    }

    public static void main(String[] args) throws IOException {
        //new InvertedIndex("D:\\webdev\\text").work();
        LOG.log(Level.INFO, SimpleIndexer.getInstance().search("brown dog").toString());
    }

    List<File> find(List<String> words){
        LOG.log(Level.INFO, "search words: " + words);
        if (words == null || words.size() == 0){
            return new ArrayList<>(); //empty
        }
        return findWithFirst(words.get(0), words.subList(1, words.size()));
    }

    private List<File> findWithFirst(String first, List<String> words){
        List<File> result = find(first);
        for (String word : words){
            List<File> found = find(word);
            if (result.size()==0 || found == null || found.size()== 0){
                return new ArrayList<>();
            }
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
