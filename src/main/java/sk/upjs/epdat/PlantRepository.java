package sk.upjs.epdat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.upjs.epdat.models.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

}
