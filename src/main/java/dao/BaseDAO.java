package dao;

import javax.sql.DataSource;

public abstract class BaseDAO {
	BaseDAO(DataSource ds) {
		this.ds = ds;
	}
	
	protected DataSource ds;
}
