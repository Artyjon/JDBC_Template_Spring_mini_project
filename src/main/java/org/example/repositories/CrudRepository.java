package org.example.repositories;

public interface CrudRepository <T>{

    //public int update(String query);

    int saveUsingUpdate(T t);

    int updateUsingUpdate(T t);

    int deleteUsingUpdate(T t);


    // public T execute(String sql, PreparedStatementCallback<T> psc);

    Integer saveByPreparedStatement(T t);

    Integer updateByPreparedStatement(T t);

    Integer deleteByPreparedStatement(T t);

}
