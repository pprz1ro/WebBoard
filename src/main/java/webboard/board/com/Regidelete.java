package webboard.board.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Regidelete")
public class Regidelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Regidelete() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		/* デザインを付与 */
		out.println("<style> body "
				+ "{ background-color: #9F9F9F} "
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
				+ "  color         : #000000;      \r\n"
				+ "  line-height   : 1em;         \r\n"
				+ "  transition    : .3s;         \r\n"
				+ "  box-shadow    : 6px 6px 3px #666666;  \r\n"
				+ "  border        : 2px solid #000000;    \r\n"
				+ "  margin:10px;	\r\n"
				+ "}\r\n"
				+ ".btn:hover {\r\n"
				+ "  box-shadow    : none;        \r\n"
				+ "  color         : #000000;      \r\n"
				+ "  background    : #E7E7E7;     \r\n"
				+ "  border-radius : 5%;          \r\n"
				+ ".caution{\r\n"
				+ "	font-size: 10px\r\n"
				+ "	margin-top: 10px;\r\n"
				+ "}</style>");
		/* sessionの取得 */
		HttpSession session = req.getSession(false);
		String id = (String) session.getAttribute("seid");
		/* データベースへのアクセス開始 */
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String name = null;
		String nowtime = null;
		String written = null;
		/* SQL文の定義 */
		String sql = "delete from member where id='" + id + "'";

		try {
			/* JDBCドライバをロード */
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			/* データベースに接続するConnectionオブジェクトの取得 */
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/[DBテーブル名]", "[MySQLユーザー名]", "[MySQLパスワード]");
			/* データベース操作を行うためのStatementオブジェクトの取得 */
			stm = con.createStatement();
			/* SQL()を実行 */
			int count = stm.executeUpdate(sql);

			/* 作成済みのsessionを削除*/
			session.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/* データベースとの接続をクローズ */
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				stm.close();
			} catch (Exception e) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Account delete success</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"boardwall\">");
		out.println("<div class=\"caution\">削除しました。</div>");

		out.println("<form method=\"POST\" action=\"Board.jsp\" name=\"Forward\">");
		out.println("<input type=\"submit\" value=\"掲示板へ\" class=\"btn\"/>");
		out.println("</form>");

		out.println("</div");
		out.println("</body>");
		out.println("</html>");

	}

}
