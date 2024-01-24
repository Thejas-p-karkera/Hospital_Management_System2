package HospitalManagementSystem.c6315hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Billing
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Billing_Id")
	private int id;
	
	@Column(name = "Appointment_Id", nullable = false)
	private int appointmentId;
	
//	@Column(name = "Patient_Name")
//	private int patientName;
//
//	@Column(name = "Doctor_Name")
//	private int doctorName;
//	
//	@Column(name = "Appointment_Date")
//	private String appointmentDate;
	
	@Column(name = "Billing_Amount", nullable = false)
	private long amount;
	
	@OneToOne
	@JoinColumn(name = "Appointment_Id", referencedColumnName = "Id", insertable = false, updatable = false)
	private Appointments appointments;
	
//	@ManyToOne
//	@JoinColumn(name = "Patient_Name", referencedColumnName = "name", insertable = false, updatable = false)
//	private Patients patients;
//
//	@ManyToOne
//	@JoinColumn(name = "Doctor_Name", referencedColumnName = "name", insertable = false, updatable = false)
//	private Doctors doctors;
	
	

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getAppointmentId()
	{
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId)
	{
		this.appointmentId = appointmentId;
	}

//	public int getPatientName()
//	{
//		return patientName;
//	}
//
//	public void setPatientName(int patientName)
//	{
//		this.patientName = patientName;
//	}
//
//	public int getDoctorName() {
//		return doctorName;
//	}
//
//	public void setDoctorName(int doctorName)
//	{
//		this.doctorName = doctorName;
//	}
//
//	public String getAppointmentDate()
//	{
//		return appointmentDate;
//	}
//
//	public void setAppointmentDate(String appointmentDate)
//	{
//		this.appointmentDate = appointmentDate;
//	}

	public long getAmount()
	{
		return amount;
	}

	public void setAmount(long amount)
	{
		this.amount = amount;
	}

	public Appointments getAppointments()
	{
		return appointments;
	}

	public void setAppointments(Appointments appointments)
	{
		this.appointments = appointments;
	}

//	public Patients getPatients()
//	{
//		return patients;
//	}
//
//	public void setPatients(Patients patients)
//	{
//		this.patients = patients;
//	}
//
//	public Doctors getDoctors()
//	{
//		return doctors;
//	}
//
//	public void setDoctors(Doctors doctors)
//	{
//		this.doctors = doctors;
//	}

	@Override
	public String toString()
	{
		return "Billing id=" + id + "\nappointmentId=" + appointmentId  +"\namount=" + amount;
	}
	
	
	
}
