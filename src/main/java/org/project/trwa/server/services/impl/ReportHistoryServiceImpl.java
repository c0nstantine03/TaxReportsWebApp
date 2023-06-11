package org.project.trwa.server.services.impl;

import org.project.trwa.db.dao.ReportHistoryDao;
import org.project.trwa.db.dao.impl.conn.DataSource;
import org.project.trwa.db.dao.impl.jdbc.ReportHistoryDaoImpl;
import org.project.trwa.db.entity.ReportHistory;
import org.project.trwa.server.services.ReportHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ReportHistoryServiceImpl implements ReportHistoryService {
	private static final Logger logger = LoggerFactory.getLogger(ReportHistoryServiceImpl.class);

	@Override
	public void updateHistory(ReportHistory reportHistory) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportHistoryDao reportHistoryDao = new ReportHistoryDaoImpl(connection);
			reportHistoryDao.update(reportHistory);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteHistory(Long id) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportHistoryDao reportHistoryDao = new ReportHistoryDaoImpl(connection);
			reportHistoryDao.delete(id);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
	}

	@Override
	public List<ReportHistory> getHistoryByCode(String code) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportHistoryDao reportHistoryDao = new ReportHistoryDaoImpl(connection);
			return reportHistoryDao.findByCode(code);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public List<ReportHistory> getClosedByAuthor(Long id) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportHistoryDao reportHistoryDao = new ReportHistoryDaoImpl(connection);
			return reportHistoryDao.findClosedForAuthor(id);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public List<ReportHistory> getClosedByInspector(Long id) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportHistoryDao reportHistoryDao = new ReportHistoryDaoImpl(connection);
			return reportHistoryDao.findClosedForInspector(id);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public List<ReportHistory> getAll() {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			ReportHistoryDao reportHistoryDao = new ReportHistoryDaoImpl(connection);
			return reportHistoryDao.getAll();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}
}
