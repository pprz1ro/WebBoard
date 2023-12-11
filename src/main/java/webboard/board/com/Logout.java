package webboard.board.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
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
		out.println("<title>Logout Completed</title>");
		out.println("<style> body "
				+ "{ background-color: #9F9F9F;} "
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
				+ "  background    : #E7E7E7;   \r\n"
				+ "  border-radius : 5%;          \r\n"
				+ "}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"boardwall\">");

		/* 作成済みのsessionを取得し削除*/
		HttpSession session = req.getSession(false);
		session.invalidate();
		out.println("ログアウトが成功しました");
		/* ログイン画面、掲示板へ進むボタンを配置 */
		out.println("<form method=\"POST\" action=\"Login.jsp\" name=\"Forward\">");
		out.println("<input type=\"submit\" value=\"ログイン画面へ進む\" class=\"btn\"/>");
		out.println("</form>");

		out.println("<form method=\"POST\" action=\"Board.jsp\" name=\"Forward\">");
		out.println("<input type=\"submit\" value=\"掲示板へ進む\" class=\"btn\"/>");
		out.println("</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}

}
