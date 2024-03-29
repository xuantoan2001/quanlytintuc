package com.quanlytintuc.controller.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quanlytintuc.constant.SystemConstant;
import com.quanlytintuc.model.BaiViet;
import com.quanlytintuc.service.IBaiVietService;
import com.quanlytintuc.service.IBinhLuanService;
import com.quanlytintuc.service.IChuDeService;

@WebServlet(urlPatterns = {"/bai-viet"})
public class BaiVietController extends HttpServlet{

	/**
	 * 
	 */
	@Inject
	private IBaiVietService baivietService;
	
	@Inject
	private IChuDeService chudeService;
	
	@Inject
	private IBinhLuanService binhluanService;
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaiViet model = new BaiViet();
		
		Long cd =  Long.parseLong(request.getParameter("mabaiviet"));
		model.setListData(baivietService.findNew());
		Long mbv = (long) cd;
		request.setAttribute("baivietmoi", model);
		request.setAttribute("dschude",chudeService.findAll());
		request.setAttribute(SystemConstant.MODEL, model);
		request.setAttribute("commentList",binhluanService.findAll(cd));
		request.setAttribute("baiviet", baivietService.findOne(mbv));
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		request.setAttribute("ngayhientai",date.format(formatter).toString());
		request.setAttribute("slbinhluan",baivietService.getTotalComment(cd));
		RequestDispatcher rd = request.getRequestDispatcher("views/web/singlenews.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
	}
	
}
