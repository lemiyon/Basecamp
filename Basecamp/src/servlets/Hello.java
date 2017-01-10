package servlets;

import java.io.IOException;
import javax.servlet.*;

public class Hello implements Servlet{
	ServletConfig config;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출");
		this.config = config;
	}
	
	public void destroy() {
		System.out.println("destroy() called.");
	}
	
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("service() has called");
	}
	
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig() has called");
		return this.config;
	}
	
	public String getServletInfo(){
		System.out.println("getServletInfo() has called");
		return "version=1.0;author=bola.kim;copyright=bola.kim";
	}
	}
