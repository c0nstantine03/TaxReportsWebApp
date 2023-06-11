package org.project.trwa.db.dao.factory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.project.trwa.db.dao.*;
import org.project.trwa.db.dao.impl.jba.*;

public class JbaDaoFactory extends DaoFactory {
	private final EntityManagerFactory managerFactory;

	public JbaDaoFactory() {
		managerFactory = Persistence.createEntityManagerFactory("TaxReport");
	}

	@Override
	public RoleDao createRoleDao() {
		return new RoleDaoImpl(managerFactory.createEntityManager());
	}

	@Override
	public ResidencyDao createResidencyDao() {
		return new ResidencyDaoImpl(managerFactory.createEntityManager());
	}

	@Override
	public PersonalityDao createPersonalityDao() {
		return new PersonalityDaoImpl(managerFactory.createEntityManager());
	}

	@Override
	public UserDao createUserDao() {
		return new UserDaoImpl(managerFactory.createEntityManager());
	}

	@Override
	public StatusDao createStatusDao() {
		return new StatusDaoImpl(managerFactory.createEntityManager());
	}

	public NextStatusDao createNextStatusDao() {
		return new NextStatusDaoImpl(managerFactory.createEntityManager());
	}

	@Override
	public ReportDao createReportDao() {
		return new ReportDaoImpl(managerFactory.createEntityManager());
	}

	@Override
	public ReportHistoryDao createReportHistoryDao() {
		return new ReportHistoryDaoImpl(managerFactory.createEntityManager());
	}
}
