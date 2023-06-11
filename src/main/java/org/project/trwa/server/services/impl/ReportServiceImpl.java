package org.project.trwa.server.services.impl;

import org.project.trwa.db.dao.ReportDao;
import org.project.trwa.db.dao.impl.conn.DataSource;
import org.project.trwa.db.dao.impl.jdbc.ReportDaoImpl;
import org.project.trwa.db.entity.Report;
import org.project.trwa.server.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportServiceImpl implements ReportService {
	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Override
	public Optional<Report> createReport(Report report) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportDao reportDao = new ReportDaoImpl(connection);
			return reportDao.insert(report);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public void updateReport(Report report) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportDao reportDao = new ReportDaoImpl(connection);
			reportDao.update(report);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteReport(Long id) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportDao reportDao = new ReportDaoImpl(connection);
			reportDao.delete(id);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
	}

	@Override
	public Optional<Report> getReport(Long id) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportDao reportDao = new ReportDaoImpl(connection);
			return reportDao.findById(id);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public List<Report> getReportsByAuthor(Long id) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportDao reportDao = new ReportDaoImpl(connection);
			return reportDao.findForAuthor(id);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public List<Report> getReportsByInspector(Long id) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportDao reportDao = new ReportDaoImpl(connection);
			return reportDao.findForInspector(id);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public List<Report> getAll() {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportDao reportDao = new ReportDaoImpl(connection);
			return reportDao.getAll();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}
}
