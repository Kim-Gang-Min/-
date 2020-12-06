package guest_Book_pack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.Vector;
import javax.imageio.*;
import java.awt.image.*;
import java.text.SimpleDateFormat;

class LoginFrame extends JFrame{
	private JLabel id,pw;
	private JTextField idText;
	private JPasswordField passText;
	private JButton loginB,signUp;
	private ActionListener logA = new loginAction();
	String ID="",PASS="",userID="";
	private Container content; 
	private BufferedReader loginFin = null;
	private BufferedReader idFin = null;
	private int exist=-1;
	private LoginFrame temp;
	
	LoginFrame()
	{
		setTitle("Guest Book Login");
		setSize(400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		content = getContentPane();
		content.setBackground(Color.DARK_GRAY);
		content.setLayout(null);
		
		id = new JLabel("아이디 : ");
		id.setLocation(50,150);
		id.setSize(100,40);
		id.setFont(new Font("Serif",Font.BOLD,11));
		id.setForeground(Color.WHITE);
		id.setFont(id.getFont().deriveFont(15.0f));
		add(id);
		
		pw = new JLabel("비밀번호 : ");
		pw.setLocation(50,200);
		pw.setSize(100,40);
		pw.setFont(new Font("Serif",Font.BOLD,11));
		pw.setForeground(Color.WHITE);
		pw.setFont(pw.getFont().deriveFont(15.0f));
		add(pw);
		
		idText = new JTextField();
		idText.setLocation(150,150);
		idText.setSize(200,40);
		idText.setFont(new Font("Serif",Font.BOLD,11));
		idText.setFont(idText.getFont().deriveFont(15.0f));
		add(idText);
		
		passText = new JPasswordField();
		passText.setLocation(150,200);
		passText.setSize(200,40);
		passText.setFont(new Font("Serif",Font.BOLD,11));
		passText.setFont(passText.getFont().deriveFont(15.0f));
		add(passText);
		
		
		
		loginB = new JButton("로그인");
		loginB.setLocation(125,350);
		loginB.setSize(150,40);
		loginB.setFont(loginB.getFont().deriveFont(16.0f));
		loginB.setForeground(Color.DARK_GRAY);
		loginB.setBackground(Color.LIGHT_GRAY);
		loginB.addActionListener(logA);
		add(loginB);
		
		signUp = new JButton("회원가입");
		signUp.setLocation(125,425);
		signUp.setSize(150,40);
		signUp.setFont(signUp.getFont().deriveFont(16.0f));
		signUp.setForeground(Color.DARK_GRAY);
		signUp.setBackground(Color.LIGHT_GRAY);
		signUp.addActionListener(logA);
		add(signUp);
		temp = this;
		
		setVisible(true);
		
	}
	
	class loginAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == loginB) {
				try {
					idFin = new BufferedReader( new FileReader("./Saves/login/idKey.txt"));
				
				
					while((ID = idFin.readLine())!=null) {
						if(ID.equals(String.valueOf(idText.getText())))
						{
							exist = 0;
							break;
						}
					}
				
				idFin.close();
				if(exist==0)
				{
					
					exist=-1;
					loginFin = new BufferedReader(new FileReader("./Saves/login/login.txt"));
					while((userID = loginFin.readLine())!=null) {
						if(userID.startsWith(ID)) {
							exist = 0;
							break;
						}
					}
					if(exist==0)
					{
						String [] PASS = userID.split(" ");
						if(PASS[1].equals(String.valueOf(passText.getText())))
						{
							postFrame post = new postFrame(userID,true);
							temp.dispose();
						}
						else {
							JOptionPane.showMessageDialog(content,"비밀번호가 다릅니다.","Password warning",JOptionPane.WARNING_MESSAGE);
							exist=-1;
						}
					}

				}
				else {
					JOptionPane.showMessageDialog(content,"아이디가 존재하지 않습니다.","ID warning",JOptionPane.WARNING_MESSAGE);
				}
			}
				
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getSource()==signUp) {
				signUpFrame sign= new signUpFrame();
			
			}
		}
	}
}


class signUpFrame extends JFrame{
	private JLabel id,password,pass_Check, name, nickName,e_mail,number,minus1,minus2,e_symbol; 
	private JTextField idText, nameText,nickNameText,emailText,numberText1,numberText2,numberText3;
	private JPasswordField passText,pass_CheckText;
	private Container content;
	private String email[] = {"naver.com", "jbnu.ac.kr","gmail.com","daum.net"};
	private JComboBox<String> e_choice;
	private JButton accept,cancle,overCheck;
	private signAction sA1 = new signAction();
	private signUpFrame temp;
	private boolean over = true;
	private BufferedWriter loginFout = null;
	private BufferedReader idFin = null;
	
