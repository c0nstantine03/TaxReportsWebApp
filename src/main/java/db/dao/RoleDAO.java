package db.dao;

import db.entity.Role;

import java.util.List;

public interface RoleDAO extends GenericDAO<Role> {

	List<Role> getAll();
}
