package db.dao;

import db.entity.Role;

import java.util.List;

public interface RoleDao extends GenericDao<Role> {

	List<Role> getAll();
}
