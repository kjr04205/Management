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
import com.project.DTO.Inventory;

@Controller
public class ExcelController {
	@Autowired
	EmployeeController employee;
	
	@Autowired
	InventoryController inventory;
	
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
		
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		
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
	
	@GetMapping("/inventory/excel")
	public void downloadExcelInventory(HttpServletResponse response) throws Exception{
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("자재 목록");
		int rowNo = 0;
		try {
			Row headerRow = sheet.createRow(rowNo++);
			headerRow.createCell(0).setCellValue("자재 이름");
			headerRow.createCell(1).setCellValue("자재 코드");
			headerRow.createCell(2).setCellValue("자재 수량");
			headerRow.createCell(3).setCellValue("자재 위치");
			headerRow.createCell(4).setCellValue("자재 분류");
			
			sheet.setColumnWidth(0, 5000);
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 5000);
			sheet.setColumnWidth(3, 5000);
			sheet.setColumnWidth(4, 5000);
			
			
			List<Inventory> list = inventory.inventoryList();
			 
			for(Inventory inventory : list) {
				Row row = sheet.createRow(rowNo++);
				row.createCell(0).setCellValue(inventory.getName());
				row.createCell(1).setCellValue(inventory.getCode());
				row.createCell(2).setCellValue(inventory.getCount());
				row.createCell(3).setCellValue(inventory.getLno());
				row.createCell(4).setCellValue(inventory.getIgno());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=Inventory.xls");
 
        workbook.write(response.getOutputStream());
        workbook.close();
	}
}
