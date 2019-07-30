package cn.com.demo.javaweb.snacks.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.javaweb.snacks.service.IFoodService;
import cn.com.demo.javaweb.snacks.service.ServiceFactory;
import cn.com.demo.javaweb.snacks.vo.FoodVO;

/**
 * Servlet implementation class SnacksDetailServlet
 */
@WebServlet("/snacksDetailServlet")
public class SnacksDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 1 获取零食得id
	 * 2 调用service获取FoodVO（包含所有详细信息）
	 * 3 设置到request中
	 * 4 转向snacksDetail.jsp
	 * 
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fdIdStr = request.getParameter("id");
		int fdId = -1;
		try {
			fdId = Integer.parseInt(fdIdStr);
		}catch(Exception e) {}
		IFoodService foodService = ServiceFactory.buildFactory().createFoodService();
		FoodVO food = foodService.searchFoodById(fdId);
		request.setAttribute("food", food);
		request.getRequestDispatcher("/WEB-INF/jsp/snacksDetail.jsp").forward(request, response);
	}

}
