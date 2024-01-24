package HospitalManagementSystem.c6315hibernate;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

public class HospitalManagementSystem
{
	static Configuration configuration;
	static Session session;
	static SessionFactory sessionfactory;
	static Scanner scanner;
	
	public static void createPatient()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration(); //class reads both the entity class and config  file
		configuration.configure(); //checks config file syntax
		    	
		//Interface. it takes metedata and build connection
		sessionfactory = configuration.buildSessionFactory();
		//session -> time period b/n start and end.
		session = sessionfactory.openSession();
		
		Patients p = new Patients();
		
		System.out.print("Enter Patient Name: ");
		String name = scanner.nextLine();
		p.setName(name);
		
		System.out.print("Enter Patient Age: ");
		int age = scanner.nextInt();
		p.setAge(age);
		
		System.out.print("Enter Patient Gender: ");
		String gender = scanner.next();
		p.setGender(gender);
		
		session.save(p);
		
		Transaction transaction = session.beginTransaction();
		
		transaction.commit();
		
		// Check if the Patient is Added or not
    	
   	 if (p.getId() > 0)
        {
            System.out.println("Patient Added Successfully.");
        }
        else
        {
            System.out.println("Failed to Add Patient!");
        }
	}
//--------------------------------------------------------------------------------------------------------------------
	public static void viewPatientById()
	{
		System.out.print("\nEnter Patient Id: ");
		int id = scanner.nextInt();

		Patients p = session.get(Patients.class, id);
		
		if(p == null)
		{
			System.out.println("There is no Record Found For This ID.");
		}
		else
		{
			System.out.println("\nPatient Details:");
			System.out.println("==================");
			
			System.out.println("+----+------------------+-------------+----------------+");
			System.out.println("| id |   Patient_Name   | Patient_Age | Patient_Gender |");
			System.out.println("+----+------------------+-------------+----------------+");
			System.out.printf("| %-2s | %-16s | %-11s | %-14s |\n",p.getId(), p.getName(), p.getAge(), p.getGender());
			System.out.println("+----+------------------+-------------+----------------+");
		}
	}
	
	
	public static void viewAllPatients()
	{	
		String hqlQuery = "from Patients";
		
		List<Patients> data = session.createQuery(hqlQuery, Patients.class).list();
		
		if(data.isEmpty())
		{
			System.out.println("There is no Patients Found");
		}
		else
		{
			System.out.println("\nPatient Details:");
			System.out.println("==================");
			
			System.out.println("+----+------------------+-------------+----------------+");
			System.out.println("| id |   Patient_Name   | Patient_Age | Patient_Gender |");
			System.out.println("+----+------------------+-------------+----------------+");
		
		for(Patients p : data)
		{
			System.out.printf("| %-2s | %-16s | %-11s | %-14s |\n",p.getId(), p.getName(), p.getAge(), p.getGender());
		}
		
		
		System.out.println("+----+------------------+-------------+----------------+");
		}

	}
	

	public static void readPatient()
	{
		while(true)
		{
			System.out.println("\nSelect How You Want To Read Patients Record:");
			System.out.println("---------------------------------------------");
			System.out.println("1. View Particular Patient.");
			System.out.println("2. View All Patients.");
			System.out.println("3. Exit.");
			System.out.print("\nEnter your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					viewPatientById();
					System.out.println();
					break;
					
				case 2:
					viewAllPatients();
					System.out.println();
					break;
					
				case 3:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//--------------------------------------------------------------------------------------------------------------------
	public static void updatePatientName()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration();
		configuration.configure();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		System.out.print("Enter Patient Id to Update Name: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Patients p = session.get(Patients.class, id); //returns Doctor type of object
		
		if( p == null)
		{
			System.out.println("There is no Record Found for This Id.");
		}
		else
		{
			Transaction transaction = session.beginTransaction();
			
			System.out.print("Enter the New Name for Patient_Id "+id+": ");
			String newName = scanner.nextLine();
			p.setName(newName);
			session.update(p);
//			session.merge(d);
			
			System.out.println("Patient Name Updated Successfully.");
			transaction.commit();
			session.close();
//			scanner.close();
		}
	}

	
	public static void updatePatientAge()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration();
		configuration.configure();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		System.out.print("Enter Patient Id to Update Age: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Patients p = session.get(Patients.class, id); //returns Doctor type of object
		if( p == null)
		{
			System.out.println("There is no Record Found for This Id.");
		}
		else
		{
			Transaction transaction = session.beginTransaction();
			
			System.out.print("Enter the New Age for Patient_Id "+id+": ");
			int age = scanner.nextInt();
			p.setAge(age);
			session.update(p);
//			session.merge(d);
			
			System.out.println("Patient Age Updated Successfully.");
			transaction.commit();
			session.close();
//			scanner.close();
		}
	}

	
	public static void updatePatientGender()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration();
		configuration.configure();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		System.out.print("Enter Patient Id to Update Gender: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Patients p = session.get(Patients.class, id); //returns Doctor type of object
		if( p == null)
		{
			System.out.println("There is no Record Found for This Id.");
		}
		else
		{
			Transaction transaction = session.beginTransaction();
			
			System.out.print("Enter the New Gender for Patient_Id "+id+": ");
			String gender = scanner.next();
			p.setGender(gender);
			session.update(p);
//			session.merge(d);
			
			System.out.println("Patient Gender Updated Successfully.");
			transaction.commit();
			session.close();
//			scanner.close();
		}
		
	}
	

	public static void updatePatient()
	{
		scanner = new Scanner(System.in);
		
		//step1
		Configuration config = new Configuration();
		config.configure();
				
		//step2
		SessionFactory factory = config.buildSessionFactory();
		session = factory.openSession();

		while(true)
		{
			System.out.println("\nSelect What you Want to Update in Patient's Record.");
			System.out.println("-----------------------------------------------------");
			System.out.println("1. Patient Name.");
			System.out.println("2. Patient Age.");
			System.out.println("3. Patient Gender.");
			System.out.println("4. Exit.");
			System.out.print("Enter your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					updatePatientName();
					System.out.println();
					break;
					
				case 2:
					updatePatientAge();
					System.out.println();
					break;
					
				case 3:
					updatePatientGender();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Invalid Choice! ..Enter a Valid Choice.");
					System.out.println();
					break;			
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//--------------------------------------------------------------------------------------------------------------------
	public static void deletePatientById()
	{
		scanner = new Scanner(System.in);

	    System.out.print("Enter Patient id: ");
	    int id = scanner.nextInt();
	    scanner.nextLine();

	    Patients p = session.get(Patients.class, id);

	    if (p == null)
	    {
	        System.out.println("Patient with ID " + id + " does not exist.");
	        return;
	    }
	    else
	    {
	    	System.out.println(". . . . . . . . . . . . . . . . . . .  A  L  E  R  T  . . . . . . . . . . . . . . . . . . .");
	    	System.out.println("You Are About to Permanently Delete a Particular Patient Record and Associated Appointments");
			System.out.print("Do You Wish to Continue? (Yes/No) : ");
			
			String confirmation = scanner.nextLine();
			
			if(confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))
			{
				Transaction tx = session.beginTransaction();

			    // Delete associated appointments first
			    Query deleteAppointmentsQuery = session.createQuery("delete from Appointments where Patient_id = :id");
			    deleteAppointmentsQuery.setParameter("id", id);
			    int deletedAppointmentsCount = deleteAppointmentsQuery.executeUpdate();
			    
			    // Now, delete the Patient
			    String name = p.getName();
			    session.delete(p);
			    
			    tx.commit();
			    
			    System.out.println("Patient " + name + " and " + deletedAppointmentsCount + " Associated Appointments Removed.");
			}
			
			else
			{
				System.out.println("Deletion Cancelled!");
			}
			
	    }
	}
	
	
	public static void deleteAllPatients()
	{
	    scanner = new Scanner(System.in); // Create a new scanner object

	    String hqlQuery = "select count(*) from Patients";
	    Long count = session.createQuery(hqlQuery, Long.class).getSingleResult();
	    
	    if(count == 0)
	    {
	    	System.out.println("There are no Patients to Delete.");
	        return;
	    }
	    
	    System.out.println(". . . . . . . . . . . . . . . .  A   L   E   R   T . . . . . . . . . . . . . . . .");
	    System.out.println("You are about to permanently delete all patient records and associated appointments");
		System.out.print("Do You Wish to Continue? (Yes/No) : ");

	    String confirmation = scanner.nextLine();

	    if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y"))
	    {
	        Transaction tx = session.beginTransaction();

	        // Delete all appointments
	        Query deleteAppointmentsQuery = session.createQuery("delete from Appointments");
	        int deletedAppointmentsCount = deleteAppointmentsQuery.executeUpdate();

	        // Now, delete all patients
	        Query deletePatientsQuery = session.createQuery("delete from Patients");
	        int deletedPatientsCount = deletePatientsQuery.executeUpdate();

	        tx.commit();

	        System.out.println(deletedPatientsCount + " patients and " + deletedAppointmentsCount + " associated appointments removed.");
	    }
	    else
	    {
	        System.out.println("Deletion cancelled.");
	    }
	}
	
	
	public static void deletePatient()
	{
		while(true)
		{
			System.out.println("\nSelect How You Want to Delete Patients Record:");
			System.out.println("----------------------------------------------");
			System.out.println("1. Delete Particular Patient.");
			System.out.println("2. Delete All Patients.");
			System.out.println("3. Exit.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					deletePatientById();
					System.out.println();
					break;
					
				case 2:
					deleteAllPatients();
					System.out.println();
					break;
					
				case 3:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//============================================================================================================================
	public static void createDoctor()
	{
		scanner = new Scanner(System.in);
		
		// Initializing the configuration object
	    configuration = new Configuration();
	    configuration.configure();
	    
		sessionfactory = configuration.buildSessionFactory();
		
		session = sessionfactory.openSession();
		
		Doctors d = new Doctors();

        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();
        d.setName(name);

        System.out.print("Enter Doctor Department: ");
        String department = scanner.nextLine();
        d.setDepartment(department);

        System.out.print("Enter Doctor Experience (in years): ");
        int experience = scanner.nextInt();
        d.setExperience(experience);
        
    	session.save(d);

    	Transaction tx = session.beginTransaction();

    	tx.commit();
    	
    	
    	// Check if the Doctor is Added or not
    	
    	 if (d.getId() > 0)
         {
             System.out.println("Doctor Added Successfully.");
         }
         else
         {
             System.out.println("Failed to Add Doctor!");
         }
	}
	
	
	public static void viewDoctorById()
	{
		System.out.print("\nEnter Doctor Id: ");
		int id = scanner.nextInt();

		Doctors d = session.get(Doctors.class, id);
		if(d == null)
		{
			System.out.println("There is no Record Found For This ID.");
		}
		else
		{
			System.out.println("\nDoctor Details:");
			System.out.println("==================");
			
			System.out.println("+-----+-----------------+-----------------+------------+");
			System.out.println("| id  | Name            | Department      |Experience  |");
			System.out.println("+-----+-----------------+-----------------+------------+");
	        System.out.printf("| %-3s | %-15s | %-15s | %-10s |\n", d.getId(), d.getName(), d.getDepartment(),  d.getExperience());
		    System.out.println("+-----+-----------------+-----------------+------------+");
		}
	}
	
	
	public static void viewAllDoctors()
	{
		String hqlQuery = "from Doctors";
		List<Doctors> data = session.createQuery(hqlQuery, Doctors.class).list();
		
		if(data.isEmpty())
		{
			System.out.println("There is no Doctors Found");
		}
		else
		{
			System.out.println("\nDoctor Details:");
			System.out.println("==================");
			
			System.out.println("+-----+-----------------+-----------------+------------+");
			System.out.println("| id  | Name            | Department      |Experience  |");
			System.out.println("+-----+-----------------+-----------------+------------+");
			
		    for (Doctors d : data)
		    {
		        System.out.printf("| %-3s | %-15s | %-15s | %-10s |\n", d.getId(), d.getName(), d.getDepartment(),  d.getExperience());
		    }

		    System.out.println("+-----+-----------------+-----------------+------------+");
		}
	}
	
	
	public static void readDoctor()
	{
		while(true)
		{
			System.out.println("\nSelect How You Want To Read Doctors Record:");
			System.out.println("--------------------------------------------");
			System.out.println("1. View Particular Doctor.");
			System.out.println("2. View All Doctors.");
			System.out.println("3. Exit.");
			System.out.print("\nEnter your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					viewDoctorById();
					System.out.println();
					break;
					
				case 2:
					viewAllDoctors();
					System.out.println();
					break;
					
				case 3:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//--------------------------------------------------------------------------------------------------------------------
	public static void updateDoctorName()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration();
		configuration.configure();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		System.out.print("Enter Doctor Id to Update Name: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Doctors d = session.get(Doctors.class, id); //returns Doctor type of object
		if(d == null)
		{
			System.out.println("There is no Record Found For This ID.");
		}
		else
		{
			Transaction transaction = session.beginTransaction();
			
			System.out.print("Enter the New Name for Doctor_Id "+id+": ");
			String newName = scanner.nextLine();
			d.setName(newName);
			session.update(d);
//			session.merge(d);
			
			System.out.println("Doctor Name Updated Successfully.");
			transaction.commit();
			session.close();
//			scanner.close();
		}
	}
	
	
	public static void updateDoctorDepartment()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration();
		configuration.configure();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		System.out.print("Enter Doctor Id to Update Department: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Doctors d = session.get(Doctors.class, id); //returns Doctor type of object
		if(d == null)
		{
			System.out.println("There is no Record Found For This ID.");
		}
		else
		{
			Transaction transaction = session.beginTransaction();
			
			System.out.print("Enter the New Department for Doctor_Id "+id+": ");
			String department = scanner.nextLine();
			d.setDepartment(department);
			session.update(d);
//			session.merge(d);
			
			System.out.println("Doctor Name Updated Successfully.");
			transaction.commit();
			session.close();
//			scanner.close();
		}
	}
	
	
	public static void updateDoctorExperience()
	{
	    scanner = new Scanner(System.in);

	    configuration = new Configuration();
	    configuration.configure();

	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    Session session = sessionFactory.openSession();
	    System.out.print("Enter Doctor Id to Update Experience: ");
	    int id = scanner.nextInt();
	    scanner.nextLine();

	    Doctors d = session.get(Doctors.class, id); // returns Doctor type of object
	    if(d == null)
		{
			System.out.println("There is no Record Found For This ID.");
		}
		else
		{
			Transaction transaction = session.beginTransaction();

		    System.out.print("Enter the New Experience for Doctor_Id " + id + ": ");
		    int experience = scanner.nextInt();
		    d.setExperience(experience);
		    session.update(d);

		    System.out.println("Doctor Experience Updated Successfully.");
		    transaction.commit();
		    session.close();
		}
	}
	

	public static void updateDoctor()
	{
		scanner = new Scanner(System.in);
		
		//step1
		Configuration config = new Configuration();
		config.configure();
				
		//step2
		SessionFactory factory = config.buildSessionFactory();
		session = factory.openSession();

		while(true)
		{
			System.out.println("\nSelect What you Want to Update in Doctor's Record.");
			System.out.println("---------------------------------------------------");
			System.out.println("1. Doctor Name.");
			System.out.println("2. Doctor Department.");
			System.out.println("3. Doctor Experience.");
			System.out.println("4. Exit.");
			System.out.print("Enter your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					updateDoctorName();
					System.out.println();
					break;
					
				case 2:
					updateDoctorDepartment();
					System.out.println();
					break;
					
				case 3:
					updateDoctorExperience();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Invalid Choice! ..Enter a Valid Choice.");
					System.out.println();
					break;			
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//--------------------------------------------------------------------------------------------------------------------
	public static void deleteDoctorById()
	{
	    scanner = new Scanner(System.in);

	    System.out.print("Enter Doctor id: ");
	    int id = scanner.nextInt();
	    scanner.nextLine();

	    Doctors d = session.get(Doctors.class, id);

	    if (d == null)
	    {
	        System.out.println("Doctor with ID " + id + " does not exist.");
	        return;
	    }

	    else
	    {
	    	System.out.println(". . . . . . . . . . . . . . . . . . .  A  L  E  R  T  . . . . . . . . . . . . . . . . . . .");
	    	System.out.println("You Are About to Permanently Delete a Particular Doctor Record and Associated Appointments");
			System.out.print("Do You Wish to Continue? (Yes/No) : ");
			
			String confirmation = scanner.nextLine();
			
			if(confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))
			{
				Transaction tx = session.beginTransaction();

			    // Delete associated appointments first
			    Query deleteAppointmentsQuery = session.createQuery("delete from Appointments where doctor_id = :id");
			    deleteAppointmentsQuery.setParameter("id", id);
			    int deletedAppointmentsCount = deleteAppointmentsQuery.executeUpdate();
			    
			    // Now, delete the doctor
			    String name = d.getName();
			    session.delete(d);
			    
			    tx.commit();
			    
			    System.out.println("Doctor " + name + " and " + deletedAppointmentsCount + " Associated Appointments Removed.");
			}
			
			else
			{
				System.out.println("Deletion Cancelled!");
			}
			
	    }
	    
	}
	
	public static void deleteAllDoctors()
	{
	   scanner = new Scanner(System.in); // Create a new scanner object

	   String hqlQuery = "select count(*) from Doctors";
	   Long count = session.createQuery(hqlQuery, Long.class).getSingleResult();
	    
	    if(count == 0)
	    {
	    	System.out.println("There are no Doctors to Delete.");
	        return;
	    }
	    
	    System.out.println(". . . . . . . . . . . . . . . .  A   L   E   R   T . . . . . . . . . . . . . . . .");
	    System.out.println("You are about to permanently delete all Doctor records and associated appointments");
	    System.out.print(" Do you wish to continue? (Yes/No): ");

	    String confirmation = scanner.nextLine();

	    if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y"))
	    {
	        Transaction tx = session.beginTransaction();

	        // Delete all appointments
	        Query deleteAppointmentsQuery = session.createQuery("delete from Appointments");
	        int deletedAppointmentsCount = deleteAppointmentsQuery.executeUpdate();

	        // Now, delete all Doctors
	        Query deleteDoctorsQuery = session.createQuery("delete from Doctors");
	        int deletedDoctorsCount = deleteDoctorsQuery.executeUpdate();

	        tx.commit();

	        System.out.println(deletedDoctorsCount + " Doctors and " + deletedAppointmentsCount + " associated appointments removed.");
	    }
	    else
	    {
	        System.out.println("Deletion cancelled.");
	    }
	}

	public static void deleteDoctor()
	{
		while(true)
		{
			System.out.println("\nSelect How You Want to Delete Doctors Record:");
			System.out.println("----------------------------------------------");
			System.out.println("1. Delete Particular Doctor.");
			System.out.println("2. Delete All Doctors.");
			System.out.println("3. Exit.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					deleteDoctorById();
					System.out.println();
					break;
					
				case 2:
					deleteAllDoctors();
					System.out.println();
					break;
					
				case 3:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//============================================================================================================================
	public static void createAppointment()
	{
		scanner = new Scanner(System.in);
		
		// Initializing the configuration object
	    configuration = new Configuration();
	    configuration.configure();
	    
		sessionfactory = configuration.buildSessionFactory();
		session = sessionfactory.openSession();
		
		Appointments a = new Appointments();
		
		System.out.print("Enter Patient Id : ");
		int patientId = scanner.nextInt();
		
	// Check if the patient ID exists in the patients table
		
		Patients p = session.get(Patients.class, patientId);
		if(p == null)
		{
			System.out.println("Invalid Patient Id.. Please Enter a Valid Id.");
			return;
		}
	//If patient ID exists,Then 		
		a.setPatientId(patientId);
		
		
		System.out.print("Enter Doctor Id : ");
		int doctorId = scanner.nextInt();
		
	// Check if the Doctor ID exists in the patients table
		Doctors d = session.get(Doctors.class, doctorId);
		if(d == null)
		{
			System.out.println("Invalid Doctor Id.. Please Enter a Valid Id.");
			return;
		}
		
	//If patient ID exists,Then 				
		a.setDoctorId(doctorId);
		
		System.out.print("Enter Appointment Date (YYYY-MM-DD) : ");
		String date = scanner.next();
		a.setAppointmentDate(date);
		
		session.save(a);
		
    	Transaction tx = session.beginTransaction();

    	tx.commit();
    	
    // Check if the appointment is booked or not
        if (a.getId() > 0)
        {
            System.out.println("Appointment booked Successfully.");
        }
        else
        {
            System.out.println("Failed to Book Appointment!");
        }
		
	}
	
	
	public static void viewAppointmentById()
	{
		System.out.print("\nEnter Appointment Id: ");
		int id = scanner.nextInt();

		Appointments a = session.get(Appointments.class, id);
		if(a == null)
		{
			System.out.println("There Is No Record Found For This ID.");
		}
		else
		{
			System.out.println("\nAppointment Details:");
			System.out.println("======================");
			
			System.out.println("+----+------------+------------------+-----------+-----------------+------------------+");
			System.out.println("| Id | Patient_id | Patient_Name     | Doctor_id | Doctor_Name     | Appointment_date |");
			System.out.println("+----+------------+------------------+-----------+-----------------+------------------+");
	        System.out.printf("| %-2s | %-10s | %-16s | %-9s | %-15s | %-16s |\n", a.getId(),a.getPatientId(),a.getPatient().getName(),a.getDoctorId(),a.getDoctor().getName(),a.getAppointmentDate());
		    System.out.println("+----+------------+------------------+-----------+-----------------+------------------+");

		}
	}
	
	
	public static void viewAllAppointments()
	{
		String hqlQuery = "SELECT a.id, a.patients.id, a.patients.name, a.doctors.id, a.doctors.name, a.appointmentDate FROM Appointments a";

		List<Object[]> data = session.createQuery(hqlQuery).getResultList();
		
		if(data.isEmpty())
		{
			System.out.println("There is no Appointments Found.");
		}
		
		else
		{
			System.out.println("\nAppointment Details:");
			System.out.println("======================");
			
			System.out.println("+----+------------+------------------+-----------+-----------------+------------------+");
			System.out.println("| Id | Patient_id | Patient_Name     | Doctor_id | Doctor_Name     | Appointment_date |");
			System.out.println("+----+------------+------------------+-----------+-----------------+------------------+");

			
	    for (Object[] row : data)
	    {
	        System.out.printf("| %-2s | %-10s | %-16s | %-9s | %-15s | %-16s |\n", row[0], row[1], row[2], row[3], row[4], row[5]);
	    }
		    System.out.println("+----+------------+------------------+-----------+-----------------+------------------+");
		}
	}
	
	
	public static void readAppointment()
	{
		while(true)
		{
			System.out.println("\nSelect How You Want To Read Appointments Record:");
			System.out.println("--------------------------------------------------");
			System.out.println("1. View Particular Appointment.");
			System.out.println("2. View All Appointments.");
			System.out.println("3. Exit.");
			System.out.print("\nEnter your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					viewAppointmentById();
					System.out.println();
					break;
					
				case 2:
					viewAllAppointments();
					System.out.println();
					break;
					
				case 3:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//--------------------------------------------------------------------------------------------------------------------
	public static void updateAppointmentPatient()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		System.out.print("Enter Appointment Id to Update Patient: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Transaction transaction = session.beginTransaction();
		
		Appointments a = session.get(Appointments.class, id); //returns Doctor type of object
		if(a == null)
		{
			System.out.println("Invalid Appointment Id.. Please Enter a Valid Id.");
			transaction.rollback();
			session.close();
			return;
		}
		else
		{
			System.out.print("Enter the New Patient_Id for Appointment_Id "+id+": ");
			int patientId = scanner.nextInt();
			
			Patients p = session.get(Patients.class, patientId);
			if(p == null)
			{
				System.out.println("Invalid Patient Id.. Please Enter a Valid Id.");
				return;
			}
			else
			{
				a.setPatientId(patientId);
				
				session.update(a);
//				session.merge(d);
				
				transaction.commit();
				session.close();
				System.out.println("Patient Name Updated Successfully.");
			}
		}
	}
	
	
	public static void updateAppointmentDoctor()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration();
		configuration.configure();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		System.out.print("Enter Appointment Id to Update Doctor: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Transaction transaction = session.beginTransaction();

		Appointments a = session.get(Appointments.class, id); //returns Doctor type of object
		if(a == null)
		{
			System.out.println("Invalid Appointment Id.. Please Enter a Valid Id.");
			transaction.rollback();
			session.close();
			return;
		}
		else
		{
			System.out.print("Enter the New Doctor_Id for Appointment_Id "+id+": ");
			int doctorId = scanner.nextInt();
			
			Doctors d = session.get(Doctors.class, doctorId);
			if(d == null)
			{
				System.out.println("Invalid Doctor Id.. Please Enter a Valid Id.");
				return;
			}
			else
			{
				a.setDoctorId(doctorId);
				
				session.update(a);
//				session.merge(d);
				
				transaction.commit();
				session.close();
				System.out.println("Doctor Name Updated Successfully.");
			}
		}
	}
	
	
	public static void updateAppointmentDate()
	{
	    scanner = new Scanner(System.in);

	    Configuration configuration = new Configuration();
	    configuration.configure();
	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    Session session = sessionFactory.openSession();

	    System.out.print("Enter Appointment Id to Update Appointment Date: ");
	    int id = scanner.nextInt();
	    scanner.nextLine();

	    Transaction transaction = session.beginTransaction();

	    Appointments a = session.get(Appointments.class, id);
	    if (a == null)
	    {
	        System.out.println("Invalid Appointment Id. Please Enter a Valid Id.");
	        transaction.rollback();
	        session.close();
	        return;
	    }

	    System.out.print("Enter the New Date (YYYY-MM-DD) for Appointment_Id " + id + ": ");
	    String newDate = scanner.nextLine();
	    a.setAppointmentDate(newDate);

	    session.update(a);
	    transaction.commit();
	    session.close();

	    System.out.println("Appointment Date Updated Successfully.");
	}
	

	public static void updateAppointment()
	{
		scanner = new Scanner(System.in);
		
		//step1
		Configuration config = new Configuration();
		config.configure();
				
		//step2
		SessionFactory factory = config.buildSessionFactory();
		session = factory.openSession();

		while(true)
		{
			System.out.println("\nSelect What you Want to Update in Appointment's Record.");
			System.out.println("---------------------------------------------------------");
			System.out.println("1. Patient Details.");
			System.out.println("2. Doctor Details.");
			System.out.println("3. Appointment Date.");
			System.out.println("4. Exit.");
			System.out.print("Enter your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					updateAppointmentPatient();
					System.out.println();
					break;
					
				case 2:
					updateAppointmentDoctor();
					System.out.println();
					break;
					
				case 3:
					updateAppointmentDate();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Invalid Choice! ..Enter a Valid Choice.");
					System.out.println();
					break;			
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------
	public static void deleteAppointmentById()
	{
		scanner = new Scanner(System.in);

		System.out.print("Enter Appointment id to Cancel the Appointment: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Appointments a = session.get(Appointments.class, id);
		
		if(a == null)
		{
			System.out.println("Appointment With ID "+id+" Does Not Exist!");
		}
		else
		{
			System.out.println(". . . . . . . . . . . .  A  L  E  R  T   . . . . . . . . . . .");
	    	System.out.println("You Are About to Permanently Delete a Particular Appointment");
			System.out.print("Do You Wish to Continue? (Yes/No) : ");
			
			String confirmation = scanner.nextLine();
			
			if(confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y"))
			{
				Transaction transaction = session.beginTransaction();
				session.delete(a);
				
				transaction.commit();
				
				System.out.println("Appointment With ID "+id+ "Deleted Successfully.");
			}
			else
			{
				System.out.println("Deletion Cancelled!");
			}
		}
    	
	}
	
	
	public static void deleteAllAppointments()
	{
	    scanner = new Scanner(System.in); // Create a new scanner object

	    String hqlQuery = "select count(*) from Appointments";
	    Long count = session.createQuery(hqlQuery, Long.class).getSingleResult();
	    
	    if(count == 0)
	    {
	    	System.out.println("There are no Appointments to Delete.");
	        return;
	    }
	    
	    System.out.println(". . . . . . . . . . . . . . . .  A   L   E   R   T . . . . . . . . . . . . . . . .");
	    System.out.println("You are about to permanently delete all the Appointments");
	    System.out.print(" Do you wish to continue? (Yes/No): ");

	    String confirmation = scanner.nextLine();

	    if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y"))
	    {
	        Transaction tx = session.beginTransaction();

	        // Delete all appointments
	        Query deleteAppointmentsQuery = session.createQuery("delete from Appointments");
	        int deletedAppointmentsCount = deleteAppointmentsQuery.executeUpdate();

	        tx.commit();

	        System.out.println("All the "+ deletedAppointmentsCount + " Appointments are Removed.");
	    }
	    else
	    {
	        System.out.println("Deletion cancelled.");
	    }
	}
	
	
	public static void deleteAppointment()
	{
		while(true)
		{
			System.out.println("\nSelect How You Want to Delete Appointment Record:");
			System.out.println("---------------------------------------------------");
			System.out.println("1. Delete Particular Appointment.");
			System.out.println("2. Delete All Appointments.");
			System.out.println("3. Exit.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					deleteAppointmentById();
					System.out.println();
					break;
					
				case 2:
					deleteAllAppointments();
					System.out.println();
					break;
					
				case 3:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//============================================================================================================================
	
	public static void generateBill()
	{
		scanner = new Scanner(System.in);
		
		configuration = new Configuration(); //class reads both the entity class and config  file
		configuration.configure(); //checks config file syntax
		    	
		//Interface. it takes metedata and build connection
		sessionfactory = configuration.buildSessionFactory();
		//session -> time period b/n start and end.
		session = sessionfactory.openSession();
		
		Billing b = new Billing();
		
		System.out.print("Enter Appointment Id: ");
		int appointmentId = scanner.nextInt();
		b.setAppointmentId(appointmentId);
		
		Appointments a = session.get(Appointments.class, appointmentId);
		
		
			if(a == null)
			{
				System.out.println("There is no Appointments found For This ID.");
			}
			else
			{
				System.out.println("Enter Billing Amount for Appointment id "+appointmentId+" :");
				long amount = scanner.nextLong();
				b.setAmount(amount);	
				
				session.save(b);
				
				Transaction transaction = session.beginTransaction();
				
				transaction.commit();
				
			
			
				if (b.getId() > 0)
		        {
		            System.out.println("Bill Generated Successfully.");
		        }
		        else
		        {
		            System.out.println("Failed to Generate Bill!");
		        }
			}
			
		
			
		
		
//   	catch(ConstraintViolationException e)
//	{
//		
//		System.out.println("Please check the Appointment Id you entered");
//	}
		
	}
	
	
	public static void viewAllBills()
	{
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	-------------------------------------------------------------------------------------------------------
	
				
	public static void create()
	{
		while(true)
		{
			System.out.println("\nSelect What You Want to Create:");
			System.out.println("---------------------------------");
			System.out.println("1. Patients Record.");
			System.out.println("2. Doctors Record.");
			System.out.println("3. Appointments Record.");
			System.out.println("4. Exit.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					createPatient();
					System.out.println();
					break;
					
				case 2:
					createDoctor();
					System.out.println();
					break;
					
				case 3:
					createAppointment();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
			
		}
		
	}
	
	public static void read()
	{
		while(true)
		{
			System.out.println("\nSelect What You Want to Read.");
			System.out.println("-------------------------------");
			System.out.println("1. Patients Record.");
			System.out.println("2. Doctors Record.");
			System.out.println("3. Appointments Record.");
			System.out.println("4. Exit.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					readPatient();
					System.out.println();
					break;
					
				case 2:
					readDoctor();
					System.out.println();
					break;
					
				case 3:
					readAppointment();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
			
		}
	}
	
	public static void update()
	{
		while(true)
		{
			System.out.println("\nSelect What You Want to Update:");
			System.out.println("---------------------------------");
			System.out.println("1. Patients Record.");
			System.out.println("2. Doctors Record.");
			System.out.println("3. Appointments Record.");
			System.out.println("4. Exit.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					updatePatient();
					System.out.println();
					break;
					
				case 2:
					updateDoctor();
					System.out.println();
					break;
					
				case 3:
					updateAppointment();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
	
	public static void delete()
	{
		while(true)
		{
			System.out.println("\nSelect What You Want to Delete:");
			System.out.println("---------------------------------");
			System.out.println("1. Patients Record.");
			System.out.println("2. Doctors Record.");
			System.out.println("3. Appointments Record.");
			System.out.println("4. Exit.");
			System.out.print("\nEnter Your Choice: ");
			
			int choice = scanner.nextInt();
			
			try
			{
				switch(choice)
				{
				case 1:
					deletePatient();
					System.out.println();
					break;
					
				case 2:
					deleteDoctor();
					System.out.println();
					break;
					
				case 3:
					deleteAppointment();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
//....................................................................................................	
	public static void main(String[] args)
	{
		scanner = new Scanner(System.in);
		
		//step1
		Configuration config = new Configuration();
		config.configure();
		
		//step2
		SessionFactory factory = config.buildSessionFactory();
		session = factory.openSession();
		
		System.out.println("===========================");
		System.out.println("HOSPITAL MANAGEMENT SYSTEM");
		System.out.println("===========================");
		
		System.out.println("Enter Admin-Username: ");
		String userName1 = scanner.next();
		System.out.println("Enter Admin-Password: ");
		int password1 = scanner.nextInt();
		
//		Admin admin = new Admin();
		
		if(userName1.equals(Admin.getUserName()) && password1 == Admin.getPassword())
		{
			while(true)
			{
				System.out.println("\n======================================");
				System.out.println("WELCOME TO HOSPITAL MANAGEMENT SYSTEM");
				System.out.println("======================================");
				System.out.println("Select What You Want TO Do");
				System.out.println("1. Create Records.");
				System.out.println("2. Read Records.");
				System.out.println("3. Update Records.");
				System.out.println("4. Delete Records.");
				System.out.println("5. Billing");
				System.out.println("6. Exit.");
				System.out.print("\nEnter Your Choice: ");
				
				try
				{
					int choice = scanner.nextInt();
					
					switch(choice)
					{
					case 1:
						create();
						System.out.println();
						break;
						
					case 2:
						read();
						System.out.println();
						break;
						
					case 3:
						update();
						System.out.println();
						break;	
						
					case 4:
						delete();
						System.out.println();
						break;
						
					case 5:
						generateBill();
						System.out.println();
						break;
						
					case 6:
						return;
						
					default:
						System.out.println("Invalid Choice! ..Enter a Valid Choice.");
						System.out.println();
						break;
					}
				}
				
				catch(InputMismatchException e)
				{
					System.out.println("Please Enter a Integer Value.");
					System.out.println();
					scanner.nextLine();
				}
			
			
			}
		}
		else
		{
			System.out.println("Incorrect Username or Password");
		}
		
		
//		while(true)
//		{
//			System.out.println("===========================");
//			System.out.println("HOSPITAL MANAGEMENT SYSTEM");
//			System.out.println("===========================");
//			System.out.println("Select What You Want TO Do");
//			System.out.println("1. Create Records.");
//			System.out.println("2. Read Records.");
//			System.out.println("3. Update Records.");
//			System.out.println("4. Delete Records.");
//			System.out.println("5. Exit.");
//			System.out.print("\nEnter Your Choice: ");
//			
//			try
//			{
//				int choice = scanner.nextInt();
//				
//				switch(choice)
//				{
//				case 1:
//					create();
//					System.out.println();
//					break;
//					
//				case 2:
//					read();
//					System.out.println();
//					break;
//					
//				case 3:
//					update();
//					System.out.println();
//					break;	
//					
//				case 4:
//					delete();
//					System.out.println();
//					break;
//					
//				case 5:
//					return;
//					
//				default:
//					System.out.println("Invalid Choice! ..Enter a Valid Choice.");
//					System.out.println();
//					break;
//				}
//			}
//			
//			catch(InputMismatchException e)
//			{
//				System.out.println("Please Enter a Integer Value.");
//				System.out.println();
//				scanner.nextLine();
//			}
//		}
	}
}
