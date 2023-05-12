package org.project.taxreportswebapp.db.dao;

import org.project.taxreportswebapp.db.entity.Report;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ReportDao extends DAO<Report> {

	List<Report> getAll();

	Optional<Report> findByCode(String code);

	List<Report> findForAuthor(Long id);

	List<Report> findForInspector(Long id);
}
