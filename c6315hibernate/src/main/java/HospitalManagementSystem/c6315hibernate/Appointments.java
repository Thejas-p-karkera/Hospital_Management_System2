package HospitalManagementSystem.c6315hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Patient_Id", nullable = false)
	private int patientId;

	@Column(name = "Doctor_Id", nullable = false)
	private int doctorId;

	@Column(name = "Appaointment_Date", nullable = false)
	private String appointmentDate;

	@ManyToOne
	@JoinColumn(name = "Patient_Id", referencedColumnName = "Id", insertable = false, updatable = false)
	private Patients patients;

	@ManyToOne
	@JoinColumn(name = "Doctor_Id", referencedColumnName = "Id", insertable = false, updatable = false)
	private Doctors doctors;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getPatientId()
	{
		return patientId;
	}

	public void setPatientId(int patientId)
	{
		this.patientId = patientId;
	}

	public int getDoctorId()
	{
		return doctorId;
	}

	public void setDoctorId(int doctorId)
	{
		this.doctorId = doctorId;
	}

	public String getAppointmentDate()
	{
		return appointmentDate;
	}

	public void setAppointmentDate(String date)
	{
		this.appointmentDate = date;
	}

	public Patients getPatient()
	{
		return patients;
	}

	public void setPatient(Patients patient)
	{
		this.patients = patient;
	}

	public Doctors getDoctor()
	{
		return doctors;
	}

	public void setDoctor(Doctors doctor)
	{
		this.doctors = doctor;
	}

	@Override
	public String toString()
	{
		return "Appointment id = "+id+"\nPatient id = "+patients+"\nDoctor id = "+doctors+"\nAppointment_date = "+appointmentDate;
	}
}
