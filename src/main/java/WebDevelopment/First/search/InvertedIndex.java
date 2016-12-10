package WebDevelopment.First.search;

import java.io.*;
import java.util.*;


public class InvertedIndex {
    public static void main(String[] args) throws IOException {
        new InvertedIndex().work();
    }

    private void work() throws IOException {

        Map<String,List<String>> result = new DirParser().parseDirectory("D:\\webdev\\text");
        System.out.println(result);
    }


}
