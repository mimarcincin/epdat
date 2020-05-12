package sk.upjs.epdat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.upjs.epdat.models.Plant;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    List<Plant> findAllByFamily(String family);

    List<Plant> findAllByFamilyAndGenusContaining(String family, String genus);

    List<Plant> findAllByFamilyAndGenusContainingAndSpeciesContaining(String family, String genus, String species);

    @Query(value = "SELECT * FROM plants WHERE plants.family = ?1 AND plants.id IN ( SELECT records.plant_id FROM records WHERE tissue LIKE %?2%)", nativeQuery = true)
    List<Plant> findAllbyFamilyAndTissue(String family, String tissue);

    @Query(value = "SELECT * FROM plants WHERE plants.family = ?1 AND plants.genus LIKE %?2% AND plants.id IN ( SELECT records.plant_id FROM records WHERE tissue LIKE %?3%)", nativeQuery = true)
    List<Plant> findAllByFamilyAndGenusAndTissue(String family, String genus, String tissue);

    @Query(value = "SELECT * FROM plants WHERE plants.family = ?1 AND plants.genus LIKE %?2% AND plants.species LIKE %?3% AND plants.id IN ( SELECT records.plant_id FROM records WHERE tissue LIKE %?4%)", nativeQuery = true)
    List<Plant> findAllByFamilyAndGenusAndSpeciesAndTissue(String family, String genus, String species, String tissue);
}
