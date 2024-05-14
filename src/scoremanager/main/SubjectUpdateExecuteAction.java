package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("UPdateExe1");

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		SubjectDao sbDao = new SubjectDao();// 科目Dao
		SchoolDao scDao = new SchoolDao();// 学校Dao
		String subject_cd = "";//科目コード
		String subject_name = "";//科目名
		String school_cd = req.getParameter("school_cd");//学校コード
		Map<String, String> errors = new HashMap<>();//エラーメッセージ
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		School sc = scDao.get(school_cd);//scDAOのgetメソッドで学校コードを取得


		//リクエストパラメータ―の取得 2
		//取得
		subject_cd= req.getParameter("subject_cd");
		subject_name = req.getParameter("subject_name");
		school_cd = req.getParameter("school_cd");

		//DBからデータ取得 3
		//検索
		Subject subject = sbDao.get(subject_cd, sc);//科目コードから科目インスタンスを取得
		List<Subject> list = sbDao.filter(teacher.getSchool());//sbDAOのfilterメソッドで学校コードをlistにぶち込む


		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で4～5が分岐
		if ( subject!= null) {
			System.out.println("updateExe開始");
			// インスタンスに値をセット
			subject.setSubject_cd(subject_cd);
			subject.setName(subject_name);
			subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
			// 科目情報を保存
			sbDao.save(subject);
			System.out.println("updateExe完了");
		} else {
			//科目がない時の処理
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
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);//繰り返し処理
			return;
		}
		//完了ページ表示
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
}

}
