package com.quanlytintuc.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanlytintuc.model.TaiKhoan;
import com.quanlytintuc.service.ITaiKhoanService;
import com.quanlytintuc.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-admin-taikhoan"})
public class TaiKhoanAPI extends HttpServlet{
	@Inject
	private ITaiKhoanService taikhoanService;
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		TaiKhoan tk = HttpUtil.of(request.getReader()).toModel(TaiKhoan.class);
		tk = taikhoanService.save(tk);
		mapper.writeValue(response.getOutputStream(), tk);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		TaiKhoan bvUpdate = HttpUtil.of(request.getReader()).toModel(TaiKhoan.class);
		bvUpdate = taikhoanService.update(bvUpdate);
		mapper.writeValue(response.getOutputStream(), bvUpdate);	
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		TaiKhoan bv = HttpUtil.of(request.getReader()).toModel(TaiKhoan.class);
		taikhoanService.delete(bv.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");	
	}
}
