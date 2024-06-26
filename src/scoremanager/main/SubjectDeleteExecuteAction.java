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

public class SubjectDeleteExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		SubjectDao sbDao = new SubjectDao();//科目Dao
		SchoolDao scDao = new SchoolDao();//学校DAO
		Subject subject = null;//科目

		//取得
		String subject_cd = req.getParameter("subject_cd");//科目コード
		String school_cd = req.getParameter("school_cd");//学校コード
		String subject_name = req.getParameter("subject_name");//科目名
		//School sc = scDao.get(school_cd);
		Teacher teacher = (Teacher) session.getAttribute("user");//ログインユーザ


		//検索
		subject = sbDao.get(subject_cd,teacher.getSchool());//科目コードと学校コードを取得

		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		//Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

		//リクエストパラメータ―の取得 2
		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐

		if (subject== null) {// 科目が未登録だった場合
			System.out.println("subjectがnull");
			errors.put("subject_cd", "科目が存在してません");
		} else{//入力された科目がDBに保存されていた場合

			System.out.println("deleteExe開始");
			// 科目インスタンスを初期化
			subject = new Subject();

			subject.setSubject_cd(subject_cd);
			subject.setName(subject_name);
			subject.setSchool((scDao.get(school_cd)));
			// 科目情報を削除
			sbDao.delete(subject);
			System.out.println("deleteExe完了");
		}

		//レスポンス値をセット 6
		//JSPへフォワード 7
		if(!errors.isEmpty()){
			System.out.println("delete3");
			// リクエスト属性をセット
			req.setAttribute("errors", errors);

			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("subject_cd", subject_cd);
			req.setAttribute("subject_name", subject_name);
			req.setAttribute("school_cd", school_cd);
			req.getRequestDispatcher("subject_delete.jsp").forward(req, res);//同じ画面へ移行
			return;
		}
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);//完了画面へ移行

	}

}
