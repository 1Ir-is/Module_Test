package services;

import exception.DuplicateMedicalRecordException;

import java.util.List;

public interface IMedicalRecordService<T> {
    List<T> findAll();

    void add(T medicalRecord) throws DuplicateMedicalRecordException;

    void delete(String recordId);

    T findById(String recordId);
}