package db.dao;

import db.entity.Report;

import java.util.List;

public interface ReportDao extends DAO<Report> {

	List<Report> getAll();

	Report findByCode(String code);

	List<Report> findForAuthor(Long id);

	List<Report> findForInspector(Long id);
}
