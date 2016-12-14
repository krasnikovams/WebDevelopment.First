package WebDevelopment.servlet;

import WebDevelopment.First.search.SimpleIndexer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchJsServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SearchServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        String words = req.getParameter("words");
        String found = results(words, SimpleIndexer.getInstance().search(words));

        PrintWriter writer = res.getWriter();
        print(found, writer);
        writer.close();
    }

    private void print(String found, PrintWriter writer) {
        writer.println("{");
        writer.println(found);
        writer.println("}");
    }

    private String results(String words, List<File> results){
        LOG.log(Level.INFO, "Search for words: [" + words + "], result: " + results);
        if (results == null || results.size() == 0){
            LOG.log(Level.INFO, "Nothing found for words: [" + words + "]");
            return "\"found\":\"0\"";
        }
        return "\"found\":\"" + results.size() + "\"";
    }
}
