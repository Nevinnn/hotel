package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;
import cn.itcast.service.IFoodService;
import cn.itcast.service.IFoodTypeService;
import cn.itcast.utils.WebUtils;

public abstract class BaseServlet extends HttpServlet{
	
	//�Ы�service
	protected IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);		
	
	protected IDinnerTableService dinnerTableService = BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	
	protected IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//(�O�s����귽)��k��^��
		Object returnValue = null;
		
		String methodName = request.getParameter("method");
		
		try {
			//1.�����e���r�`�X
			Class clazz = this.getClass();
			//2.�����e���檺��k��method����
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			//3.�����k
			returnValue = method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "/error/error.jsp";
		}
		//����
		WebUtils.goTo(request, response, returnValue);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
