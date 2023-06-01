package org.project.trwa.db.dao;

import org.project.trwa.db.entity.Report;

import java.util.List;
import java.util.Optional;

public interface ReportDao extends DAO<Report> {

	List<Report> getAll();

	Optional<Report> findByCode(String code);

	List<Report> findForAuthor(Long id);

	List<Report> findForInspector(Long id);
}
