package example13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEmpoyees {

	public static void main(String[] args) {
		//JDBCドライバを読み込む
	try {
		//ドライバークラスの読み込み
		
		Class.forName("org.mariadb.jdbc.Driver");
	}catch(ClassNotFoundException e) {
	throw new IllegalStateException(
			"JDBCドラバを読み込めませんでした");
	
	}
	//xamppでポート番号確認、データベース名
	
	String db = "jdbc:mariadb://localhost:3306/example";
	String user ="root";
	String password="";
	
	//データベースに接続
	
	try(Connection conn = 
			DriverManager.getConnection(db,user,password)){
		
		//SELECT文を準備
		
		String sql = "SELECT ID,NAME,AGE FROM EMPLOYEE";
		//String sql = "SELECT * FROM EMPLOYEE";
	
		//PreparedStatement でSQL文の準備を行う
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		//SELECT文を実行し、結果表(ResultSet)を取得
		//ResultSetインスタンスにSELECT文の結果が格納される
		ResultSet rs = stmt.executeQuery();
		
		//結果表に格納されたレコードの内容を表示
		//結果表の取り出し対象レコードを１つ進める		
		while(rs.next()){
			//結果表の取り出し対象レコードを１つ進める
			String id = rs.getString("ID");
			String name = rs.getString("NAME");
			int age = rs.getInt("AGE");
			
			//取得したデータの表示
			System.out.println("ID:"+ id);
			System.out.println("名前:"+ name);
			System.out.println("年齢:"+ age +"\n");
		}
		
	}catch(SQLException e) {
		//接続やSQL処理失敗時の処理
		e.printStackTrace();
	}
	}

}
