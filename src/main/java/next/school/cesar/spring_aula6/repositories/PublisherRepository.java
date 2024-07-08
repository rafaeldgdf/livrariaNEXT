package next.school.cesar.spring_aula6.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import next.school.cesar.spring_aula6.entities.PublisherEntity;



public interface PublisherRepository extends JpaRepository<PublisherEntity, UUID> {

}

//é regra, dentro do repository: 
//-ser interface
//- extends JpaRepository
//- <NomeDaClasse, ID>  