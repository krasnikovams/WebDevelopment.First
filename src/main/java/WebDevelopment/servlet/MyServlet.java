package WebDevelopment.servlet;

        import java.io.BufferedReader;
        import java.io.IOException;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        import org.json.JSONObject;

public class MyServlet extends HttpServlet {

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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"hello from java. you entered\" : \"" + words + "\"}");

    }

}
