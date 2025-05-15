package repositories;

import models.NormalMedicalRecord;
import models.VipMedicalRecord;
import utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class VipMedicalRecordRepository implements IMedicalRecordRepository<VipMedicalRecord> {
    private static final String VIP_FILE = "D:\\module_test\\Final_Test_Module_2\\module_test\\src\\datas\\medical_record.csv";

    @Override
    public List<VipMedicalRecord> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(VIP_FILE);
        List<VipMedicalRecord> vipMedicalRecords = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",", 2);
            if (parts.length > 1 && "Vip Patient".equals(parts[0])) {
                vipMedicalRecords.add(VipMedicalRecord.fromCSV(parts[1]));
            }
        }
        return vipMedicalRecords;
    }

    @Override
    public void save(List<VipMedicalRecord> data) {
        List<String> result = new ArrayList<>();
        List<String> lines = SaveFileUtils.readFromFile(VIP_FILE);
        for (String line : lines) {
            if (!line.startsWith("Vip Patient,")) {
                result.add(line);
            }
        }
        for (VipMedicalRecord vipMedicalRecord : data) {
            result.add("Vip Patient," + vipMedicalRecord.toCSV());
        }
        SaveFileUtils.writeToFile(VIP_FILE, result, false);
    }

    @Override
    public void add(VipMedicalRecord medicalRecord) {
        List<VipMedicalRecord> current = findAll();
        current.add(medicalRecord);
        save(current);
    }

    @Override
    public void delete(String recordId) {
        List<VipMedicalRecord> current = findAll();
        for (int i = 0; i < current.size(); i++) {
            if (current.get(i).getRecordId().equalsIgnoreCase(recordId)) {
                current.remove(i);
                break;
            }
        }
        save(current);
    }

    @Override
    public VipMedicalRecord findByRecordId(String recordId) {
        List<VipMedicalRecord> current = findAll();
        for (VipMedicalRecord vipMedicalRecord : current) {
            if (vipMedicalRecord.getRecordId().equalsIgnoreCase(recordId)) {
                return vipMedicalRecord;
            }
        }
        return null;
    }

    @Override
    public boolean existsByPatientId(String patientId) {
        List<VipMedicalRecord> records = findAll();
        for (VipMedicalRecord record : records) {
            if (record.getPatientId().equalsIgnoreCase(patientId)) {
                return true;
            }
        }
        return false;
    }
}
