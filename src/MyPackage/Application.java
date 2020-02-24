package MyPackage;

import javax.swing.JFrame;
import java.sql.SQLException;

public class Application {
	public static void main(String args[])throws SQLException
	{ 
		JFrame f = new MaFrame();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}

}