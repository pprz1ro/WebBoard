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
		PrintWriter out = res.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>確認画面</title>");
		/* スタイルを付与 */
		out.println("<style> body { background-color: #9F9F9F;} "
				+ ".boardwall {"
				+ "display: flex;"
				+ "align-items: center;"
				+ " justify-content: center;"
				+ " padding: 20px;"
				+ " background: transparent;"
				+ " border: 2px solid rgba(255, 255, 255, 5);"
				+ " border-radius: 20px;"
				+ " backdrop-filter: blur(20px);"
				+ " box-shadow: 0 0 30px rgba(0, 0, 0, 5);"
				+ " display: flex;"
				+ " flex-direction: column;"
				+ " }"
				+ ".btn {\r\n"
				+ "  display       : inline-block;\r\n"
				+ "  border-radius : 5%;          \r\n"
				+ "  font-size     : 10px;        \r\n"
				+ "  text-align    : center;      \r\n"
				+ "  cursor        : pointer;     \r\n"
				+ "  padding       : 12px 12px;   \r\n"
				+ "  background    : #E7E7E7;      \r\n"
				+ "  color         : #000000;     \r\n"
				+ "  line-height   : 1em;         \r\n"
				+ "  transition    : .3s;         \r\n"
				+ "  box-shadow    : 6px 6px 3px #666666;  \r\n"
				+ "  border        : 2px solid #000000;    \r\n"
				+ "  margin:10px;	\r\n"
				+ "}\r\n"
				+ ".btn:hover {\r\n"
				+ "  box-shadow    : none;        \r\n"
				+ "  color         : #000000;     \r\n"
				+ "  background    : #E7E7E7;      \r\n"
				+ "  border-radius : 5%;          \r\n"
				+ "}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"boardwall\">");
		out.println("<h2>入力情報を確認して登録ボタンを押してください</h2>");
		out.println("ID：<strong>" + req.getParameter("id") + "</strong><br/>");
		out.println("パスワード：<strong>" + req.getParameter("password") + "</strong><br/>");
		out.println("氏名：<strong>" + req.getParameter("name") + "</strong><br/>");
		out.println("年齢：<strong>" + req.getParameter("age") + "</strong><br/>");
		out.println("性別：<strong>" + req.getParameter("gender") + "</strong><br/>");
		out.println("自己紹介：<strong>" + req.getParameter("selfinf") + "</strong><br/>");
		/* 取得した値をセット */
		req.setAttribute("id", req.getParameter("id"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("name", req.getParameter("name"));
		req.setAttribute("age", req.getParameter("age"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.setAttribute("selfinf", req.getParameter("selfinf"));
		/* 登録ボタンを配置 */
		out.println("<form method=\"get\" name=\"Forward\" class=\"btn\">");
		out.println("<input type=\"submit\" value=\"登録\" class=\"btn\"/>");
		out.println("</form");
		/* 登録結果画面へ値を渡す */
		RequestDispatcher rd = req.getRequestDispatcher("/Regiresult");
		rd.forward(req, res);

		out.println("<input type=\"reset\" value=\"戻る\" /><br/><br/>");
		out.println("（この画面はサーブレットで出力しています）");
		out.println("</div");
		out.println("</body>");
		out.println("</html>");
	}

}
