package db.dao;

import db.entity.Role;

import java.util.List;

public interface RoleDao extends DAO<Role> {

	List<Role> getAll();

	Role findByCode(String code);
}
