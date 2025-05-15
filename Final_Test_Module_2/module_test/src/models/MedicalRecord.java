package models;

public abstract class MedicalRecord {
    protected int recordNumber;
    protected String recordId;
    protected String patientId;
    protected String patientName;
    protected String admissionDate;
    protected String dischargeDate;
    protected String reason;

    public MedicalRecord(int recordNumber, String recordId, String patientId, String patientName, String admissionDate, String dischargeDate, String reason) {
        this.recordNumber = recordNumber;
        this.recordId = recordId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.reason = reason;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public abstract String toCSV();
    public abstract String getDetails();
}
