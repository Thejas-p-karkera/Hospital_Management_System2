package HospitalManagementSystem.c6315hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Entity class or pojo class

@Entity //Doctor table will create
public class Doctors
{
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Doctor_Name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "Department", nullable = false)
	private  String department;
	
	@Column(name = "Experience", nullable = false)
	private int experience;
	
	//getter and setters -> prefix set or get
		public int getId()
		{
	        return id;
	    }

	    public void setId(int id)
	    {
	        id = id;
	    }

	    public String getName()
	    {
	        return name;
	    }

	    public void setName(String name)
	    {
	        this.name = name;
	    }

	    public String getDepartment()
	    {
	        return department;
	    }

	    public void setDepartment(String department)
	    {
	        this.department = department;
	    }

	    public int getExperience()
	    {
	        return experience;
	    }

	    public void setExperience(int experience)
	    {
	        this.experience = experience;
	    }

		@Override
		public String toString()
		{
			return "Doctor id=" + id + "\nname=" + name + "\ndepartment=" + department + "\nexperience=" + experience+"\n";

		}
}
