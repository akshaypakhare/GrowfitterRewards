package growfitter;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.DataProvider;

import resources.JDBC;
//this is a dataProvider class
public class LoginInputs {
	
	//@DataProvider(name="PuserLogin")
	/*
	 * public Object[][] getCreds() { Object[][] obj=new Object[][] {
	 * //registered,invalid,non-registered
	 * //{"9594499052"},{"34944990"},{"9594499059"} {"9594499052","000000"} };
	 * return obj; }
	 * 
	 * @DataProvider(name="PuserLoginInval") public Object[][] getPwd() { Object[][]
	 * obj=new Object[][] { {"9594499052","123456"} }; return obj;
	 * 
	 * }
	 */
	
	@DataProvider(name="LoginCredential")
	public Object[][] dpMethod (Method m){
		switch (m.getName()) {
		case "puserLogin": 
			return new Object[][] {{"9594499052","000000"}};
		case "loginLive":
			//live-success
			return new Object[][] {{"8108778753","000000"}};
		case "loginInvalPin": 
			//invalid pin tests
			//puser,live
			return new Object[][] {{"9594499052","123654"},{"8108778753","123654"}};
		case "loginInvalid":
			//invalid login which will take you to signup
			//puser-inactive,puser-expired tenure,puser-future joining,live-processing
			return new Object[][] {{"9594499011"},{"6594499050"},{"7594499004"},{"9988712109"}};
		case "loginRewardUser":
			//reward user success,status 0 means valid pin & otherwise invalid pin.
			return new Object[][] {{"7021190950","000000","0"},{"7021190950","123654","1"}};
			
		case "loginFitkit":
			//reward user success,status 0 means valid pin & otherwise invalid pin.
			return new Object[][] {{"7021190950","000000","0"}};
			
		}
		return null;
	}
	
	@DataProvider(name="DBlogincred")
	public Object[][] db (Method m) throws Exception{
		switch (m.getName()) {
		case "loginFitkit":
			String data[][]=readDB();
			return data;
		}
		return null;
	}
	
	public String[][]  readDB( ) throws Exception 
	{
		Connection con = JDBC.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM `gt_qalogininputs` WHERE `gt_usertype`=1 AND `gt_app`=1");
		String mob = null;
		String pin = null;
		while(rs.next()) {
			mob = rs.getString("gt_mobile");
			pin = rs.getString("gt_pin");
		}
		return new String[][] {{mob,pin,"0"}};
		
	}
	
}

