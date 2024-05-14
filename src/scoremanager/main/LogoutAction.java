package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action{

	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {

		//セッションを取得

		HttpSession session=req.getSession();

		// ログインユーザーを取得
		//Teacher teacher = (Teacher) session.getAttribute("user");
		if(session.getAttribute("user")!=null) {
			session.removeAttribute("user");
			System.out.println("1");
			req.getRequestDispatcher("logout.jsp").forward(req, res);
		}






	}
}

