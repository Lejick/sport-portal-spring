package org.portal.back.model.sherdog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FighterRepository extends CrudRepository<Fighter, Long> {
    List<Fighter> findBySherdogUrlContaining(String sherdogUrl);
}
