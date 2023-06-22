package com.mf1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mf1.dto.CustomReport;
import com.mf1.repository.CustomReportRepository;
import com.mf1.service.SessionService;

@Controller
@RequestMapping("/admin/report")
public class Admin_ReportController {
	@Autowired
	CustomReportRepository customReportRepository;
	
	@Autowired
	SessionService sessionService;
	
	@GetMapping
	public String index() {
		List<CustomReport> report = customReportRepository.getCustomReports();
		sessionService.set("report", report);
		return "admin-report";
	}
}
