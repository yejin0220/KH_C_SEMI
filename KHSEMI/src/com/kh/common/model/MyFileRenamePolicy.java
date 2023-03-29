package com.kh.common.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {
	
		 /*
		    * �ݵ�� �̿ϼ��� rename�޼ҵ带 �������̵��ؼ� �����ؾ� ��
		    * ������ ������ ���� �޾Ƽ� ���ϸ� �����۾� ��, ������ ������ ��ȯ���ִ� �޼ҵ�
		    */
		   @Override
		   public File rename(File originFile) {
		      
		      // �������ϸ�("aaa.jpg")
		      String originName = originFile.getName();
		      
		      // �������ϸ� : ���Ͼ��ε�ð�(����Ͻú���)+5�ڸ�������(10000-99999) => �ִ��� �̸��� ��ġ�� �ʰ�
		      // Ȯ���� : �������ϸ��� �״�� ���� ����
		      
		      // ������        ->          ������
		      // aaa.jpg     ->     2023022011265012345.jpg
		      
		      // 1. ���� ���ε�� �ð�(����Ͻú���) => String currentTime;
		      String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		      // 2. 5�ڸ� ������ => int ranNum;
		      int ranNum = (int)(Math.random()*90000+10000); // 10000<= random < 100000
		      // 3. �������� Ȯ���� => String ext
		      /*
		       * ���ϸ� �߰��� .�� ���� ��찡 �ֱ� ������ �������ϸ��� ���� ������ .�� �ε����� ��������
		       * ���ϸ�� Ȯ���ڸ� ���� ����
		       */
		      String ext = originName.substring(originName.lastIndexOf(".")); // .jpg, .png
		      
		      String changeName = currentTime+ranNum+ext; // 2023022011265012345.jpg
		      
		      return new File(originFile.getParent() , changeName); // ���������� ������ ���ϸ����� ������Ѽ� ��ü�� ��ȯ����
		   }
	
	
	
	
	
	
}
