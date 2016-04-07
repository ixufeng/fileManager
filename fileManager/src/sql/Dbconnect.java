package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import file.PathConfig;
/**
 * 
 * @author xiejialong
 * 数据库链接
 */
public class Dbconnect {
	
	String url = PathConfig.SQLURL;
	Connection con = null;
	Statement state = null;
	ResultSet rs = null;
	PreparedStatement dir=null;

	// 获取连接
	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				con = DriverManager.getConnection(url);
			} catch (SQLException e) {
				System.out.println("fail");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动类");
		}
		return con;
	}
	// 定义函数进行增、删、改
	public int getModify(String sql) {
		int d = 0;
		try {
			state = getConn().createStatement();
			d = state.executeUpdate(sql);
			closeState();
			closeCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}
	//预处理
	public void prepare(){
		String sql="slect * from file where filePath=?";
				try {
					dir=getConn().prepareStatement(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	//查询目录下的文件记录
	public ResultSet selectFile(String dirPath){
		if(dir==null)
			prepare();
		else
			try {
				dir.setString(1, dirPath);
				rs=dir.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return rs;
	}
	// 查询
	public ResultSet select(String sql) {
		try {
			state = getConn().createStatement();
			rs = state.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
	// 定义函数进行关闭
	public void closeCon() {
		try {
			if (con != null || !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeState() {
		try {
			if (state != null || !state.isClosed()) {
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeRs() {
		try {
			if (!rs.isClosed() && rs != null) {
				rs.close();
			}
			closeState();
			closeCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

	

