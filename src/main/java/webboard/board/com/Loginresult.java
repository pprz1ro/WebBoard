package webboard.board.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Loginresult")
public class Loginresult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Loginresult() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String getid = null;
		String getpass = null;
		String dbid = null;
		String dbpass = null;
		String dbname = null;

		try {
			/* JDBCドライバをロード */
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			/*データベースに接続するConnectionオブジェクトの取得 */
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/[DBテーブル名]", "[MySQLユーザー名]", "[MySQLパスワード]");
			/* データベース操作を行うためのStatementオブジェクトの取得 */
			stm = con.createStatement();
			/* 入力されたIDとパスワードの値を取得 */
			getid = req.getParameter("id");
			getpass = req.getParameter("password");
			/* SQL()を実行 */
			rs = stm.executeQuery("select * from member where id= " + "'" + getid + "'");

			/* 得られた結果をレコードごとに表示 */
			while (rs.next()) {
				dbid = rs.getString("id");
				dbpass = rs.getString("pass");
				dbname = rs.getString("name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* データベースとの接続をクローズ */
			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		/* デザインを付与 */
		out.println("<style> body { background-color: #9F9F9F} "
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
				+ "  background    : #E7E7E7;     \r\n"
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
				+ "  background    : #E7E7E7;     \r\n"
				+ "  border-radius : 5%;          \r\n"
				+ "}</style>");

		/* 入力されたIDとパスワードとDB上のIDとパスワードを照合 */
		if (getid.equals(dbid) && getpass.equals(dbpass)) {

			out.println("<html>");
			out.println("<head>");
			out.println("<title>確認画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"boardwall\">");
			out.println("ログインが成功しました");

			out.println("<form method=\"POST\" action=\"Profile.jsp\" name=\"Forward\">");
			out.println("<input type=\"submit\" value=\"プロフィール画面へ進む\" class=\"btn\"/>");
			out.println("</form>");

			out.println("<form method=\"POST\" action=\"Board.jsp\" name=\"Forward\">");
			out.println("<input type=\"submit\" value=\"掲示板へ進む\" class=\"btn\"/>");
			out.println("</form>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

			// セッションオブジェクトの生成or取得
			HttpSession session = req.getSession(true);
			session.setAttribute("seid", dbid);
			session.setAttribute("sename", dbname);

		} else if (!(getid.equals(dbid))) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>確認画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"boardwall\">");
			out.println("入力されたIDかパスワードが一致しません。再度ご入力ください。");

			out.println("<form method=\"POST\" action=\"Login.jsp\" name=\"Forward\">");
			out.println("<input type=\"submit\" value=\"ログイン画面へ戻る\" class=\"btn\"/>");
			out.println("</form>");

			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

		} else if (getid.equals(dbid) && !(getpass.equals(dbpass))) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>確認画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"boardwall\">");
			out.println("入力されたパスワードが一致しません。再度ご入力ください。");

			out.println("<form method=\"POST\" action=\"Login.jsp\" name=\"Forward\">");
			out.println("<input type=\"submit\" value=\"ログイン画面へ戻る\" class=\"btn\"/>");
			out.println("</form>");

			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}
		;

	}

}
