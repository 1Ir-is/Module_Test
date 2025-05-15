package repositories;

import models.NormalMedicalRecord;
import utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class NormalMedicalRecordRepository implements IMedicalRecordRepository<NormalMedicalRecord> {
    private static final String NORMAL_FILE = "D:\\module_test\\Final_Test_Module_2\\module_test\\src\\datas\\medical_record.csv";


    @Override
    public List<NormalMedicalRecord> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(NORMAL_FILE);
        List<NormalMedicalRecord> normalMedicalRecords = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",", 2);
            if (parts.length > 1 && "Normal Patient".equals(parts[0])) {
                normalMedicalRecords.add(NormalMedicalRecord.fromCSV(parts[1]));
            }
        }
        return normalMedicalRecords;
    }

    @Override
    public void save(List<NormalMedicalRecord> data) {
        List<String> result = new ArrayList<>();
        List<String> lines = SaveFileUtils.readFromFile(NORMAL_FILE);
        for (String line : lines) {
            if (!line.startsWith("Normal Patient,")) {
                result.add(line);
            }
        }
        for (NormalMedicalRecord normalMedicalRecord : data) {
            result.add("Normal Patient," + normalMedicalRecord.toCSV());
        }
        SaveFileUtils.writeToFile(NORMAL_FILE, result, false);
    }

    @Override
    public void add(NormalMedicalRecord medicalRecord) {
        List<NormalMedicalRecord> current = findAll();
        current.add(medicalRecord);
        save(current);
    }

    @Override
    public void delete(String recordId) {
        List<NormalMedicalRecord> current = findAll();
        for (int i = 0; i < current.size(); i++) {
           if (current.get(i).getRecordId().equalsIgnoreCase(recordId)) {
               current.remove(i);
               break;
           }
        }
        save(current);
    }

    @Override
    public NormalMedicalRecord findByRecordId(String recordId) {
        List<NormalMedicalRecord> current = findAll();
        for (NormalMedicalRecord normalMedicalRecord : current) {
            if (normalMedicalRecord.getRecordId().equalsIgnoreCase(recordId)) {
                return normalMedicalRecord;
            }
        }
        return null;
    }

    @Override
    public boolean existsByPatientId(String patientId) {
        List<NormalMedicalRecord> records = findAll();
        for (NormalMedicalRecord record : records) {
            if (record.getPatientId().equalsIgnoreCase(patientId)) {
                return true;
            }
        }
        return false;
    }
}
