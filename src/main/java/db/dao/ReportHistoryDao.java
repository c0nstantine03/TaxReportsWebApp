package db.dao;

import db.entity.ReportHistory;

import java.util.List;

public interface ReportHistoryDao extends DAO<ReportHistory> {

	List<ReportHistory> getAll();

	List<ReportHistory> findByCode(String code);

	List<ReportHistory> findClosedForAuthor(Long id);

	List<ReportHistory> findClosedForInspector(Long id);
}
