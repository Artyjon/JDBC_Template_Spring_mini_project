package org.example.repositories;

import java.util.List;

public interface CrudRepository<T> {

    //public int update(String query);

    int saveUsingUpdate(T t);

    int updateUsingUpdate(T t);

    int deleteUsingUpdate(T t);


    // public T execute(String sql, PreparedStatementCallback<T> psc);

    Integer saveByPreparedStatement(T t);

    Integer updateByPreparedStatement(T t);

    Integer deleteByPreparedStatement(T t);

    // public T query(String sql, ResultSetExtractor<T> rse)

    T getFirstWithResultSetExtractor();

    T findById(Long id);

    List<T> findAll();

// public int update(String sql, @Nullable Object... args);

    int save(T t);

    int deleteById(Long id);

}
