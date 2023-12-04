package webboard.board.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Regiresult")
public class Regiresult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Regiresult() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		
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
				+ "  border-radius : 5%;          /* 角丸       */\r\n"
				+ "  font-size     : 10px;        /* 文字サイズ */\r\n"
				+ "  text-align    : center;      /* 文字位置   */\r\n"
				+ "  cursor        : pointer;     /* カーソル   */\r\n"
				+ "  padding       : 12px 12px;   /* 余白       */\r\n"
				+ "  background    : #E7E7E7;     /* 背景色     */\r\n"
				+ "  color         : #000000;    /* 文字色     */\r\n"
				+ "  line-height   : 1em;         /* 1行の高さ  */\r\n"
				+ "  transition    : .3s;         /* なめらか変化 */\r\n"
				+ "  box-shadow    : 6px 6px 3px #666666;  /* 影の設定 */\r\n"
				+ "  border        : 2px solid #000000;    /* 枠の指定 */\r\n"
				+ "  margin:10px;	\r\n"
				+ "}\r\n"
				+ ".btn:hover {\r\n"
				+ "  box-shadow    : none;        /* カーソル時の影消去 */\r\n"
				+ "  color         : #000000;      /* 背景色     */\r\n"
				+ "  background    : #E7E7E7;     /* 文字色     */\r\n"
				+ "  border-radius : 5%;          /* 角丸       */\r\n"
				+ "}</style>");
		
		//Linux用
				final String URL = "jdbc:mysql://localhost:3306/sampledb";
				final String USER = "sample";
				final String PASS = "Ppr0@0905";
				
//		String sql = "insert into member values(6,'梅原',75);";
//		String sql2 = "insert into member values(7,'柿町',90);";
//		String sql3 = "select * from member";
//		String sql4 = "insert into member values(?,?,?)";

		Object reid = (String)req.getAttribute("id");
		Object repassword = (String)req.getAttribute("password");
		Object rename = (String)req.getAttribute("name");
		Object reage = (String)req.getAttribute("age");
		Object regender = (String)req.getAttribute("gender");
		Object reselfinf = (String)req.getAttribute("selfinf");
		Object emp="";
		
		String sid=(String)reid;
		String spass=(String)repassword;
		String sname=(String)rename;
		String sage=(String)reage;
		String sselfinf=(String)reselfinf;
		int iid=sid.length();
		int ipass=spass.length();
		int iname=sname.length();
		int iage=sage.length();
		int iselfinf=sselfinf.length();
		
		if(reid==emp||repassword==emp||rename==emp||
				reage==emp||regender==emp||reselfinf==emp) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>確認画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"boardwall\">");
			out.println("未記入の項目があります。再度ご入力ください。");
			out.println("<form method=\"POST\" action=\"Register.jsp\" name=\"Forward\">");
			out.println("<input type=\"submit\" value=\"プロフィール登録へ戻る\" class=\"btn\"/>");
			out.println("</form");
			out.println("</div");
			out.println("</body>");
			out.println("</html>");
			
		}else if(iid<6||iid>12||ipass<6||ipass>12||iname>10||iage>3||iselfinf>400) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>確認画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"boardwall\">");
			out.println("入力した項目の文字数が規定通りではありません。再度ご入力ください。");
			out.println("<form method=\"POST\" action=\"Register.jsp\" name=\"Forward\">");
			out.println("<input type=\"submit\" value=\"プロフィール登録へ戻る\" class=\"btn\"/>");
			out.println("</form");
			out.println("</div");
			out.println("</body>");
			out.println("</html>");
			
		}else {
		
		String sql5 = "insert into member values(no ,'"
				+ reid + "',"
				+ "'" + repassword + "',"
				+ "'"+ rename + "',"
				+ reage + ","
				+ "'" + regender + "',"
				+ "'" + reselfinf + "'"
				+ ")";

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
			/* データベースに接続するConnectionオブジェクトの取得 */
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sample", "Ppr0@0905");
			/* データベース操作を行うためのStatementオブジェクトの取得 */
			stm = con.createStatement();
			/* SQL()を実行 */
			int count = stm.executeUpdate(sql5);

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
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Regislation Result</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<div class=\"boardwall\">");
				out.println("ID・パスワード・プロフィールの登録は以下の通り登録されました。</br>");
				out.println("ID：<strong>" + reid + "</strong><br/>");
				out.println("password：<strong>" + repassword + "</strong><br/>");
				out.println("ユーザー名：<strong>" + rename + "</strong><br/>");
				out.println("年齢：<strong>" + reage + "</strong><br/>");
				out.println("性別：<strong>" + regender + "</strong><br/>");
				out.println("自己紹介：<strong>" + reselfinf + "</strong><br/>");

				out.println("<form method=\"POST\" action=\"Board.jsp\" name=\"Forward\">");
				out.println("<input type=\"submit\" value=\"掲示板へ進む\" class=\"btn\"/>");
				out.println("</form>");
				
				out.println("<form method=\"POST\" action=\"Login.jsp\" name=\"Forward\">");
				out.println("<input type=\"submit\" value=\"ログイン画面へ\" class=\"btn\"/>");
				out.println("</form");
								
				out.println("</div");
				out.println("</body>");
				out.println("</html>");

	}
	
	}

}
