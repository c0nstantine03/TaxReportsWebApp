package org.project.trwa.server.services;

import org.project.trwa.db.entity.ReportHistory;

import java.util.List;

public interface ReportHistoryService {
	void updateHistory(ReportHistory reportHistory);
	void deleteHistory(Long id);
	List<ReportHistory> getHistoryByCode(String code);
	List<ReportHistory> getClosedByAuthor(Long id);
	List<ReportHistory> getClosedByInspector(Long id);
	List<ReportHistory> getAll();
}
