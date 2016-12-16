package WebDevelopment.servlet;

import WebDevelopment.First.search.SimpleIndexer;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SearchJsServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SearchServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jObj = new JSONObject(sb.toString());
        String words = jObj.getString("words");
        List<String> found = results(words, SimpleIndexer.getInstance().search(words));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(found));

    }

    private List<String> results(String words, List<File> results){
        LOG.log(Level.INFO, "Search for words: [" + words + "], result: " + results);
        if (results == null || results.size() == 0){
            LOG.log(Level.INFO, "Nothing found for words: [" + words + "]");
            return Collections.singletonList("NOTHING FOUND");
        }
        return results.stream().map(File::getName).collect(Collectors.toList());
    }
}
