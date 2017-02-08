package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.sun.org.apache.bcel.internal.generic.NEW;

import edu.unsw.comp9321.jdbc.*;
/**
 * Servlet implementation class ControlServlet
 */
@WebServlet({"/ControlServlet", "/dispatcher", "/home", "/searchresults", "/entryinfo", "/itemonsaleresult", "/sellerhome","/userlistresult", "/adminsearchresults","/adminbooksearchresults","/adminusersearchresult"})
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(ControlServlet.class.getName());
	private UserDAO users;
	private BookDAO books;
	private Map commands;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() throws Exception{
        super();

    }
    
    public void init(ServletConfig config) throws ServletException {

		super.init(config);

		commands = new HashMap();
		commands.put("login", new LoginCommand());
		commands.put("basicsearch", new BasicSearchCommand());
		commands.put("advancedsearch", new AdvanceSearchCommand());
		commands.put("PAGE_NOT_FOUND", new ErrorCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("entryinfo", new EntryInfoCommand());
		commands.put("cart", new CartCommand());
		commands.put("purchase", new PurchaseCommand());
		commands.put("random", new RandomCommand());
		commands.put("addingentry", new AddingCommand());
		commands.put("sellerhome", new ItemOnSaleCommand());
		commands.put("pause", new PauseCommand());
		commands.put("register", new RegisterCommand());
		commands.put("sellerentryinfo", new SellerItemInfoCommand());
		commands.put("recover", new RecoverCommand());
		commands.put("adminlogin", new AdminLoginCommand());
		commands.put("adminhome", new AdminHomeCommands());
		commands.put("manageuser", new ManageUserCommands());
		commands.put("managebook", new ManageBookCommands());
		commands.put("ban",new BanCommand());
		commands.put("adminuserinfo",new AdminUserInfoCommands());
		commands.put("account", new AccountPageCommand());
		commands.put("goedit", new GoEditCommand());
		commands.put("infoedit", new AccountEditCommand());
		commands.put("adminentryinfo", new AdminEntryInfoCommand());
		commands.put("deleteentry", new DeleteEntryCommand());
		commands.put("adminbooksearch", new AdminBookSearchCommand());
		commands.put("adminusersearch", new AdminUserSearchCommand());

	}
    /** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void resolveCommand(
		HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		System.out.println(request.getParameter("operation"));

		// TODO: find the command that was requested by 
		// the client and then call the execute method
		Command cmd = (Command) commands.get(request.getParameter("operation"));
		if (cmd == null) {
	        cmd = (Command) commands.get("PAGE_NOT_FOUND");
		}
		
		cmd.execute(request, response);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = request.getServletPath();
		System.out.println(url);
		RequestDispatcher rd;
		String username = (String) request.getSession().getAttribute("username");
		String adminname = (String) request.getSession().getAttribute("adminname");

		if(username==null && adminname == null){
			if(request.getParameter("operation") != null && request.getParameter("operation").equals("register")){
				resolveCommand(request,response);
				return;
			}
			rd = request.getRequestDispatcher("/default.jsp");	
			rd.forward(request, response);
			return;
		}else if(url.equals("/home")){
			request.getSession().setAttribute("back", -1);
			Command cmd = (Command) commands.get("random");
			cmd.execute(request, response);
			return;
		}else if(url.equals("/sellerhome")){
			request.getSession().setAttribute("back", -1);
			Command cmd = (Command) commands.get("sellerhome");
			cmd.execute(request, response);
			return;
		}else if(url.equals("/searchresults")){
				if(request.getParameter("index")!=null){
				request.getSession().setAttribute("index", request.getParameter("index"));
				rd = request.getRequestDispatcher("SearchResults.jsp");
				rd.forward(request, response);
				return;
			}
				
		}else if(url.equals("/adminsearchresults")){
			if(request.getParameter("index")!=null){
			request.getSession().setAttribute("index", request.getParameter("index"));
			rd = request.getRequestDispatcher("BookManagement.jsp");
			rd.forward(request, response);
			return;
			}
		}else if(url.equals("/adminbooksearchresults")){
			if(request.getParameter("index")!=null){
				request.getSession().setAttribute("index", request.getParameter("index"));
				rd = request.getRequestDispatcher("AdminBookResults.jsp");
				rd.forward(request, response);
				return;
				}
			
		}else if(url.equals("/itemonsaleresult")){
				if(request.getParameter("index")!=null){
				request.getSession().setAttribute("index", request.getParameter("index"));
				rd = request.getRequestDispatcher("ItemsOnSales.jsp");
				rd.forward(request, response);
				return;
			}
		}else if(url.equals("/userlistresult")){
			if(request.getParameter("index")!=null){
				request.getSession().setAttribute("index", request.getParameter("index"));
				rd = request.getRequestDispatcher("AdminUser.jsp");
				rd.forward(request, response);
				return;
			}
		}else if(url.equalsIgnoreCase("/adminusersearchresult")){
			if(request.getParameter("index")!=null){
				request.getSession().setAttribute("index", request.getParameter("index"));
				rd = request.getRequestDispatcher("AdminUserResults.jsp");
				rd.forward(request, response);
				return;
			}
		}
		resolveCommand(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST");
		request.getSession().setAttribute("back", -1);
		resolveCommand(request, response);
		
		
		
	}

	
	
	
	
	
	
}
