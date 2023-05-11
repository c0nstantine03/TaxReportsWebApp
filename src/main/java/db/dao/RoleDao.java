package db.dao;

import db.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends DAO<Role> {

	List<Role> getAll();

	Optional<Role> findByCode(String code);
}
