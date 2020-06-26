package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.Food;
import cn.itcast.entity.FoodType;
import cn.itcast.utils.Condition;
import cn.itcast.utils.PageBean;

public class FoodServlet extends BaseServlet {

	/**
	 * 1.進入主頁,顯示菜品以及菜系
	 */
	
	public Object foodDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//1.1 獲取餐桌ID 根據ID查詢 再把查詢的結果保存到session (生成訂單用)
		//只需要執行一次即可:先從session獲取看有沒有餐桌對象:如果沒有 就執行根據主鍵查詢
		//如果session中已經有餐桌對象 就不執行主鍵查詢
		Object obj = session.getAttribute("dinnerTable");
		//判斷
		if(obj == null){
			//只在第一次執行的時候查詢餐桌對象
			String tableId = request.getParameter("tableId");
			DinnerTable dt = dinnerTableService.findById(Integer.parseInt(tableId));
			//保存到session
			request.getSession().setAttribute("dinnerTable", dt);
		}

		
		//1.2 查詢所有"菜品訊息" 保存
		PageBean<Food> pb = new PageBean<Food>();
			//分頁參數 : 獲取當前頁參數
		String curPage = request.getParameter("currentPage");
		//判斷
		if(curPage == null || "".equals(curPage.trim())){
			//第一次訪問 設置當前頁為1
			pb.setCurrentPage(1);
		}else{
			//設置當前頁
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		
		//條件對像
		Condition condition = new Condition();
		//分頁參數 :菜id
		String foodTypeId = request.getParameter("foodTypeId");
		
		if(foodTypeId != null){//如果類別為null 不作為條件那就查詢全部
			
			condition.setFoodTypeId(Integer.parseInt(foodTypeId));
		}
		
		//分頁參數 :菜名稱
		String foodName = request.getParameter("foodName");
		condition.setFoodName(foodName);
		
		//設置條件對象到pb中
		pb.setCondition(condition);
		
		//----------->分頁查詢
		foodService.getAll(pb);
		//保存查詢後的pb對象
		request.setAttribute("pb", pb);
		
		
		//1.3 查詢所有"菜系訊息" 保存
		List<FoodType> listFoodType = foodTypeService.getAll();
		request.setAttribute("listFoodType", listFoodType);
		
		//1.4  跳轉
		return request.getRequestDispatcher("/app/caidan.jsp");
	}

}
