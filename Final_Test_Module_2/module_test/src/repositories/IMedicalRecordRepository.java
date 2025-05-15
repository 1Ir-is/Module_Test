package repositories;

import java.util.List;

public interface IMedicalRecordRepository<T> {
    List<T> findAll();
    void save(List<T> data);
    void add(T medicalRecord);
    void delete(String recordId);
    T findByRecordId(String recordId);
    boolean existsByPatientId(String patientId);
}
