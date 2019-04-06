package com.terenko.repository;
import com.terenko.model.zipFile;
import org.springframework.data.jpa.repository.JpaRepository;
public interface zipRepository extends JpaRepository<zipFile, Long> {

}
