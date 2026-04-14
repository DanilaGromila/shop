package org.gromila.shopapp.repository;

import org.gromila.shopapp.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByIdAndItemId(Long id, Long itemId);

    List<Feedback> findAllByItemId(Long itemId);
}
