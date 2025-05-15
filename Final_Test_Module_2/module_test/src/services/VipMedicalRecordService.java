package services;

import exception.DuplicateMedicalRecordException;
import models.VipMedicalRecord;
import repositories.VipMedicalRecordRepository;

import java.util.List;

public class VipMedicalRecordService implements IMedicalRecordService<VipMedicalRecord> {
    private final VipMedicalRecordRepository vipMedicalRecordRepository = new VipMedicalRecordRepository();

    @Override
    public List<VipMedicalRecord> findAll() {
        return vipMedicalRecordRepository.findAll();
    }

    @Override
    public void add(VipMedicalRecord medicalRecord) throws DuplicateMedicalRecordException {
        if (findById(medicalRecord.getRecordId()) != null) {
            throw new DuplicateMedicalRecordException("Bệnh án đã tồn tại");
        }
        vipMedicalRecordRepository.add(medicalRecord);
    }

    @Override
    public void delete(String recordId) {
        vipMedicalRecordRepository.delete(recordId);
    }

    @Override
    public VipMedicalRecord findById(String recordId) {
        return vipMedicalRecordRepository.findByRecordId(recordId);
    }
}