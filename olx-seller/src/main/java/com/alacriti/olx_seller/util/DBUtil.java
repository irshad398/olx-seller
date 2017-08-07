package com.alacriti.olx_seller.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;





public class DBUtil 
{
	
	private String fileName;
	private String dbPropsFile;
	private Map<Integer, String> clientLogoURLMap; 
	private Map<Integer, String> clientCallBackURLMap; 
	private static final int customer_unique_identifier_type =2;
	
	
	public DBUtil(String dbPropsFile)
	{
		
		this.dbPropsFile = dbPropsFile;
		clientLogoURLMap = new HashMap<Integer, String>();
		clientLogoURLMap.put(new Integer(1), "/onlineshopping/images/home/logo.png");
		clientLogoURLMap.put(new Integer(2), "/ecommerce/images/home/logo.png");
		clientLogoURLMap.put(new Integer(3), "/cameron-insurance/images/checkout_logo.png");
		
		clientCallBackURLMap = new HashMap<Integer, String>();
		clientCallBackURLMap.put(new Integer(1), "/onlineshopping/post-token");
		clientCallBackURLMap.put(new Integer(2), "/ecommerce/post-token");
		clientCallBackURLMap.put(new Integer(3), "/cameron-insurance/post-token");
	}
	
	
	protected Connection getConnection()
	{
		
		Connection con = null;
		try 
		{
			
			Properties props =  readPropertyFile();
			
			String dbURL = "jdbc:mysql://"+
						(String) props.get("DB_HOST")+":"+
						(String) props.get("DB_PORT")+"/"+
						(String) props.get("DB_SCHEMA_NAME");
			
			String dbUserName = (String) props.get("DB_USERNAME");
			String dbPasswd = (String) props.get("DB_PASSWD");
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(dbURL, dbUserName, dbPasswd);
			return con;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	private void testConnection()
	{
		Connection con = null;
		try
		{
			con = getConnection();			
			if (con!=null)
			{
				System.out.println("Test connection success");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(con, null, null);
		}
	}

	private Properties readPropertyFile() throws Exception 
	{
		Properties prop = null;
		InputStream input = null;
		try 
		{
			prop = new Properties();
			input = new FileInputStream(dbPropsFile);
			prop.load(input);
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (input != null) 
			{
				try 
				{
					input.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	protected void close(Connection con, Statement st,  ResultSet rs)
	{
		try 
		{
			if (rs!=null) rs.close();
			
			if (st!=null) st.close();
			
			if (con!=null) con.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	protected List<MerchantVO> getMerchants()
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<MerchantVO> merchants = new ArrayList<MerchantVO>();
		
		try
		{
			con = getConnection();
			ps = con.prepareStatement("select * from client_tbl");
			rs = ps.executeQuery();
			while (rs.next())
			{
				MerchantVO mvo = new MerchantVO();
				mvo.setClientIdPK(rs.getLong(1));
				mvo.setClient_issued_id(rs.getString(2));
				merchants.add(mvo);
			}
			return merchants;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(con, ps, rs);
		}
		return merchants;
	}
	
	public static void main(String[] args) 
	{
		
		String dbPropsFile = args[0]; 
		
		if (args[0]==null || args[0].trim().length()<=0)
		{
			System.out.println("DB properties file not provided. Process exited");
		}
		
		DBUtil dbu = new DBUtil(dbPropsFile);
		dbu.testConnection();
		
		while (true)
		{
			System.out.println("Enter 1 to create merchants");
			System.out.println("Enter 2 to create customers");
			
			Scanner inp = new Scanner(System.in);
			String option = inp.nextLine();
			if (option.equals("1"))
			{
				System.out.println ("Enter file path and file name to create partners");
				Scanner scanner = new Scanner(System.in);
				String fileName = scanner.nextLine();
				
				if (scanner!=null)
					scanner.close();
				
				dbu.setFileName(fileName);
				System.exit(0);
			}
			if (option.equals("2"))
			{
				System.out.println("Enter customer login id");
				Scanner scan = new Scanner(System.in);
				
				System.out.println("Enter number of customers to be created per partner");
				if (scan!=null)
					scan.close();
				System.exit(0);
			}
			else
			{
				System.out.println("Invalid option");
				System.exit(0);
			}
			if (inp!=null)
				inp.close();
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}


class MerchantVO
{
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_issued_id() {
		return client_issued_id;
	}
	public void setClient_issued_id(String client_issued_id) {
		this.client_issued_id = client_issued_id;
	}
	public String getObp_issued_partner_key() {
		return obp_issued_partner_key;
	}
	public void setObp_issued_partner_key(String obp_issued_partner_key) {
		this.obp_issued_partner_key = obp_issued_partner_key;
	}
	public String getObp_signature_key1() {
		return obp_signature_key1;
	}
	public void setObp_signature_key1(String obp_signature_key1) {
		this.obp_signature_key1 = obp_signature_key1;
	}
	public String getObp_signature_key2() {
		return obp_signature_key2;
	}
	public void setObp_signature_key2(String obp_signature_key2) {
		this.obp_signature_key2 = obp_signature_key2;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getLogo_path() {
		return logo_path;
	}
	public void setLogo_path(String logo_path) {
		this.logo_path = logo_path;
	}
	public String getCall_back_url() {
		return call_back_url;
	}
	public void setCall_back_url(String call_back_url) {
		this.call_back_url = call_back_url;
	}
	public String getCss_file_path() {
		return css_file_path;
	}
	public void setCss_file_path(String css_file_path) {
		this.css_file_path = css_file_path;
	}
	public String getTxn_option_id() {
		return txn_option_id;
	}
	public void setTxn_option_id(String txn_option_id) {
		this.txn_option_id = txn_option_id;
	}
	public String getAddress_option_id() {
		return address_option_id;
	}
	public void setAddress_option_id(String address_option_id) {
		this.address_option_id = address_option_id;
	}
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
	public String getLast_modified_time() {
		return last_modified_time;
	}
	public void setLast_modified_time(String last_modified_time) {
		this.last_modified_time = last_modified_time;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	
	public long getClientIdPK() {
		return clientIdPK;
	}
	public void setClientIdPK(long clientIdPK) {
		this.clientIdPK = clientIdPK;
	}

	public int getCustomer_unique_identifier_type() {
		return customer_unique_identifier_type;
	}
	public void setCustomer_unique_identifier_type(
			int customer_unique_identifier_type) {
		this.customer_unique_identifier_type = customer_unique_identifier_type;
	}

	private String client_id;
	private String client_issued_id;
	private String obp_issued_partner_key;
	private String obp_signature_key1;
	private String obp_signature_key2;
	private String client_name;
	private String logo_path;
	private String call_back_url;
	private String css_file_path;
	private String txn_option_id;
	private String address_option_id;
	private String creation_time;
	private String last_modified_time;
	private String template_id;
	private int customer_unique_identifier_type;
	private long clientIdPK; 

}

