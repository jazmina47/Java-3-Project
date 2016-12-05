package healthcareLook;

public class Appointment {

	String appointmentDate;
	String reasonForAppointment;
	String appointmentTime;
	String patient_id;
	
	public Appointment(){
		
	}

	public Appointment(String appointmentDate, String appointmentTime, String patient_id) {
		this.appointmentDate = appointmentDate;
		this.reasonForAppointment = "Paying";
		this.appointmentTime = appointmentTime;
		this.patient_id = patient_id;
	}



	public Appointment(String appointmentDate, String reasonForAppointment, String appointmentTime, String patient_id) {
		super();
		this.appointmentDate = appointmentDate;
		this.reasonForAppointment = reasonForAppointment;
		this.appointmentTime = appointmentTime;
		this.patient_id = patient_id;
	}





	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getReasonForAppointment() {
		return reasonForAppointment;
	}

	public void setReasonForAppointment(String reasonForAppointment) {
		this.reasonForAppointment = reasonForAppointment;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	
	
}
