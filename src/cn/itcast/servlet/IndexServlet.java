package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.DinnerTable;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;
import cn.itcast.utils.WebUtils;

public class IndexServlet extends BaseServlet {
	
	/*
	 * Ä~©ÓBaseServlet
	 * 
	 //³Ð«Øservice
	private IDinnerTableService dinnerTableService= BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getParameter("method");
		if(method == null){
			method = "listTable";
		}
		
		if("listTable".equals(method)){
			//1
			listTable(request, response);
		}
	
	}
		public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	*/
	
	//1
	public Object listTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Object uri = null;
		List<DinnerTable> list = dinnerTableService.findNoUseTable();
		request.setAttribute("listDinnerTable", list);
		uri = request.getRequestDispatcher("/app/index.jsp");
		
		return uri;
		
	}



	

}
