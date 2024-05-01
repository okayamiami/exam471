package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		SubjectDao sbDao = new SubjectDao();//科目DAOをインスタンス化
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		String subject_cd = req.getParameter("subject_cd");//科目コード
		//String school_cd = req.getParameter("school_cd");

		Subject subject = sbDao.get(subject_cd);//科目コードから科目インスタンスを取得
		List<Subject> list = sbDao.filter(teacher.getSchool());

		//ビジネスロジック 4
		//科目コードから学生インスタンスを取得
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//条件で手順4~6の内容が分岐
		req.setAttribute("subject_", list);
		if (list != null) {// 学生が存在していた場合
			req.setAttribute("subject_cd", subject.getSubject_cd());
			req.setAttribute("subject_name", subject.getName());
		} else {// 科目が存在していなかった場合
			errors.put("subject_cd", "科目が存在していません");
			req.setAttribute("errors", errors);
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);

	}

}
