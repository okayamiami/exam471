package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class LogoutAction extends Action{

	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {

		//セッションを取得
		HttpSession session=req.getSession();
		// ログインユーザーを取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		//if(session.getAttribute("Teacher")!=null) {
			//session.removeAttribute("Teacher");
			//req.getRequestDispatcher("logout.jsp").forward(req, res);
		//}

		if(teacher!=null){
			session.removeAttribute("teacher");
		}else{
			return;
		}

		req.getRequestDispatcher("logout_done.jsp").forward(req, res);

	}
}
