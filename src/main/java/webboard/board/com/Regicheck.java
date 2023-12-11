package webboard.board.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Regicheck")
public class Regicheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Regicheck() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");

		/* 取得した値をセット */
		req.setAttribute("id", req.getParameter("id"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("name", req.getParameter("name"));
		req.setAttribute("age", req.getParameter("age"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.setAttribute("selfinf", req.getParameter("selfinf"));

		/* 登録結果画面へ値を渡す */
		RequestDispatcher rd = req.getRequestDispatcher("/Regiresult");
		rd.forward(req, res);

	}

}
