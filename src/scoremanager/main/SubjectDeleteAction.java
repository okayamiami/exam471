package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		SubjectDao sbDao = new SubjectDao();//科目Dao

		String subject_name = req.getParameter("subject_name");
		String subject_cd = req.getParameter("subject_cd");//科目コード
		//リクエストパラメータ―の取得 2
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		// ログインユーザーの学校コードをもとに科目の一覧を取得
		List<Subject> list = sbDao.filter(teacher.getSchool());
		Subject subject = sbDao.get(subject_cd,teacher.getSchool());//科目コードと学校コードを取得
		SchoolDao scDao =new SchoolDao();
		School sch=scDao.get(teacher.getSchool().getCd());


		//DBからデータ取得 3

		//なし
		//ビジネスロジック 4
		//なし
		if(subject==null){
			System.out.println("subjectに入ってない");
		}else{
			System.out.println("subjectに入ってる");
		}
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストにデータをセット
		req.setAttribute("subject_name", subject_name);
		req.setAttribute("subject_cd", subject_cd);
		req.setAttribute("school", sch);
		req.setAttribute("sblist", list);
		req.setAttribute("subject", subject);

		req.getRequestDispatcher("subject_delete.jsp").forward(req, res);

	}

}
