package cn.com.demo.javaweb.snacks.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.javaweb.snacks.service.IFoodService;
import cn.com.demo.javaweb.snacks.service.ServiceFactory;
import cn.com.demo.javaweb.snacks.vo.FoodVO;

/**
 * Servlet implementation class SearchSnacksServlet
 */
@WebServlet("/searchSnacksServlet")
public class SearchSnacksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 显示当前页码对应的食品LI
	 * 1 接收当前要显示的页码
	 * 2 接收查询条件
	 * 3 调用后面的Service，获得当前页面要显示的所有食品的List
	 * 3.5 将List设置在request中
	 * 4 转向到snacksList.jsp显示li
	 * 
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 * 1 接收当前要显示的页码
		String currPageNoStr = request.getParameter("pageNo");
		int pageNo = -1;
		try {
			pageNo = Integer.parseInt(currPageNoStr);
		}catch(Exception e) {}
//		 * 2 接收查询条件
		String keyword = request.getParameter("keyword");
//		 * 3 调用后面的Service，获得当前页面要显示的所有食品的List
		IFoodService foodService = ServiceFactory.buildFactory().createFoodService();
		List<FoodVO> foodList = foodService.searchFoods(pageNo, keyword);
//		 * 3.5 将List设置在request中
		request.setAttribute("foodList", foodList);
//		 * 4 转向到snacksList.jsp显示li
		request.getRequestDispatcher("/WEB-INF/jsp/snacksList.jsp").forward(request, response);
	}

}
