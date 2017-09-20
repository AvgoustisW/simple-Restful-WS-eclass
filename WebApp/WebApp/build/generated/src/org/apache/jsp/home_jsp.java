package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.net.*;
import java.io.*;
import javax.swing.JButton;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!-- ============================================== -->\n");
      out.write("<!--  TEI Athinas - Tmima Mixanikwn Pliroforikis    -->\n");
      out.write("<!--                                                -->\n");
      out.write("<!--          Teliki Ergasia sto mathima            -->\n");
      out.write("<!--           Diktyakoy Programmatismou            -->\n");
      out.write("<!--                                                -->\n");
      out.write("<!--                                                -->\n");
      out.write("<!--      Avgoustis Aristeidis, 101049              -->\n");
      out.write("<!--      Christou Konstantinos, 101040             -->\n");
      out.write("<!-- ============================================== -->\n");
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
      out.write("        <title>Home page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h2>Welcome to your home page ");
      out.print( session.getAttribute("FirstName"));
      out.write(' ');
      out.print( session.getAttribute("LastName"));
      out.write(".</h2>   \n");
      out.write("        \n");
      out.write("        <!-- Option menu -->\n");
      out.write("        <form name=\"Input\" method=\"post\" action=\"show_degrees.jsp\">\n");
      out.write("            <input type=\"submit\" value=\"Show my degrees\">\n");
      out.write("        </form>\n");
      out.write("        \n");
      out.write("        <br><a href=\"login.html\">Log out</a>\n");
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
