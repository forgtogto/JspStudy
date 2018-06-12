package kr.jkseo.crud.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.jkseo.crud.command.BCommand;
import kr.jkseo.crud.command.BContentCommand;
import kr.jkseo.crud.command.BDeleteCommand;
import kr.jkseo.crud.command.BListCommand;
import kr.jkseo.crud.command.BModifyCommand;
import kr.jkseo.crud.command.BReplyCommand;
import kr.jkseo.crud.command.BReplyViewCommand;
import kr.jkseo.crud.command.BWriteCommand;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("actionDo");
		
		request.setCharacterEncoding("EUC-KR");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		if(com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
			
		} else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "list.do";
			
		} else if(com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
			
		} else if(com.equals("/content_view.do")){
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
			
		} else if(com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			viewPage = "list.do";
			
		} else if(com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do";
			
		} else if(com.equals("/reply_view.do")) {
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage = "reply_view.jsp";
			
		} else if(com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
