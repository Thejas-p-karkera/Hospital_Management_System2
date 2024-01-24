package HospitalManagementSystem.c6315hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Patients
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "Patient_Name", nullable = false, unique = true)
	private String name;
	
	@Column(name="Patient_Age", nullable = false)
	private int age;
	
	@Column(name="Patient_Gender", nullable = false)
	private String gender;
	
	

	public int getId()
	{
		return Id;
	}

	public void setId(int id)
	{
		Id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	
	
	@Override
	public String toString()
	{
		return "\nName = " +name+ "\nAge = " +age+ "\nGender = " +gender ;
	}
	
	
	
	
}