package com.zhao.repository;

import com.zhao.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PositionRepository extends JpaRepository<Position,Long> {
    @Query(nativeQuery = true,value = "select  p.id,p.name,pd.description from position as p inner join position_detail as pd on pd.pid =p.id where p.id =:id")
    public  Object findPositionLoadById(@Param("id")Long id);
}
