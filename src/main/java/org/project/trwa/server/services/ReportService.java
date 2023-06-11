package org.project.trwa.server.services;

import org.project.trwa.db.entity.Report;
import org.project.trwa.db.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReportService {
	Optional<Report> createReport(Report report);
	void updateReport(Report report);
	void deleteReport(Long id);
	Optional<Report> getReport(Long id);
	List<Report> getReportsByAuthor(Long id);
	List<Report> getReportsByInspector(Long id);
	List<Report> getAll();
}
