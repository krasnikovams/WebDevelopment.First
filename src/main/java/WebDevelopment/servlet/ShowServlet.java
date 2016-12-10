package WebDevelopment.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        String fileName = req.getParameter("file");

        PrintWriter writer = res.getWriter();
        writer.println("<html>");
        write(fileName,writer);
        writer.println("</html>");
        writer.close();
    }

    private void write(String fileName,PrintWriter writer){
        if (fileName == null){
            writer.println("<head><title>Error</title></head>"
                    + "<body><font color=\"red\"><b>FILE NOT FOUND</b></font></body>");
            return;
        }
        File file = new File(fileName);
        writer.println("<head><title>" + file.getName() + "</title></head>");
        writer.println("<body><pre>");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while( (line = br.readLine())!= null ){
                writer.println(line);
            }
        }catch (IOException ioe){
            writer.println("<hr>");
            writer.println(ioe.getMessage());
            writer.println("</hr>");
        }

        writer.println("</pre></body>");
    }
}