package WebDevelopment.servlet;

import WebDevelopment.First.search.SimpleIndexer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        String words = req.getParameter("words");
        String found = results(words, SimpleIndexer.getInstance().search(words));

        PrintWriter writer = res.getWriter();
        writer.println("<html>");
        writer.println("<head><title>Search results</title></head>");
        writer.println("<body>" + found + "</body>");
        writer.println("</html>");
        writer.close();
    }

    private String results(String words, List<File> results){
        String header = "<b>Search result for words: [" + words + "]</b><br><br>";
        if (results == null || results.size() == 0){
            return header + "<font color=\"red\"><b>NOTHING FOUND</b></font>";
        }
        StringBuilder result = new StringBuilder();
        for (File file:results){
            appendFile(result, file);
        }
        return header + result.toString();
    }

    private void appendFile(StringBuilder result, File file) {
        String name = file.getName();
        result.append("<br><form name=\"show")
                .append(name)
                .append("\" method=\"get\" action=\"show\"> ")
                .append(name)
                .append(" <input type=\"hidden\" name=\"file\" value=\"")
                .append(encodeAbsolutePath(file))
                .append("\"/> <br/>")
                .append(" <input type=\"submit\" value=\"Show\" />")
                .append("</form></br>");
    }

    private String encodeAbsolutePath(File file) {
        try{
            return java.net.URLEncoder.encode(file.getAbsolutePath(),"UTF-8");
        }catch (UnsupportedEncodingException uee){
            return null;
        }
    }
}