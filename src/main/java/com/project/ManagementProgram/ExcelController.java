package com.project.ManagementProgram;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.DTO.Employee;

@Controller
public class ExcelController {
	@Autowired
	EmployeeController employee;
	
	@GetMapping("/excel")
	public void downloadExcel(HttpServletResponse response) throws Exception{
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("게시판글들");
		int rowNo = 0;
		
		Row headerRow = sheet.createRow(rowNo++);
		headerRow.createCell(0).setCellValue("이름");
		headerRow.createCell(1).setCellValue("전화번호");
		headerRow.createCell(2).setCellValue("직책");
		headerRow.createCell(3).setCellValue("부서");
		headerRow.createCell(4).setCellValue("입사날짜");
		
		List<Employee> list = employee.employeeList();
		for(Employee employee : list) {
			Row row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(employee.getName());
			row.createCell(1).setCellValue(employee.getNumber());
			row.createCell(2).setCellValue(employee.getPosition());
			row.createCell(3).setCellValue(employee.getTeam());
			row.createCell(4).setCellValue(employee.getStartdate());
		}
		
		response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=Employee.xls");
 
        workbook.write(response.getOutputStream());
        workbook.close();
	}
}
