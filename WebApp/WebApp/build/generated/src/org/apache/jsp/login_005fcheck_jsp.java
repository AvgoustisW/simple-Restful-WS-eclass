package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.StringTokenizer;
import java.sql.*;
import java.net.*;
import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public final class login_005fcheck_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        \n");
      out.write("        <title>Login check page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            
            final String username = request.getParameter("username");
            final String password = request.getParameter("password");


            String recv;
            String recvbuff = "";
            URL jsonpage = new URL("http://localhost:8080/Nikites/webresources/LoginS?username="+username+"&password="+password+"&from=web");
            URLConnection urlcon = jsonpage.openConnection();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream(), "UTF8"));

            while ((recv = buffread.readLine()) != null)
                recvbuff += recv;
            buffread.close();
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject)parser.parse(recvbuff);
            
       
            if (false){
                switch (json.get("Error").toString()) {
                    case "1": 
                        
      out.write("<h2>Wrong username or password.<br></h2>");

                        break;
                    case "5":
                        
      out.write("<h2>Γραμματεία ή admin πρέπει να συνδεθούν από desktop app.<br></h2>");

                        break;
                    case "100":    
                        
      out.write("<h2>Unexpected error.<br></h2>");

                        break;
                    default:
                        
      out.write("<h2>Unexpected error.<br></h2>");
        
                }
                
      out.write("<h2>Press <a href=\"login.html\">here</a> to try again...</h2>");

        
            }
            else {
                session.setAttribute("AM", json.get("AM"));
                session.setAttribute("FirstName", json.get("FirstName"));
                session.setAttribute("LastName", json.get("LastName"));
        
      out.write("\n");
      out.write("                <h2>Welcome ");
      out.print( json.get("FirstName"));
      out.write(' ');
      out.print( json.get("LastName"));
      out.write(" <br> \n");
      out.write("                You may proceed to the home page <a href=\"home.jsp\">here.</a></h2>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
