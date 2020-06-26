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
	 * 1.�i�J�D��,��ܵ�~�H�ε�t
	 */
	
	public Object foodDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//1.1 ����\��ID �ھ�ID�d�� �A��d�ߪ����G�O�s��session (�ͦ��q���)
		//�u�ݭn����@���Y�i:���qsession����ݦ��S���\���H:�p�G�S�� �N����ھڥD��d��
		//�p�Gsession���w�g���\���H �N������D��d��
		Object obj = session.getAttribute("dinnerTable");
		//�P�_
		if(obj == null){
			//�u�b�Ĥ@�����檺�ɭԬd���\���H
			String tableId = request.getParameter("tableId");
			DinnerTable dt = dinnerTableService.findById(Integer.parseInt(tableId));
			//�O�s��session
			request.getSession().setAttribute("dinnerTable", dt);
		}

		
		//1.2 �d�ߩҦ�"��~�T��" �O�s
		PageBean<Food> pb = new PageBean<Food>();
			//�����Ѽ� : �����e���Ѽ�
		String curPage = request.getParameter("currentPage");
		//�P�_
		if(curPage == null || "".equals(curPage.trim())){
			//�Ĥ@���X�� �]�m��e����1
			pb.setCurrentPage(1);
		}else{
			//�]�m��e��
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		
		//����ﹳ
		Condition condition = new Condition();
		//�����Ѽ� :��id
		String foodTypeId = request.getParameter("foodTypeId");
		
		if(foodTypeId != null){//�p�G���O��null ���@�����󨺴N�d�ߥ���
			
			condition.setFoodTypeId(Integer.parseInt(foodTypeId));
		}
		
		//�����Ѽ� :��W��
		String foodName = request.getParameter("foodName");
		condition.setFoodName(foodName);
		
		//�]�m�����H��pb��
		pb.setCondition(condition);
		
		//----------->�����d��
		foodService.getAll(pb);
		//�O�s�d�᪺߫pb��H
		request.setAttribute("pb", pb);
		
		
		//1.3 �d�ߩҦ�"��t�T��" �O�s
		List<FoodType> listFoodType = foodTypeService.getAll();
		request.setAttribute("listFoodType", listFoodType);
		
		//1.4  ����
		return request.getRequestDispatcher("/app/caidan.jsp");
	}

}
