package webboard.board.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Comwrite")
public class Comwrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Comwrite() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		//スタイル付与
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

		/* 入力されたコメントの値を取得 */
		String comment = (String) req.getParameter("comment");
		/* コメント文字数が制限を越えた場合 */
		int math = comment.length();
		if (math > 400) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>確認画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"boardwall\">");
			out.println("書き込み文字数は400字以内です。再度ご入力ください。");
			out.println("<form method=\"POST\" action=\"Board.jsp\" name=\"Forward\">");
			out.println("<input type=\"submit\" value=\"掲示板へ戻る\" class=\"btn\"/>");
			out.println("</form");
			out.println("</div");
			out.println("</body>");
			out.println("</html>");
		} else {

			String sql5 = null;
			String id = null;
			String name = null;
			/* 作成済みのsessionを取得 */
			HttpSession session = req.getSession(false);

			/* 現在日時の取得 */
			LocalDateTime now = LocalDateTime.now();
			/* 現在日時の表示形式指定 */
			DateTimeFormatter formatNow = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			String nowTime = formatNow.format(now);

			/* sessionあり、ログインなしの場合のコメント書き込み */
			if (session != null && (id = (String) session.getAttribute("seid")) == null) {
				session = null;
				sql5 = "insert into written values(no,'noID','名無しさん','"
						+ comment + "',"
						+ "'" + nowTime + "'"
						+ ")";
			/* sessionあり、ログインありの場合のコメント書き込み */
			} else if (session != null && (id = (String) session.getAttribute("seid")) != null) {
				name = (String) session.getAttribute("sename");
				sql5 = "insert into written values(no,'"
						+ id + "',"
						+ "'" + name + "',"
						+ "'" + comment + "',"
						+ "'" + nowTime + "'"
						+ ")";
			}

			/* データベースへのアクセス開始 */
			Connection con = null;
			Statement stm = null;
			ResultSet rs = null;
			PreparedStatement ps = null;

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
				/* SQL()を実行 */
				int count = stm.executeUpdate(sql5);

			} catch (SQLException e) {
				e.printStackTrace();
			} 
			/* データベースとの接続をクローズ */
			finally {

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
			/* HTML、CSS */
			out.println("<html>");
			out.println("<head>");
			out.println("<title>書き込み完了画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"boardwall\">");
			out.println("<h1>投稿が完了しました</h1>");

			out.println("<form method=\"POST\" action=\"Board.jsp\" name=\"Forward\">");
			out.println("<input type=\"submit\" value=\"掲示板へ戻る\" class=\"btn\"/>");
			out.println("</form");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

		}

	}
}
