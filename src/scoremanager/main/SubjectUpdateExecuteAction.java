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

public class SubjectUpdateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("UPdateExe1");

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		SubjectDao sbDao = new SubjectDao();// 科目Dao
		//SchoolDao scDao = new SchoolDao();// 学校Dao
		String subject_cd = "";//科目コード
		String subject_name = "";//科目名
		Map<String, String> errors = new HashMap<>();//エラーメッセージ
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得

		//リクエストパラメータ―の取得 2
		subject_cd= req.getParameter("subject_cd");
		subject_name = req.getParameter("subject_name");
		//String school_cd = req.getParameter("school_cd");


		System.out.println("UPdateExe2");

		//DBからデータ取得 3
		List<Subject> list = sbDao.filter(teacher.getSchool());
		Subject subject = sbDao.get(subject_cd,teacher.getSchool());// 科目コードから科目インスタンスを取得

		//School school = scDao.get(school_cd);

		//List<Subject> list = sbDao.filter(teacher.getSchool());//ログインユーザーの学校コードをもとに科目の一覧を取得

		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で4～5が分岐
		System.out.println("UPdateExe3");
		if ( list!= null) {
			System.out.println("updateExe開始");
			// 科目インスタンスを初期化
			//subject = new Subject();
			// インスタンスに値をセット
			//subject.setSubject_cd(subject_cd);
			subject.setName(subject_name);
			//subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
			// 科目情報を保存
			sbDao.save(subject);
			System.out.println("updateExe完了");
		} else {
			System.out.println("科目がnull");
			errors.put("list", "科目が存在していません");
		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		req.setAttribute("sblist", list);
		//JSPへフォワード 7
		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			System.out.println("UPdateExeエラー処理");
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("subject_cd", subject_cd);
			req.setAttribute("subject_name", subject_name);
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
			return;
		}
		System.out.println("完了ページへ");

		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
}

}
