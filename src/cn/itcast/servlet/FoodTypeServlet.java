package cn.itcast.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.FoodType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IFoodTypeService;
import cn.itcast.utils.WebUtils;

public class FoodTypeServlet extends BaseServlet {

	/*
		private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);		
		
		private Object uri;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String method = request.getParameter("method");
		
		if ("addFoodType".equals(method)) {
			
			addFoodType(request, response);
		}

		else if ("list".equals(method)) {
			
			list(request, response);
		}
		
		else if ("viewUpdate".equals(method)) {
		
			viewUpdate(request, response);
		}
		
		else if ("delete".equals(method)) {
		
			delete(request, response);
		}
		
		else if ("update".equals(method)) {
		
			update(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	this.doGet(request, response);
	}
	
	*/

	
	public Object addFoodType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;
	
		String foodTypeName = request.getParameter("foodTypeName");
		FoodType ft = new FoodType();
		ft.setTypeName(foodTypeName);
		
	
		foodTypeService.save(ft);
		
		
		uri = request.getRequestDispatcher("/foodType?method=list");

		
		return uri;
		
	}
	


	
	public Object list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;
		
			List<FoodType> list = foodTypeService.getAll();
		
			request.setAttribute("listFoodType", list);
			
			uri = request.getRequestDispatcher("/sys/type/foodtype_list.jsp");
			return uri;
	}


	public Object viewUpdate(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Object uri = null;
	
			String id = request.getParameter("id");
		
			FoodType ft = foodTypeService.findById(Integer.parseInt(id));
	
			request.setAttribute("foodType", ft);
	
			uri = request.getRequestDispatcher("/sys/type/foodtype_update.jsp");
			return uri;
	}
	

	public Object delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Object uri = null;
		
			String id = request.getParameter("id");
			
			foodTypeService.delete(Integer.parseInt(id));
		
			uri = "/foodType?method=list";
			return uri;
	}
	

	public Object update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Object uri = null;

			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("foodTypeName");
			FoodType foodType = new FoodType();
			foodType.setId(id);
			foodType.setTypeName(name);
			
		
			foodTypeService.update(foodType);

			uri = "/foodType?method=list";
			return uri;
	}


	

	

}
