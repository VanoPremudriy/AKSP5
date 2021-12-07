package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.entity.Message;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {

}
