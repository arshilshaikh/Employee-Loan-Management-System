

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Loan1
 */
@WebServlet("/Loan1")
public class Loan1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("k2");
		PrintWriter out=response.getWriter();
		
		if(id==null)
		{
		response.sendRedirect("index.jsp");	
		}
		String ltype=request.getParameter("loantype");
		String name=request.getParameter("name");
		String lamt=request.getParameter("loanamt");
		
		int roi=0;
		int minst=0;
		
		if(ltype.equals("Home Loan"))
		{
			roi=8;
			minst=3000;
		}
		else if(ltype.equals("Car Loan"))
		{
			roi=10;
			minst=3500;
		}
		else if(ltype.equals("Education Loan"))
		{
			roi=11;
			minst=2500;
		}
		else if(ltype.equals("Buissness Loan"))
		{
			roi=12;
			minst=4000;
		}
		else if(ltype.equals("Appliance Loan"))
		{
			roi=7;
			minst=2500;
		}
		else if(ltype.equals("Personal Loan"))
		{
			roi=6;
			minst=3000;
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","9893");
			java.sql.PreparedStatement ps=con.prepareStatement("insert into loan values(?,?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, lamt);
			ps.setInt(3, roi);
			ps.setInt(4, minst);
			ps.setString(5, ltype);
			ps.setString(6, name);
			ps.setString(7, "no");
			int x=0;
			x=ps.executeUpdate();
			if(x!=0)
			{
				out.println("successfully updated");
			}
			else
			{
				out.println("not");
			}
		}
		catch (Exception e) {
			out.println(e);
		}
		
	}

}
