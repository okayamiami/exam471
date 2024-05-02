package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
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
		TeacherDAO dao=new TeacherDAO();
		Teacher teacher=dao.search(login, password);

		//ある場合
		if (teacher!=null) {
			session.setAttribute("teacher", teacher);
			return "login.jsp";
		}
		//ない場合
		return "error.jsp";
	}
}

		req.getRequestDispatcher("login.jsp").forward(req, res);

	}
}
