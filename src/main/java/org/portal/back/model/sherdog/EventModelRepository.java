package org.portal.back.model.sherdog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventModelRepository extends CrudRepository<EventModel, Long> {
    List<EventModel> findByEventNameContaining(String event_name);
}
