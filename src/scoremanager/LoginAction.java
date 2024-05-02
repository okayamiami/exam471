package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginAction extends Action{

	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {


		//セッション取得
		HttpSession session=req.getSession();

		//ログイン名とパスワードを取得
		String login=req.getParameter("login");
		String password=req.getParameter("password");

		//データベースからデータ検索
		TeacherDao dao=new TeacherDao();
		Teacher teacher=dao.login(login, password);

		//ある場合
		if (teacher!=null) {
			session.setAttribute("teacher", teacher);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		//ない場合
		req.getRequestDispatcher("error.jsp").forward(req, res);
	}




}

