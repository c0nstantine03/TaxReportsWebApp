package org.project.trwa.db.dao;

import org.project.trwa.db.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends DAO<Role> {

	List<Role> getAll();

	Optional<Role> findByCode(String code);
}