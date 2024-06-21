

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setContentType("text/html:charset=UTP-8");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println(email);
		
		
		try
		{
			 Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pandey","root","root");
	            PreparedStatement p = con.prepareStatement("Select *from subham where email=?");
	            p.setString(1, email);
	            ResultSet rs = p.executeQuery();
	            if(rs.next()) {
	            	response.setContentType("text/html");
	                 out = response.getWriter();
	                out.println("<h3>Email already exists!</h3>");
	                out.close();
	            } else {
	PreparedStatement ps=con.prepareStatement("insert into subham values(?,?,?)");
	ps.setString(1,name);
	ps.setString(2,email);
	ps.setString(3, password);
	int i=ps.executeUpdate();
	System.out.println(i);
	if(i>0)
	{
		out.println("You are Registered");
		response.sendRedirect("home.html");
	}
	            }
	
	
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}

}
