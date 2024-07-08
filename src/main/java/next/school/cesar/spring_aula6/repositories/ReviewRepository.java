package next.school.cesar.spring_aula6.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import next.school.cesar.spring_aula6.entities.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {

}