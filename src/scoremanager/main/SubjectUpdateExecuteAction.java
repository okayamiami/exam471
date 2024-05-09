package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		SubjectDao sbDao = new SubjectDao();// 科目Dao
		SchoolDao scDao = new SchoolDao();// 学校Dao
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメータ―の取得 2
		String subject_cd= req.getParameter("subject_cd");
		String subject_name = req.getParameter("subject_name");
		String school_cd = req.getParameter("school_cd");

		//DBからデータ取得 3
		Subject subject = sbDao.get(subject_cd,teacher.getSchool());// 科目コードから科目インスタンスを取得
		//School school = scDao.get(school_cd);

		//List<Subject> list = sbDao.filter(teacher.getSchool());//ログインユーザーの学校コードをもとに科目の一覧を取得

		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で4～5が分岐
		if (subject == null) {
			errors.put("subject_cd", "科目が存在していません");
		} else {
			System.out.println("updateExe開始");
			// 科目が存在していた場合
			// インスタンスに値をセット
			//subject.setSubject_cd(subject_cd);
			//subject.setName(subject_name);
			//school.setCd(school_cd);
			//subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
			// 科目を保存
			//sbDao.save(subject);
			// 科目インスタンスを初期化
			subject = new Subject();
			// インスタンスに値をセット
			subject.setSubject_cd(subject_cd);
			System.out.println("update1");
			subject.setName(subject_name);
			System.out.println("update2");
			subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
			System.out.println("update3");
			// 科目情報を保存
			sbDao.save(subject);
			System.out.println("updateExe終了");

		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7


		System.out.println("updateExecute");
		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			System.out.println("111");
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("subject_cd", subject_cd);
			req.setAttribute("subject_name", subject_name);
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
			return;
		}
		System.out.println("222");

		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
}

}
