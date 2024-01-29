package HospitalManagementSystem.c6315hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "billing")
public class Billing
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Billing_Id")
    private int id;

    @Column(name = "Billing_Amount", nullable = false)
    private long amount;

    @Column(name = "Appointment_Id", nullable = false)
    private int appointmentId;

    @Column(name = "Patient_Name")
    private String patientName;

    @Column(name = "Doctor_Name")
    private String doctorName;

    @OneToOne
    @JoinColumn(name = "Appointment_Id", referencedColumnName = "Id", insertable = false, updatable = false)
    private Appointments appointments;

    @OneToOne
    @JoinColumn(name = "Patient_Id", referencedColumnName = "Id", insertable = false, updatable = false)
    private Patients patients;

    @OneToOne
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

    public int getAppointmentId()
    {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId)
    {
        this.appointmentId = appointmentId;
    }

    public String getPatientName()
    {
        return patientName;
    }

    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public String getDoctorName()
    {
        return doctorName;
    }

    public void setDoctorName(String doctorName)
    {
        this.doctorName = doctorName;
    }

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

    public Patients getPatients()
    {
        return patients;
    }

    public void setPatients(Patients patients)
    {
        this.patients = patients;
    }

    public Doctors getDoctors()
    {
        return doctors;
    }

    public void setDoctors(Doctors doctors)
    {
        this.doctors = doctors;
    }

    @Override
    public String toString()
    {
        return "Billing [id=" + id + ", amount=" + amount + ", appointmentId=" + appointmentId + ", appointments="
                + appointments + ", patients=" + patients + ", doctors=" + doctors + "]";
    }
}
