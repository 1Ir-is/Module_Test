package models;

public class VipMedicalRecord extends MedicalRecord {
    private String vipType;
    private String vipDuration;

    public VipMedicalRecord(int recordNumber, String recordId, String patientId, String patientName, String admissionDate, String dischargeDate, String reason, String vipType, String vipDuration) {
        super(recordNumber, recordId, patientId, patientName, admissionDate, dischargeDate, reason);
        this.vipType = vipType;
        this.vipDuration = vipDuration;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public String getVipDuration() {
        return vipDuration;
    }

    public void setVipDuration(String vipDuration) {
        this.vipDuration = vipDuration;
    }

    @Override
    public String toCSV() {
        return recordNumber + "," + recordId + "," + patientId + "," + patientName + "," + admissionDate + "," + dischargeDate + "," + reason + "," + vipType + "," + vipDuration;
    }

    @Override
    public String getDetails() {
        return "Vip Patient [Record Number: " + recordNumber + ", Record Id: " + recordId +
                ", Patient Id: " + patientId + ", Patient Name: " + patientName + ", Admission Date: " + admissionDate + ", Discharge Date: " + dischargeDate + ", Reason: " + reason + ", Vip Type: " + vipType +  ", Vip Duration: " + vipDuration + "]";
    }

    public static VipMedicalRecord fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new VipMedicalRecord(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                parts[5],
                parts[6],
                parts[7],
                parts[8]
        );
    }
}
