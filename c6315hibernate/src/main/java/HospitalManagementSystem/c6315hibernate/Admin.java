package HospitalManagementSystem.c6315hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private static String userName = "admin";
    private static int password = 1234;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public static String getUserName()
    {
        return userName;
    }

//    public void setUserName(String userName)
//    {
//        this.userName = userName;
//    }

    public static int getPassword()
    {
        return password;
    }

//    public void setPassword(int password)
//    {
//        this.password = password;
//    }
}
