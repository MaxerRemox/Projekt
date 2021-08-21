package pl.Dominik.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.Dominik.entity.Event;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

    List<Event> findByOwner_id(Long id);

    @Query("update Event e set e.description = :desc, e.date = :date, e.name = :name, e.place = :place WHERE e.id = :id")
    void updateEvent(@Param("id") Long id, String desc, Date date, String name, String place);
}
