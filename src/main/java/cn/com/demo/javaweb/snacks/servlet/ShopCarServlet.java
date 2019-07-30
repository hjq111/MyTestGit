package cn.com.demo.javaweb.snacks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.demo.javaweb.snacks.shopcar.ShopCar;
import cn.com.demo.javaweb.snacks.util.Constant;

/**
 * Servlet implementation class ShopCarServlet
 */
@WebServlet("/shopCarServlet")
public class ShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取操作
		String operate = request.getParameter("op");
		if(operate == null) {
			operate = "search";
		}
		// 获取fdId
		String fdIdStr = request.getParameter("id");
		int fdId = -1;
		try {
			fdId = Integer.parseInt(fdIdStr);
		} catch (Exception e) {
		}
		// 获取数据
		String countStr = request.getParameter("count");
		int count = -1;
		try {
			count = Integer.parseInt(countStr);
		} catch (Exception e) {
		}

		// 重session中获取购物车对象
		HttpSession session = request.getSession();
		ShopCar shopCar = (ShopCar) session.getAttribute(Constant.SHOP_CAR);
		if (shopCar == null) {
			shopCar = new ShopCar();
			session.setAttribute(Constant.SHOP_CAR, shopCar);
		}

		// 调用对应方法
		// 返回不同结果
		switch (operate) {
		case "add":
			shopCar.addSnacks(fdId, count);
			break;
		case "update":
			shopCar.updateSnacks(fdId, count);
			break;
		case "remove":
			shopCar.removeSnacks(fdId);
			break;
		case "clear":
			shopCar.clear();
			break;
		default:
			request.getRequestDispatcher("/WEB-INF/jsp/shopCar.jsp").forward(request, response);
			return;
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(shopCar.getCount());
		out.flush();
		out.close();
	}

}
