package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		//HttpSession session = req.getSession();//セッション
		SubjectDao sbDao = new SubjectDao();//科目Dao
		SchoolDao scDao = new SchoolDao();

		String subject_cd = req.getParameter("subject_cd");//科目コード
		String school_cd = req.getParameter("school_cd");//学校コード
		School sc = scDao.get(school_cd);

		Subject subject = sbDao.get(subject_cd, sc);//科目コードから科目インスタンスを取得

		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		//Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

		//リクエストパラメータ―の取得 2
		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐

		if (subject == null) {// 科目が未登録だった場合

			return;

		} else{//入力された学番がDBに保存されていた場合

			// 科目インスタンスを初期化
			subject = new Subject();
			// 科目情報を保存
			sbDao.delete(subject);
		}

		//レスポンス値をセット 6
		//JSPへフォワード 7

		//System.out.println("3-2");
		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("subject_delete.jsp").forward(req, res);

			return;
		}
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);

	}

}
