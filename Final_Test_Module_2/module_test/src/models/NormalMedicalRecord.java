package models;

public class NormalMedicalRecord extends MedicalRecord {
    private double hospitalFee;

    public NormalMedicalRecord(int recordNumber, String recordId, String patientId, String patientName, String admissionDate, String dischargeDate, String reason, double hospitalFee) {
        super(recordNumber, recordId, patientId, patientName, admissionDate, dischargeDate, reason);
        this.hospitalFee = hospitalFee;
    }

    public double getHospitalFee() {
        return hospitalFee;
    }

    public void setHospitalFee(double hospitalFee) {
        this.hospitalFee = hospitalFee;
    }


    @Override
    public String toCSV() {
        return recordNumber + "," + recordId + "," + patientId + "," + patientName + "," + admissionDate + "," + dischargeDate + "," + reason + "," + hospitalFee;
    }

    @Override
    public String getDetails() {
        return "Normal Patient [Record Number: " + recordNumber + ", Record Id: " + recordId +
                ", Patient Id: " + patientId + ", Patient Name: " + patientName + ", Admission Date: " + admissionDate + ", Discharge Date: " + dischargeDate + ", Reason: " + reason + ", Hospital Fee: " + hospitalFee + " VND ]";
    }

    public static NormalMedicalRecord fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new NormalMedicalRecord(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                parts[5],
                parts[6],
                Double.parseDouble(parts[7])
        );
    }
}
