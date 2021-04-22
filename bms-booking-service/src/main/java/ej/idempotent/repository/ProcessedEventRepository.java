package ej.idempotent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProcessedEventRepository<T> extends CrudRepository<T, Long> {

    List<T> findAllByEventId(String eventId);

}
