package sk.upjs.epdat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.upjs.epdat.models.Record;


import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{
    Page<Record> findByPlantId(Long plantId, Pageable pageable);
    Optional<Record> findByPlantIdAndId(Long plantId, Long Id);
}