	signUpFrame(){
		setTitle("SignUp is Free!");
		setSize(400,600);
		setLayout(null);
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) /2 -450, (screenSize.height - frameSize.height) /2-100);
		
		content = getContentPane();
		content.setBackground(Color.DARK_GRAY);

		id = new JLabel("아이디 : ");
		id.setLocation(20,50);
		id.setSize(150,30);
		id.setFont(id.getFont().deriveFont(16.0f));
		id.setForeground(Color.WHITE);
		add(id);
		
		overCheck = new JButton("중복체크");
		overCheck.setLocation(280,50);
		overCheck.setSize(90,30);
		overCheck.setFont(new Font("Serif",Font.BOLD,11));
		overCheck.setFont(overCheck.getFont().deriveFont(13.0f));
		overCheck.setForeground(Color.DARK_GRAY);
		overCheck.setBackground(Color.LIGHT_GRAY);
		overCheck.addActionListener(sA1);
		add(overCheck);
		
		password = new JLabel("비밀번호 : ");
		password.setLocation(20,100);
		password.setSize(150,30);
		password.setFont(password.getFont().deriveFont(16.0f));
		password.setForeground(Color.WHITE);
		add(password);
		
		pass_Check = new JLabel("비번 확인 : ");
		pass_Check.setLocation(20,150);
		pass_Check.setSize(150,30);
		pass_Check.setFont(pass_Check.getFont().deriveFont(16.0f));
		pass_Check.setForeground(Color.WHITE);
		add(pass_Check);
		
		name = new JLabel("이름 : ");
		name.setLocation(20,200);
		name.setSize(150,30);
		name.setFont(name.getFont().deriveFont(16.0f));
		name.setForeground(Color.WHITE);
		add(name);
		
		nickName = new JLabel("별명 : ");
		nickName.setLocation(20,250);
		nickName.setSize(150,30);
		nickName.setFont(nickName.getFont().deriveFont(16.0f));
		nickName.setForeground(Color.WHITE);
		add(nickName);
		
		number = new JLabel("전화 번호 : ");
		minus1 = new JLabel("-");
		minus1.setLocation(190,300);
		minus1.setSize(150,30);
		minus1.setFont(number.getFont().deriveFont(16.0f));
		minus1.setForeground(Color.WHITE);
		minus2 = new JLabel("-");
		minus2.setLocation(275, 300);
		minus2.setSize(150,30);
		minus2.setFont(number.getFont().deriveFont(16.0f));
		minus2.setForeground(Color.WHITE);
		number.setLocation(20,300);
		number.setSize(150,30);
		number.setFont(number.getFont().deriveFont(16.0f));
		number.setForeground(Color.WHITE);
		add(number);
		add(minus1);
		add(minus2);
		
		e_mail = new JLabel("이메일 : ");
		e_symbol = new JLabel("@");
		e_mail.setLocation(20,350);
		e_mail.setSize(150,30);
		e_mail.setFont(e_mail.getFont().deriveFont(16.0f));
		e_mail.setForeground(Color.WHITE);
		e_symbol.setLocation(220,350);
		e_symbol.setSize(150,30);
		e_symbol.setFont(e_symbol.getFont().deriveFont(16.0f));
		e_symbol.setForeground(Color.WHITE);
		add(e_mail);
		add(e_symbol);
		
		idText=new JTextField();
		idText.setBounds(120,50,150,30);
		idText.setFont(new Font("Serif",Font.BOLD,11));
		idText.setFont(idText.getFont().deriveFont(15.0f));
		add(idText);
		
		passText=new JPasswordField();
		passText.setBounds(120,100,250,30);
		passText.setFont(new Font("Serif",Font.BOLD,11));
		passText.setFont(passText.getFont().deriveFont(15.0f));
		add(passText);
		
		pass_CheckText=new JPasswordField();
		pass_CheckText.setBounds(120,150,250,30);
		pass_CheckText.setFont(new Font("Serif",Font.BOLD,11));
		pass_CheckText.setFont(pass_CheckText.getFont().deriveFont(15.0f));
		add(pass_CheckText);
		
		nameText=new JTextField();
		nameText.setBounds(120,200,250,30); 
		nameText.setFont(new Font("Serif",Font.BOLD,11));
		nameText.setFont(nameText.getFont().deriveFont(15.0f));
		add(nameText);
		
		nickNameText=new JTextField();
		nickNameText.setBounds(120,250,250,30); 
		nickNameText.setFont(new Font("Serif",Font.BOLD,11));
		nickNameText.setFont(nickNameText.getFont().deriveFont(15.0f));
		add(nickNameText);
		
		numberText1=new JTextField();
		numberText1.setBounds(120,300,60,30); 
		numberText1.setFont(new Font("Serif",Font.BOLD,11));
		numberText1.setFont(numberText1.getFont().deriveFont(15.0f));
		add(numberText1);
		numberText2=new JTextField();
		numberText2.setBounds(205,300,60,30); 
		numberText2.setFont(new Font("Serif",Font.BOLD,11));
		numberText2.setFont(numberText2.getFont().deriveFont(15.0f));
		add(numberText2);
		numberText3=new JTextField();
		numberText3.setBounds(290,300,60,30); 
		numberText3.setFont(new Font("Serif",Font.BOLD,11));
		numberText3.setFont(numberText3.getFont().deriveFont(15.0f));
		add(numberText3);
		
		emailText=new JTextField();
		emailText.setBounds(120,350,90,30); 
		emailText.setFont(new Font("Serif",Font.BOLD,11));
		emailText.setFont(emailText.getFont().deriveFont(15.0f));
		add(emailText);
		
		e_choice = new JComboBox<String>(email);
		e_choice.setBounds(250,350,120,30);
		add(e_choice);
		
		accept = new JButton("확  인");
		accept.setLocation(50,425);
		accept.setSize(130,40);
		accept.setFont(new Font("Serif",Font.BOLD,11));
		accept.setFont(accept.getFont().deriveFont(17.0f));
		accept.setForeground(Color.DARK_GRAY);
		accept.setBackground(Color.LIGHT_GRAY);
		accept.addActionListener(sA1);
		add(accept);
		
		cancle = new JButton("취  소");
		cancle.setLocation(220,425);
		cancle.setSize(130,40);
		cancle.setFont(new Font("Serif",Font.BOLD,11));
		cancle.setFont(cancle.getFont().deriveFont(17.0f));
		cancle.setForeground(Color.DARK_GRAY);
		cancle.setBackground(Color.LIGHT_GRAY);
		cancle.addActionListener(sA1);
		add(cancle);
		
		temp = this;
		
		setVisible(true);
		
		
	}
	
	class signAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == accept) {
				
				if(over == true)
				{
					JOptionPane.showMessageDialog(content,"중복 검사를 완료해주세요.","Overlapping please",JOptionPane.WARNING_MESSAGE);
				}
				else if(!String.valueOf(passText.getText()).equals(String.valueOf(pass_CheckText.getText())))
				{
					JOptionPane.showMessageDialog(content,"비밀번호와 비밀번호 확인이 일치하지 않습니다.","Password error",JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(nameText.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(content,"이름을 입력해주세요.","Name error",JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(nickNameText.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(content,"닉네임을 입력해주세요.","Nickname error",JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(numberText1.getText()).equals("")||String.valueOf(numberText3.getText()).equals("")||String.valueOf(numberText2.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(content,"번호를 입력해주세요.","Number error",JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(emailText.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(content,"이메일을 입력해주세요.","E-mail error",JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						writeSignUp(loginFout);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					temp.dispose();
				}
			}
			else if(e.getSource()==cancle) {
				temp.dispose();
				
			}
			else if(e.getSource()==overCheck) {
				try {
				if(checkOver(idFin,String.valueOf(idText.getText()))==-1)
				{
					JOptionPane.showMessageDialog(content,"아이디가 중복 됩니다.","Overlapping error",JOptionPane.WARNING_MESSAGE);
					over = true;
				}
				else
				{
					JOptionPane.showMessageDialog(content,"아이디가 사용 가능합니다.","Not Overlapping",JOptionPane.INFORMATION_MESSAGE);
					over = false;
				}
				}
				catch(IOException e1){
					JOptionPane.showMessageDialog(content,"파일 오류","File error",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	int checkOver(BufferedReader idFin,String overId) throws IOException {
		String id=null;
		idFin =new BufferedReader( new FileReader("./Saves/login/idKey.txt"));
		while((id = idFin.readLine())!=null) {
			
			if(overId.equals(id))
			{
				idFin.close();
				return -1;
			}
		}
		
		idFin.close();
		return 0;
	}
	
	void writeSignUp(BufferedWriter loginFout) throws IOException
	{
		
		loginFout =new BufferedWriter( new FileWriter("./Saves/login/login.txt",true));
		PrintWriter loginFoutP = new PrintWriter(loginFout,true);
		
		Person signP = new Person();
		String num1,num2,num3,saves="";
		signP.id = String.valueOf(idText.getText());
		signP.pass = String.valueOf(passText.getText());
		signP.name = String.valueOf(nameText.getText());
		signP.nickName=String.valueOf(nickNameText.getText());
		num1 = String.valueOf(numberText1.getText());
		num2 = String.valueOf(numberText2.getText());
		num3 = String.valueOf(numberText3.getText());
			
		signP.number=signP.number.concat(num1);
		signP.number=signP.number.concat("-");
		signP.number=signP.number.concat(num2);
		signP.number=signP.number.concat("-");
		signP.number=signP.number.concat(num3);
		
		
		signP.e_mail = String.valueOf(emailText.getText());
		signP.e_mail =signP.e_mail.concat("@");
		signP.e_mail =signP.e_mail.concat(e_choice.getSelectedItem().toString());
		
		saves = saves.concat(signP.id);
		saves = saves.concat(" ");
		saves = saves.concat(signP.pass);
		saves = saves.concat(" ");
		saves = saves.concat(signP.name);
		saves = saves.concat(" ");
		saves = saves.concat(signP.nickName);
		saves = saves.concat(" ");
		saves = saves.concat(signP.number);
		saves = saves.concat(" ");
		saves = saves.concat(signP.e_mail);

		loginFoutP.write(saves+"\n");
		idKeyWrite(signP.id);
		
		loginFoutP.close();
		
		String path = "./Saves/picture/";
		path = path.concat(signP.id);
		File Folder = new File(path);
		
		if(!Folder.exists()) {
			Folder.mkdir();
		}
		
		path = path.concat("/favorite");
		Folder = new File(path);
		if(!Folder.exists()) {
			Folder.mkdir();
		}
	}
	
	void idKeyWrite(String id) throws IOException{
		BufferedWriter idFout =new BufferedWriter( new FileWriter("./Saves/login/idKey.txt",true));
		PrintWriter idFoutP = new PrintWriter(idFout,true);
		idFoutP.write(id+"\n");
		idFoutP.close();
	}

}


class Person{
	String id;
	String pass;
	String name;
	String nickName;
	String e_mail;
	String number="";
	
	int picNum=0;
	int favoriteNum=0;
	}

class postFrame extends JFrame{
	private Person user;
	private Container content; 
	private File dir, fileList[];
	private String fileName[],path,pathNum;
	private JScrollPane mainScroll;
	private JPanel uploadPan,menu,scrollPane,dayPane;
	private JButton allP,favorite,yearP,monthP,makeP,myInfo,exit,searchTag,searchDate,upload,picButton;
	private ActionListener pA1 = new postAction();
	private Image img;
	private ImageIcon imgPic;
	private postFrame temp;
	private Vector<JButton> tempButton = new Vector<JButton>();
	private Vector<String> tempPath = new Vector<String>();
	boolean isMaster;
	private JLabel kim,logo;
	
	postFrame(String str,boolean master){
		kim = new JLabel("Kim's photograph album");
		kim.setBounds(200,30,600,40);
		kim.setForeground(Color.WHITE);
		kim.setFont(new Font("Tempus Sans ITC",Font.BOLD,11));
		kim.setFont(kim.getFont().deriveFont(40.0f));
		add(kim);
		
		imgPic = new ImageIcon("./Saves/logo.png");
		img = imgPic.getImage();
		img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imgPic = new ImageIcon(img);
		logo = new JLabel(imgPic);
		logo.setBounds(50, 0 ,100,100);
		add(logo);
		
		isMaster = master;
		temp = this;
		path = "./Saves/picture";
		user = new Person();
		user = insertPerson(str);	
		path= path.concat("/"+user.id);
		pathNum = path;
		dir = new File(path);
		fileList = dir.listFiles();
		fileName=dir.list();
		
		int allPicNum=0,favoritePicNum=0;
		
		for(int i=0; i<fileList.length;i++) 
			if(fileName[i].endsWith("PNG")||fileName[i].endsWith("png")) 
				allPicNum++;
		user.picNum = allPicNum;
		
		pathNum = pathNum.concat("/favorite");
		dir = new File(pathNum);
		fileList = dir.listFiles();
		fileName=dir.list();
		
		for(int i=0; i<fileList.length;i++) 
			if(fileName[i].endsWith("PNG")||fileName[i].endsWith("png")) 
				favoritePicNum++;
		
		user.favoriteNum = favoritePicNum;
		
		setTitle("사진 앨범");
		setSize(918,900);
		if(master)	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		content = getContentPane();
		content.setBackground(Color.DARK_GRAY);
		content.setLayout(null);
	
		dir = new File(path);
		fileList = dir.listFiles();
		fileName=dir.list();
			
		allP = new JButton("모두 보기");
		allP.setBounds(0,0,199,80);
		allP.setForeground(Color.BLACK);
		allP.setBackground(Color.WHITE);
		allP.setFont(new Font("Serif",Font.BOLD,11));
		allP.setFont(allP.getFont().deriveFont(17.0f));
		allP.addActionListener(pA1);
		add(allP);

		favorite = new JButton("즐겨찾기");
		favorite.setBounds(0,80,199,80);
		favorite.setForeground(Color.BLACK);
		favorite.setBackground(Color.WHITE);
		favorite.setFont(new Font("Serif",Font.BOLD,11));
		favorite.setFont(favorite.getFont().deriveFont(17.0f));
		favorite.addActionListener(pA1);
		add(favorite);

		yearP = new JButton("년 단위 보기");
		yearP.setBounds(0,160,199,80);
		yearP.setForeground(Color.BLACK);
		yearP.setBackground(Color.WHITE);
		yearP.setFont(new Font("Serif",Font.BOLD,11));
		yearP.setFont(yearP.getFont().deriveFont(17.0f));
		yearP.addActionListener(pA1);
		add(yearP);

		monthP = new JButton("월 단위 보기");
		monthP.setBounds(0,240,199,80);
		monthP.setForeground(Color.BLACK);
		monthP.setBackground(Color.WHITE);
		monthP.setFont(new Font("Serif",Font.BOLD,11));
		monthP.setFont(monthP.getFont().deriveFont(17.0f));
		monthP.addActionListener(pA1);
		add(monthP);

		
		makeP = new JButton("다른 사용자 앨범 보기");
		makeP.setBounds(0,320,199,80);//
		makeP.setForeground(Color.BLACK);
		makeP.setBackground(Color.WHITE);
		makeP.setFont(new Font("Serif",Font.BOLD,11));
		makeP.setFont(makeP.getFont().deriveFont(16.0f));
		makeP.addActionListener(pA1);
		add(makeP);
	
		if(master) myInfo = new JButton("내 정보");
		else myInfo = new JButton("사용자 정보");
		
		myInfo.setBounds(0,595,199,80);
		myInfo.setForeground(Color.BLACK);
		myInfo.setBackground(Color.WHITE);
		myInfo.setFont(new Font("Serif",Font.BOLD,11));
		myInfo.setFont(myInfo.getFont().deriveFont(17.0f));
		myInfo.addActionListener(pA1);
		add(myInfo);
		
		exit = new JButton("나가기");
		exit.setBounds(0,675,199,80);
		exit.setForeground(Color.BLACK);
		exit.setBackground(Color.WHITE);
		exit.setFont(new Font("Serif",Font.BOLD,11));
		exit.setFont(exit.getFont().deriveFont(17.0f));
		exit.addActionListener(pA1);
		add(exit);

		menu = new JPanel();
		menu.setLayout(null);
		menu.setBounds(0, 100, 199, 800);
		menu.setForeground(Color.BLACK);
		menu.setBackground(Color.WHITE);
		menu.add(allP);
		menu.add(favorite);
		menu.add(yearP);
		menu.add(exit);
		menu.add(monthP);
		
		menu.add(makeP);
		menu.add(myInfo);

		add(menu);

		searchTag = new JButton("태그 검색");
		searchTag.setBounds(40,30,100,40);
		searchTag.setForeground(Color.WHITE);
		searchTag.setBackground(Color.DARK_GRAY);
		searchTag.setFont(new Font("Serif",Font.BOLD,11));
		searchTag.setFont(searchTag.getFont().deriveFont(14.0f));
		searchTag.addActionListener(pA1);
		add(searchTag);
		
		searchDate = new JButton("날짜 검색");
		searchDate.setBounds(160,30,100,40);
		searchDate.setForeground(Color.WHITE);
		searchDate.setBackground(Color.DARK_GRAY);
		searchDate.setFont(new Font("Serif",Font.BOLD,11));
		searchDate.setFont(searchDate.getFont().deriveFont(14.0f));
		searchDate.addActionListener(pA1);
		add(searchDate);
		
		if(master==true) {
			upload = new JButton("업로드");
			upload.setBounds(600,30,100,40);
			upload.setForeground(Color.WHITE);
			upload.setBackground(Color.DARK_GRAY);
			upload.setFont(new Font("Serif",Font.BOLD,11));
			upload.setFont(upload.getFont().deriveFont(14.0f));
			upload.addActionListener(pA1);
			add(upload);
		}
		uploadPan = new JPanel();
		uploadPan.setLayout(null);
		uploadPan.setBounds(180, 100, 720, 100);
		uploadPan.setForeground(Color.BLACK);
		uploadPan.setBackground(Color.LIGHT_GRAY);
		uploadPan.add(searchTag);
		uploadPan.add(searchDate);
		if(master==true)	uploadPan.add(upload);
		add(uploadPan);
		
		allOpen(1,user,null);
		setVisible(true);
		

		
	}
	
	class postAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==upload) {
				uploadFrame up= new uploadFrame(user);
			}
			else if(e.getSource()==allP)
			{
				content.remove(mainScroll);
				allOpen(1,user,null);
				
			}
			else if(e.getSource()==favorite)
			{
				content.remove(mainScroll);
				allOpen(4,user,null);
			}
			else if(e.getSource()==yearP)
			{
				content.remove(mainScroll);
				allOpen(2,user,null);
			}
			else if(e.getSource()==monthP)
			{
				content.remove(mainScroll);
				allOpen(3,user,null);
			}
			else if(e.getSource()==makeP)
			{
				openOthers();
			}
			else if(e.getSource()==myInfo)
			{
				JOptionPane.showMessageDialog(content,"아이디 : "+user.id+"\n이름 : "+user.name+"\n별명 : "+user.nickName+"\n전화 번호 : "+user.number+"\n이메일 : "+user.e_mail+"\n전체 사진 수 : "+user.picNum+"\n즐겨찾기 수 : "+user.favoriteNum,"내 정보",JOptionPane.INFORMATION_MESSAGE);
			}
			else if(e.getSource()==exit)
			{
				temp.dispose();
			}
			else if(e.getSource()==searchTag)
			{
				String tag = (String) JOptionPane.showInputDialog(temp, "태그를 입력하시오.", "Enter Tag", JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(tag!=null) {
				content.remove(mainScroll);
				allOpen(6,user,tag);
				}
			}
			else if(e.getSource()==searchDate)
			{
				String date = (String) JOptionPane.showInputDialog(temp, "날짜를 입력하시오.(yyyy-mm-dd)\nex)2020-12-06", "날짜 검색 ", JOptionPane.PLAIN_MESSAGE, null, null, null);
				boolean exDate = false;
				
				dir = new File(path);
				fileList = dir.listFiles();
				fileName=dir.list();
				
				for(int i=0;i<fileList.length;i++)
					if(fileName[i].startsWith (date))
					{
						exDate = true;
						break;
					}
				if(exDate) {
					content.remove(mainScroll);
					allOpen(5,user,date);
				}
				else {
					JOptionPane.showMessageDialog(content,"해당 날짜에 사진이 없거나 잘못된 입력입니다.","Date error",JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
			for(int i=0; i<tempButton.size();i++) {
				if(e.getSource()==tempButton.get(i)) {
					pictureFrame picF = new pictureFrame(tempPath.get(i),isMaster);
				}
			}

			
		}
		
	}
	
	void openOthers() {
		
		try {
			BufferedReader idFin = new BufferedReader( new FileReader("./Saves/login/login.txt"));
			String ID="",idText="",loginID [];
			boolean exist=false;
			
			
			idText = (String) JOptionPane.showInputDialog(this, "아이디를 입력하시오.", "다른 사용자 앨범 보기 ", JOptionPane.PLAIN_MESSAGE, null, null, null);
			while((ID = idFin.readLine())!=null) {
				loginID = ID.split(" ");
				if(loginID[0].equals(idText))
				{
					exist = true;
					break;
				}
			}

		if(exist) {
			postFrame otherFrame = new postFrame(ID,false);
		}
		else {
			JOptionPane.showMessageDialog(content,"아이디가 없습니다.","ID error",JOptionPane.WARNING_MESSAGE);
		}
		idFin.close();
		}
		catch(IOException e) {
			e.printStackTrace();
	}
	
}
	
	void allOpen(int num,Person user,String userDate) { //1. all, 2. year, 3. month, 4. favorite, 5. date, 6. tag
		
		String picName;
		int count=0,dayY=0;
		boolean month=false,year =false;
		
		dir = new File(path);
		fileList = dir.listFiles();
		fileName=dir.list();
		
		scrollPane = new JPanel();
		scrollPane.setLayout(null);
		tempButton.removeAllElements();
		tempPath.removeAllElements();
		
		switch(num) {
		
			case 1:
				scrollPane.setPreferredSize(new Dimension(680,((fileList.length)/3)*228));
				for(int i=0; i<fileList.length;i++) {
					if(fileName[i].endsWith("PNG")||fileName[i].endsWith("png")) {
						picName = "./Saves/picture";
						picName = picName.concat("/"+user.id+"/"+fileName[i]);
						imgPic = new ImageIcon(picName);
						img = imgPic.getImage();
						img = img.getScaledInstance(228, 228, Image.SCALE_SMOOTH);
						imgPic = new ImageIcon(img);
						picButton = new JButton(imgPic);
						picButton.setBounds((count%3)*228,((count/3)*228),228,228);
						picButton.addActionListener(pA1);
						tempButton.add(picButton);
						tempPath.add(picName);
						
						count++;
						scrollPane.add(picButton);
					}
			}
			break;
			
			case 2:
				for(int j=0;j<5;j++) {
					for(int i=0; i<fileList.length;i++) {
						if((fileName[i].endsWith("PNG")||fileName[i].endsWith("png"))&&(fileName[i].startsWith(Integer.toString(2018+j)))){
							year=true;
							break;
						}
					}
					if(year==true)
					{
					count=0;
					JLabel yearL = new JLabel(Integer.toString(2018+j)+"년");
					yearL.setForeground(Color.WHITE);
					yearL.setFont(new Font("Serif",Font.BOLD,11));
					yearL.setFont(yearL.getFont().deriveFont(18.0f));
					yearL.setBounds(0,5,100 , 50);
					add(yearL);	
					dayPane = new JPanel();
					dayPane.setBackground(Color.DARK_GRAY);
					dayPane.setBounds(0,dayY+4 ,228*3 , 57);
					dayPane.add(yearL);
					add(dayPane);
					scrollPane.add(dayPane);
	
					
					for(int i=0; i<fileList.length;i++) {
						if((fileName[i].endsWith("PNG")||fileName[i].endsWith("png"))&&(fileName[i].startsWith(Integer.toString(2018+j))))
						{
							picName = "./Saves/picture";
							picName = picName.concat("/"+user.id+"/"+fileName[i]);
							imgPic = new ImageIcon(picName);
							img = imgPic.getImage();
							img = img.getScaledInstance(228, 228, Image.SCALE_SMOOTH);
							imgPic = new ImageIcon(img);
							picButton = new JButton(imgPic);
							picButton.setBounds((count%3)*228,((count/3)*228)+dayY+60,228,228);
							picButton.addActionListener(pA1);
							tempButton.add(picButton);
							tempPath.add(picName);
							count++;
							scrollPane.add(picButton);
						}
					}
					year = false;
					dayY += (((count-1)/3)+1)*228+55;
				}
				}
				scrollPane.setPreferredSize(new Dimension(680,dayY));
				
			break;
			case 3:
				for(int j=0;j<5;j++) {
					for(int m=0;m<12;m++)
					{
						for(int i=0; i<fileList.length;i++) {
	
							if(m<9) {
								if((fileName[i].endsWith("PNG")||fileName[i].endsWith("png"))&&(fileName[i].startsWith(Integer.toString(2018+j)+"-0"+Integer.toString(m+1))))
								{
									month = true;
								}
							}
							else if(m>=9){
								if((fileName[i].endsWith("PNG")||fileName[i].endsWith("png"))&&(fileName[i].startsWith(Integer.toString(2018+j)+"-"+Integer.toString(m+1)))) {
									month = true;
								}
							}	
						}
						
						if(month) {
							count=0;
							JLabel yearL = new JLabel(Integer.toString(2018+j)+"년 "+Integer.toString(m+1)+"월");
							yearL.setForeground(Color.WHITE);
							yearL.setFont(new Font("Serif",Font.BOLD,11));
							yearL.setFont(yearL.getFont().deriveFont(18.0f));
							yearL.setBounds(0,5,100 , 50);
							add(yearL);	
							dayPane = new JPanel();
							dayPane.setBackground(Color.DARK_GRAY);
							dayPane.setBounds(0,dayY+4 ,228*3 , 57);
							dayPane.add(yearL);
							add(dayPane);
							scrollPane.add(dayPane);
							for(int i=0; i<fileList.length;i++) {
								if(m<9) {
									if((fileName[i].endsWith("PNG")||fileName[i].endsWith("png"))&&(fileName[i].startsWith(Integer.toString(2018+j)+"-0"+Integer.toString(m+1))))
									{
										picName = "./Saves/picture";
										picName = picName.concat("/"+user.id+"/"+fileName[i]);
										imgPic = new ImageIcon(picName);
										img = imgPic.getImage();
										img = img.getScaledInstance(228, 228, Image.SCALE_SMOOTH);
										imgPic = new ImageIcon(img);
										picButton = new JButton(imgPic);
										picButton.setBounds((count%3)*228,((count/3)*228)+dayY+60,228,228);
										tempPath.add(picName);
										picButton.addActionListener(pA1);
										tempButton.add(picButton);
										count++;
										scrollPane.add(picButton);

									}
								}
									else {
										if((fileName[i].endsWith("PNG")||fileName[i].endsWith("png"))&&(fileName[i].startsWith(Integer.toString(2018+j)+"-"+Integer.toString(m+1))))
										{
											picName = "./Saves/picture";
											picName = picName.concat("/"+user.id+"/"+fileName[i]);
											imgPic = new ImageIcon(picName);
											img = imgPic.getImage();
											img = img.getScaledInstance(228, 228, Image.SCALE_SMOOTH);
											imgPic = new ImageIcon(img);
											picButton = new JButton(imgPic);
											picButton.setBounds((count%3)*228,((count/3)*228)+dayY+60,228,228);
											count++;
											tempPath.add(picName);
											picButton.addActionListener(pA1);
											tempButton.add(picButton);
											scrollPane.add(picButton);

										}
									}
	
								
							}
							month = false;
							dayY += (((count-1)/3)+1)*228+55;
						}
					}	
				}
				
				scrollPane.setPreferredSize(new Dimension(680,dayY));
				break;
				
			case 4:
				String pathF = "./Saves/picture/";
				pathF = pathF.concat(user.id+"/favorite");
				File Folder = new File(pathF);
				
				if(!Folder.exists()) {
					Folder.mkdir();
				}
				
				dir = new File(pathF);
				fileList = dir.listFiles();
				fileName=dir.list();
				
				scrollPane.setPreferredSize(new Dimension(680,((fileList.length-1)/3+1)*228));
				for(int i=0; i<fileList.length;i++) {
					if(fileName[i].endsWith("PNG")||fileName[i].endsWith("png")) {
						picName = pathF;
						picName = picName.concat("/"+fileName[i]);
						imgPic = new ImageIcon(picName);
						img = imgPic.getImage();
						img = img.getScaledInstance(228, 228, Image.SCALE_SMOOTH);
						imgPic = new ImageIcon(img);
						picButton = new JButton(imgPic);
						picButton.setBounds((count%3)*228,((count/3)*228),228,228);
						picButton.addActionListener(pA1);
						tempButton.add(picButton);
						tempPath.add(picName);
						count++;
						scrollPane.add(picButton);
					}
			}
				
				
				break;
				
			case 5:
				count =0;
				scrollPane.setPreferredSize(new Dimension(680,((fileList.length)/3)*228));
				for(int i=0; i<fileList.length;i++) {
					if((fileName[i].endsWith("PNG")||fileName[i].endsWith("png"))&&fileName[i].startsWith(userDate)) {
						picName = "./Saves/picture";
						picName = picName.concat("/"+user.id+"/"+fileName[i]);
						imgPic = new ImageIcon(picName);
						img = imgPic.getImage();
						img = img.getScaledInstance(228, 228, Image.SCALE_SMOOTH);
						imgPic = new ImageIcon(img);
						picButton = new JButton(imgPic);
						picButton.setBounds((count%3)*228,((count/3)*228),228,228);
						picButton.addActionListener(pA1);
						tempButton.add(picButton);
						tempPath.add(picName);
						count++;
						scrollPane.add(picButton);
					}
			}
				break;
			case 6:
				String pathF2 = "./Saves/picture/";
				pathF2 = pathF2.concat(user.id+"/"+userDate);
				File Folder2 = new File(pathF2);
				
				if(!Folder2.exists()) {
					JOptionPane.showMessageDialog(content,"해당 태그가 없습니다.","No tag error",JOptionPane.WARNING_MESSAGE);
				}
				else {
				dir = new File(pathF2);
				fileList = dir.listFiles();
				fileName=dir.list();
				
				scrollPane.setPreferredSize(new Dimension(680,((fileList.length-1)/3+1)*228));
				for(int i=0; i<fileList.length;i++) {
					if(fileName[i].endsWith("PNG")||fileName[i].endsWith("png")) {
						picName = pathF2;
						picName = picName.concat("/"+fileName[i]);
						imgPic = new ImageIcon(picName);
						img = imgPic.getImage();
						img = img.getScaledInstance(228, 228, Image.SCALE_SMOOTH);
						imgPic = new ImageIcon(img);
						picButton = new JButton(imgPic);
						picButton.setBounds((count%3)*228,((count/3)*228),228,228);
						picButton.addActionListener(pA1);
						tempButton.add(picButton);
						tempPath.add(picName);
						count++;
						scrollPane.add(picButton);
					}
				}
				break;
				
				}
		}
		add(scrollPane);
		mainScroll = new JScrollPane(scrollPane);
		mainScroll.setBounds(199, 200, 701, 653);
		mainScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		add(mainScroll);
		
		
		
		setVisible(true);
	}
	
	Person insertPerson(String str)
	{
		String [] strTemp = str.split(" ");
		Person user = new Person();
		user.id = strTemp[0];
		user.pass = strTemp[1];
		user.name = strTemp[2];
		user.nickName = strTemp[3];
		user.number = strTemp[4];
		user.e_mail = strTemp[5];
	
	
		return user;
	}
	
}

class pictureFrame extends JFrame{
	private Image img;
	private ImageIcon imgPic;
	private Container content;
	private JLabel picLabel;
	private JPanel menuPane,picturePane;
	private JButton PicTag,PicSpin,favorite,delete,exit;
	private pictureFrame temp;
	private String pathA,answer[]={"180도", "90도", "취소"};;
	private pictureAction picA1 = new pictureAction();
	
	pictureFrame(String path,boolean master){
		pathA = path;
		temp = this;
		setTitle("사진 보기");
		setSize(800,800);
		
		setLocationRelativeTo(null);
		
		content = getContentPane();
		content.setBackground(Color.DARK_GRAY);
		content.setLayout(null);
		
		imgPic = new ImageIcon(path);
		img = imgPic.getImage();
		img = img.getScaledInstance(650, 650, Image.SCALE_SMOOTH);
		imgPic = new ImageIcon(img);
		picturePane = new JPanel();
		picturePane.setBounds(70,70,650,650);
		picturePane.setLayout(null);
		picLabel = new JLabel(imgPic);
		picLabel.setBounds(0,0,650,650);
		picturePane.add(picLabel);
		content.add(picturePane);
		
		if(master) {
			PicTag = new JButton("태그 설정");
			PicTag.setBounds(25,10,130,30);
			PicTag.setFont(new Font("Serif",Font.BOLD,11));
			PicTag.setFont(PicTag.getFont().deriveFont(16.0f));
			PicTag.setBackground(Color.WHITE);
			PicTag.setForeground(Color.BLACK);
			PicTag.addActionListener(picA1);
			add(PicTag);
			
			PicSpin = new JButton("사진 회전");
			PicSpin.setBounds(175,10,130,30);
			PicSpin.setFont(new Font("Serif",Font.BOLD,11));
			PicSpin.setFont(PicSpin.getFont().deriveFont(16.0f));
			PicSpin.setBackground(Color.WHITE);
			PicSpin.setForeground(Color.BLACK);
			PicSpin.addActionListener(picA1);
			add(PicSpin);
			
			favorite = new JButton("즐겨찾기");
			favorite.setBounds(325,10,130,30);
			favorite.setFont(new Font("Serif",Font.BOLD,11));
			favorite.setFont(favorite.getFont().deriveFont(16.0f));
			favorite.setBackground(Color.WHITE);
			favorite.setForeground(Color.BLACK);
			favorite.addActionListener(picA1);
			add(favorite);
			
			delete = new JButton("사진 삭제");
			delete.setBounds(475,10,130,30);
			delete.setFont(new Font("Serif",Font.BOLD,11));
			delete.setFont(delete.getFont().deriveFont(16.0f));
			delete.setBackground(Color.WHITE);
			delete.setForeground(Color.BLACK);
			delete.addActionListener(picA1);
			add(delete);
		}
		
		exit = new JButton("나가기");
		exit.setBounds(625,10,130,30);
		exit.setFont(new Font("Serif",Font.BOLD,11));
		exit.setFont(exit.getFont().deriveFont(16.0f));
		exit.setBackground(Color.WHITE);
		exit.setForeground(Color.BLACK);
		exit.addActionListener(picA1);
		add(exit);
		
		menuPane = new JPanel();
		menuPane.setBounds(0, 0, 800, 50);
		menuPane.setBackground(Color.BLACK);
		menuPane.setLayout(null);
		
		if(master) {
			menuPane.add(PicTag);
			menuPane.add(PicSpin);
			menuPane.add(favorite);
			menuPane.add(delete);
		}
		menuPane.add(exit);
		add(menuPane);
		setVisible(true);
	}
	
	class pictureAction implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==PicTag) {
				String tag = (String) JOptionPane.showInputDialog(temp, "태그를 입력하시오.", "Enter Tag", JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(!(tag==null))
					getTag(tag);
			
			}
			else if(e.getSource()==PicSpin) {

				int ans = JOptionPane.showOptionDialog(temp, "몇 도 회전하시겠습니까?", "Spin Picture", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null);
				content.remove(picturePane);
				spinPicture(ans);

			}
			else if(e.getSource()==favorite) {
				getTag("favorite");
			}
			else if(e.getSource()==delete) {
				File file = new File(pathA);
				file.delete();
				JOptionPane.showMessageDialog(content,"파일을 삭제했습니다.","File delete",JOptionPane.ERROR_MESSAGE);
				temp.dispose();
	
			}
			else if(e.getSource()==exit) {
				temp.dispose();
			}
			
		}
	}
	void getTag(String tag) {
		String basePath [],bPath="";
		basePath= pathA.split("/");
		bPath = bPath.concat(basePath[0]+"/"+basePath[1]+"/"+basePath[2]+"/"+basePath[3]+"/"+tag);
		File Folder = new File(bPath);
		
		if(!Folder.exists()) {
			Folder.mkdir();
		}
		
		
		File saveDir = new File(bPath);
		File[] saveList = saveDir.listFiles();
		String[] saveName = saveDir.list();
		
		bPath= bPath.concat("/"+Integer.toString(saveList.length)+".png");

		File file = new File(pathA);
		
		try {
			BufferedImage picImg = ImageIO.read(file);
			File file2 = new File(bPath);
			ImageIO.write(picImg,"png",file2);
		} catch (IOException e1) {
			System.out.println(e1);
		}
		
	}
	
	
	void spinPicture(int ans){

	    File orgFile = new File(pathA);
	    BufferedImage oldImage;
	    int rotate;
		try {
			oldImage = ImageIO.read(orgFile);

	    BufferedImage newImage = null;
	 
	    if(ans==0) {
	        newImage = new BufferedImage(oldImage.getWidth(),oldImage.getHeight(), oldImage.getType());
	        rotate = 180;
	    }
	    else {
	        newImage = new BufferedImage(oldImage.getHeight(),oldImage.getWidth(), oldImage.getType());
	        rotate=90;
	    }
	 
	    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
	 
	    graphics.rotate(Math.toRadians(rotate), newImage.getWidth() / 2, newImage.getHeight() / 2);
	 
	    if(ans==1) {
	        graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2, (newImage.getHeight() - oldImage.getHeight()) / 2);
	    }
	 
	    graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);
	 
	    ImageIO.write(newImage, "png", new FileOutputStream(new File(pathA)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		picturePane = new JPanel();
		
		imgPic = new ImageIcon(pathA);
		img = imgPic.getImage();
		img = img.getScaledInstance(650, 650, Image.SCALE_SMOOTH);
		imgPic = new ImageIcon(img);
		picLabel = new JLabel(imgPic);
		picturePane.setBounds(70,70,650,650);
		picLabel.setBounds(0,0,650,650);
		picturePane.add(picLabel);
		content.add(picturePane);
		temp.setVisible(true);
	 
	}
}

class uploadFrame extends JFrame{
	private String path = System.getProperty ( "user.home" ),picPath,saveFileName;
	private File dir, saveDir;
	private File  fileList [],saveList [];
	private String[] fileName, saveName,answer= {"예","아니오"};
	private JScrollPane pathScroll;
	private JPanel menu,temp2,temp3=new JPanel();
	private JButton back,exit;
	private Vector<JButton> temp = new Vector<JButton>();
	private Container content; 
	private JLabel folderL,folderEmo;
	private Image img;
	private ImageIcon imgIcon;
	private uploadAction upA1 = new uploadAction();
	private Vector<Integer> num = new Vector<Integer>();
	private uploadFrame me = this;
	private int dep =0;
	private Vector<String> deep = new Vector<String>();
	private BufferedImage picImg =null;
	private Date today = new Date();
	private SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
	private Person user;
	
	uploadFrame(Person user){
		
		this.user = user;
		path = path.concat("/Desktop");
		
		dir = new File(path);
		fileList = dir.listFiles();
		fileName=dir.list();
		
		content = getContentPane();
		content.setLayout(null);
		setTitle("사진 업로드");
		setSize(417,400);
		setLocationRelativeTo(null);
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) /2 +450, (screenSize.height - frameSize.height) /2-100);
		
		exit = new JButton("나가기");
		exit.setBounds(240,5,100,40);
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.DARK_GRAY);
		exit.setFont(new Font("Serif",Font.BOLD,11));
		exit.setFont(exit.getFont().deriveFont(14.0f));
		exit.addActionListener(upA1);
		add(exit);
		
		back = new JButton(" ← ");
		back.setBounds(60,5,100,40);
		back.setForeground(Color.WHITE);
		back.setBackground(Color.DARK_GRAY);
		back.setFont(new Font("Serif",Font.BOLD,11));
		back.setFont(back.getFont().deriveFont(14.0f));
		back.addActionListener(upA1);
		add(back);
		
		menu = new JPanel();
		menu.setBounds(0, 0, 400, 50);
		menu.setFont(new Font("Serif",Font.BOLD,11));
		menu.setFont(menu.getFont().deriveFont(14.0f));
		menu.setBackground(Color.LIGHT_GRAY);
		menu.add(back);
		menu.add(exit);
		menu.setLayout(null);
		add(menu);
		
		viewFile(path);
		

		setVisible(true);
	}
	
	class uploadAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<temp.size();i++)
			{
				if(e.getSource()==temp.get(i)){
					if(fileList[num.get(i)].isDirectory())
					{
						deep.add(path);
						dep++;
						path = path.concat("/");
						path = path.concat(fileName[num.get(i)]);
						dir = new File(path);
						fileList = dir.listFiles();
						fileName=dir.list();
						content.remove(pathScroll);
						
						viewFile(path);
					}
					else {
						int ans = JOptionPane.showOptionDialog(me, "사진을 업로드 하시겠습니까?", "Upload Check", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, answer[0]);
						int countSave=1;
						
						if(ans==0) {
							saveFileName="./Saves/picture/"+user.id;
							saveDir = new File(saveFileName);
							saveList = saveDir.listFiles();
							saveName = saveDir.list();
							saveFileName= saveFileName.concat("/"+ymd.format(today));
							
							for(int j=0; j<saveList.length;j++)
							{
								if(saveName[j].startsWith(ymd.format(today))) {
									countSave++;
								}
							}
							
							
							saveFileName = saveFileName.concat("_"+countSave+".png");
							picPath = path;
							picPath = picPath.concat("/"+fileName[num.get(i)]);
							
							File file = new File(picPath);
							try {
								picImg = ImageIO.read(file);
								File file2 = new File(saveFileName);
								ImageIO.write(picImg,"png",file2);
							} catch (IOException e1) {
								System.out.println(e1);
							}
						}
						
						
					}
				}
			}
			
			if(e.getSource()==exit)
			{
				me.dispose();
			}
			
			else if(e.getSource()==back) {
				if(dep == 0)
					JOptionPane.showMessageDialog(content,"더 이상 뒤로 갈 수 없습니다.","Can't back",JOptionPane.WARNING_MESSAGE);
				else
				{
					path = deep.get(--dep);
					dir = new File(path);
					fileList = dir.listFiles();
					fileName=dir.list();
					content.remove(pathScroll);
					viewFile(path);
				}
			}
		}
		
	}
	
	
	void viewFile(String path)
	{
		int count=0,length=0;
		
		
		for(int i=0; i<fileList.length;i++) 
			if(fileName[i].endsWith("png")||fileName[i].endsWith("PNG")||!fileName[i].contains(".")) 
				length++;
		
		temp3 = new JPanel();
		temp3.setLayout(null);
		temp3.setPreferredSize(new Dimension(380,length*50));
		temp3.setBounds(0,0,385,length*50);

		temp.removeAllElements();
		num.removeAllElements();
		
		for(int i=0; i<fileList.length;i++) {
			if(fileName[i].endsWith("PNG")||fileName[i].endsWith("png")||fileList[i].isDirectory()) {
				temp2 = new JPanel();
				temp2.setBounds(0, (count*50), 385, 50);
				temp2.setBackground(Color.DARK_GRAY);
				temp2.setLayout(null);
				if(fileName[i].endsWith("PNG")||fileName[i].endsWith("png"))
				{
					folderL = new JLabel("사진");
					folderL.setBounds(60,0 , 40,50);
					imgIcon = new ImageIcon("./Saves/png.png");
					img = imgIcon.getImage();
					img = img.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
					imgIcon = new ImageIcon(img);
					folderEmo = new JLabel(imgIcon);
					folderEmo.setBounds(0,0,50,50);

				}
				else
				{
					folderL = new JLabel("폴더");
					folderL.setBounds(60,0 , 40,50);
					imgIcon = new ImageIcon("./Saves/folder.png");
					img = imgIcon.getImage();
					img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
					imgIcon = new ImageIcon(img);
					folderEmo = new JLabel(imgIcon);
					folderEmo.setBounds(0,0,50,50);
				}
				
				add(folderEmo);
				folderL.setForeground(Color.WHITE);
				folderL.setFont(new Font("Serif",Font.BOLD,12));
				folderL.setFont(folderL.getFont().deriveFont(14.0f));
				add(folderL);
				num.add(i);
				temp.add(new JButton(fileName[i]));				
				temp.get(count).setBounds(100,0 , 285,50);
				temp.get(count).setFont(new Font("Serif",Font.BOLD,12));
				temp.get(count).setFont(temp.get(0).getFont().deriveFont(14.0f));
				temp.get(count).addActionListener(upA1);
				add(temp.get(count));
				
				temp2.add(folderL);
				temp2.add(temp.get(count));	
				temp2.add(folderEmo);
				add(temp2);
				temp3.add(temp2);
				count+=1;
			}
	}	

		add(temp3);
		pathScroll = new JScrollPane(temp3);
		pathScroll.setBounds(0,50,400,305);
		pathScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		add(pathScroll);

		setVisible(true);
		
	}
}



public class guestBook{
	public static void main(String argv[])
	{
		
		LoginFrame lo = new LoginFrame();
	}
	
}
