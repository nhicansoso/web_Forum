package HoTro;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoi {
	public Connection cn;
	public void ketnoi() throws Exception{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		cn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Project_Java;user=sa; password=123");
		System.out.println("Da Ket Noi");
	}
}
