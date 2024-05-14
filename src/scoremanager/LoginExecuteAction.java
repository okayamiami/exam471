package scoremanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";

		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		Teacher teacher = new Teacher();

		TeacherDao tDao= new TeacherDao();
		//リクエストパラメータ―の取得 2
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		teacher=tDao.login(id, password);

		if(teacher==null){
			errors.put("null", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
		}else{
			teacher.setAuthenticated(true);
		}

		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return;
		}
		//Sessionを有効にする
		HttpSession session = req.getSession(true);
		//セッションに"user"という変数名で値はTeacher変数の中身
		session.setAttribute("user", teacher);

		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7
		//req.getRequestDispatcher("main/Menu.action").forward(req, res);

		//リダイレクト
		url = "main/Menu.action";
		res.sendRedirect(url);
	}
}
