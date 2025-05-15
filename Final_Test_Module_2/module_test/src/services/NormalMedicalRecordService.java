package services;

import exception.DuplicateMedicalRecordException;
import models.NormalMedicalRecord;
import repositories.NormalMedicalRecordRepository;

import java.util.List;

public class NormalMedicalRecordService implements IMedicalRecordService<NormalMedicalRecord> {
    private final NormalMedicalRecordRepository normalMedicalRecordRepository = new NormalMedicalRecordRepository();

    @Override
    public List<NormalMedicalRecord> findAll() {
        return normalMedicalRecordRepository.findAll();
    }

    @Override
    public void add(NormalMedicalRecord medicalRecord) throws DuplicateMedicalRecordException {
        if (findById(medicalRecord.getRecordId()) != null) {
            throw new DuplicateMedicalRecordException("Bệnh án đã tồn tại");
        }
        normalMedicalRecordRepository.add(medicalRecord);
    }

    @Override
    public void delete(String recordId) {
        normalMedicalRecordRepository.delete(recordId);
    }

    @Override
    public NormalMedicalRecord findById(String recordId) {
        return normalMedicalRecordRepository.findByRecordId(recordId);
    }
}