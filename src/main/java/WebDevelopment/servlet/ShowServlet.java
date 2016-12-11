package WebDevelopment.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.*;

public class ShowServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ShowServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        String fileNameEncoded = req.getParameter("file");

        PrintWriter writer = res.getWriter();
        writer.println("<html>");
        write(fileNameEncoded,writer);
        writer.println("</html>");
        writer.close();
    }

    private void write(String fileNameEncoded,PrintWriter writer){
        String fileName = decode(fileNameEncoded);
        LOG.log(Level.INFO, "showing file: [" + fileName + "]");
        if (fileName == null){
            LOG.log(Level.WARNING, "File not found: [" + fileNameEncoded + "]");
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
            LOG.log(Level.WARNING, "Exception writing file: [" + file + "]", ioe);
            writer.println("<hr>");
            writer.println(ioe.getMessage());
            writer.println("</hr>");
        }

        writer.println("</pre></body>");
    }

    private String decode(String encoded){
        LOG.log(Level.FINE, "decoding file name: [" + encoded + "]");
        try{
            return java.net.URLDecoder.decode(encoded,"UTF-8");
        }catch (UnsupportedEncodingException uee){
            LOG.log(Level.WARNING, "Exception decoding file name: [" + encoded + "]", uee);
            return null;
        }
    }
}