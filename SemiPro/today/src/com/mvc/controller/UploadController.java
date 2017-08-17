package com.mvc.controller;


import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.service.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



@WebServlet("/upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request,response);
	}
	
	protected void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request.setCharacterEncoding("UTF-8");
		
		BoardService service = new BoardService();
		
		
		//용량제한 10MB
		int maxSize = 1024*1024*10;
		
		//웹서버 메인 주소 알아내기
		String root = request.getSession().getServletContext().getRealPath("/");
		//저장 경로 설정
		System.out.println(root+"저장경로");
		String savePath = root+"upload";
		//폴더가 없을 경우
		File dir = new File(savePath);
		if(!dir.isDirectory()){
			System.out.println("폴더 없음, 생성 시작");
			dir.mkdir();
		}
				
		String oldFileName = "";//원래 파일 이름
		String newFileName="";//변경할 파일 이름
		
		
		//request -> multipartRequest
		//request객체, 저장경로, 파일사이즈, 인코딩타입, 파일명 변경 정책
		MultipartRequest multi = new MultipartRequest(request, savePath, 
				maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String area = multi.getParameter("area");//가져올 지역
		String title = multi.getParameter("title");//가져올 제목
		String tag = multi.getParameter("tag");//가져올 태그
		double latit = Double.parseDouble(multi.getParameter("latit"));//위도
		double longit = Double.parseDouble(multi.getParameter("longit"));//경도
		String id = multi.getParameter("loginID");//사용자 아이디
		
		
		int idx =service.board(id,title,tag,latit,longit,area);//문단 idx를 가져오기
		
		int cnt = Integer.parseInt(multi.getParameter("cnt"));//더보기 한 수
		
		
		int success = 0;
		int textidx = 0;
		for(int i=0;i<cnt;i++){//cnt 는 더보기한 숫자
			String text = multi.getParameter("text"+i);//text cnt한 수만큼
			textidx = service.text(text,idx);//글에대한 idx
			
			System.out.println(multi.getFilesystemName("uploadFile"+i));
			System.out.println(textidx+"텍스트 idx");
			System.out.println(idx+"보드 idx");
			
			//실제 파일명 뽑아 내기
			oldFileName = multi.getFilesystemName("uploadFile"+i);//uploadFile cnt한 수만큼
			//System.out.println(oldFileName);
			
			long currTime = System.currentTimeMillis();
			SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			//변경할 파일명(20170511112058)
			newFileName = simDf.format(new Date(currTime))
					+"."+oldFileName.substring(oldFileName.lastIndexOf(".")+1);
			System.out.println(newFileName);
			//System.out.println(newFileName);
			
			//파일객체 생성
			//업로드 파일 객체
			File oldFile = new File(savePath+"/"+oldFileName);
			//저장될 파일 객체
			File newFile = new File(savePath+"/"+newFileName);
			
			//파일명 변경
			oldFile.renameTo(newFile);	
			
			//System.out.println(oldFile);
			//System.out.println(newFile);
			success += service.upload(oldFileName,newFileName,idx,textidx);//업로드할때마다 1이 뜨므로 그걸더함
			System.out.println(success+"성공여부");
		}	
		
		
		
		
		
		String msg = "글쓰기에 실패";
		String page = "write.jsp";
		if(success==cnt){//success 참조해서 더보기 한 숫자와 같은지 비교
			msg = "글쓰기에 성공";
			page = "/area?area="+area;	
			service.feel(idx);
			//service.reply(idx);
		}
		request.setAttribute("result", msg);
		RequestDispatcher dis = request.getRequestDispatcher(page);
		dis.forward(request, response);
		
		
		
		
	}
}
