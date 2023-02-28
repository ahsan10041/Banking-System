package account;
import java.util.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
public class customer
{
    private String name;
    private String address;
    private String phone_number;
    private int acc_number;
    private HashMap<Integer,String> records;
    public customer()
    {
        name = "";
        address = "";
        phone_number = "";
        acc_number = 0;
        records = new HashMap<Integer, String>();
    }
    public customer(int acc_number,String name, String phone_number, String address)
    {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.acc_number = acc_number;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getPhone_number()
    {
        return phone_number;
    }
    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }
    public int getAcc_number()
    {
        return acc_number;
    }
    public void setAcc_number(int acc_number)
    {
        this.acc_number = acc_number;
    }
    public void setRecords(int unique_ID,String name,String phone_number,String address)
    {
        this.records.put(unique_ID , "Name               : "       + name +"\n"
                + "Account Number     : xxxxxx" + unique_ID +"\n"
                + "Phone Number       : "       + phone_number +"\n"
                + "Address            : "       + address);
        this.setAcc_number(unique_ID);
        this.setAddress(address);
        this.setName(name);
        this.setPhone_number(phone_number);
    }
    public HashMap<Integer,String> getRecords()
    {
        return this.records;
    }
    public void customer_accounts(int unique_ID)
    {
        switch(unique_ID)
        {
            case 1041: this.setRecords(1041, "Ahsan", "03090715598", "F6-ISB");
                break;
            case 1058: this.setRecords(1058, "Azan", "03090714498", "I8-ISB");
                break;
            case 1173: this.setRecords(1173, "Zeeshan", "03070717798", "E11-ISB");
                break;
            case 1111: this.setRecords(1111, "Dheeraj", "03050717465", "G13-ISB");
                break;

            default: this.setRecords(unique_ID,this.getName(),this.getPhone_number(),this.getAddress());
        }
    }
    @SuppressWarnings("resource")
    public void removeAccount(int unique_ID)
    {
        if(this.records.containsKey(unique_ID))
        {
            System.out.println("Account details of " + unique_ID + " : ");
            this.customer_details(unique_ID);
            System.out.println("*NOTE : Once it deleted, it cannot be recovered*"
                    + "\nAre you sure you want to delete? (y/n)");
            Scanner in_5 = new Scanner(System.in);
            char remove = in_5.next().charAt(0);
            if (remove == 'y')
                this.records.remove(unique_ID);
        }
        else
            System.out.println("No account with unique ID "+unique_ID+" exists");
    }
    public void loginRecords(String name,int unique_ID)
    {
        @SuppressWarnings("unused")
        boolean file_creation = false;
        try
        {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile())
                file_creation = true;
            else
                file_creation = false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try (FileWriter f = new FileWriter("filename.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);)
        {
            p.println(name + " " + unique_ID);
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }

    }
    public int[] readFromFile(String name, int unique_ID)
    {
        int[] id_find = {0,0};
        boolean correct = false;
        String last_id = "";
        BufferedReader reader;
        String data = name + " " + Integer.toString(unique_ID);
        try
        {
            reader = new BufferedReader(new FileReader("filename.txt"));
            String line;
            while ((line = reader.readLine()) != null)
            {
                // read next line
                last_id = line;
                if(data.equals(line))
                    correct = true;
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int account_number = Integer.parseInt(last_id.substring(last_id.length() - 4))+1;
        id_find[0] = account_number;
        if (!correct)
        {
            id_find[1] = 1;
        }
        else
            id_find[1] = 0;
        return id_find;
    }

    @SuppressWarnings("resource")
    public boolean login()
    {
        Scanner ob = new Scanner(System.in);
        System.out.println(" ________");
        System.out.println("|__Name__|");
        String name = ob.nextLine();
        this.setName(name);
        System.out.println(" _____________");
        System.out.println("|__Unique ID__|");
        int unique_id = ob.nextInt();
        this.setAcc_number(unique_id);
        //ob.close();
        this.customer_accounts(unique_id);
        int[] check = this.readFromFile(name, unique_id);
        if(check[1] == 0)
        {
            System.out.println("Welcome "+ name);
            return true;
        }
        else
        {
            System.out.println("wrong Name or Id");
            return false;
        }
    }
    @SuppressWarnings("resource")
    public void register()
    {
        Scanner in = new Scanner(System.in);
        System.out.println(" ________");
        System.out.println("|__Name__|");
        String name = in.nextLine();
        this.setName(name);

        System.out.println(" ________________");
        System.out.println("|__Phone Number__|");
        String phone_number = in.nextLine();
        this.setPhone_number(phone_number);

        System.out.println(" ___________");
        System.out.println("|__Address__|");
        String address = in.nextLine();
        this.setAddress(address);

        //generating unique account number
        int[] account_number = this.readFromFile("",0);
        this.setAcc_number(account_number[0]);
        System.out.println(" _____________");
        System.out.println("|__User Data__|");
        System.out.println("\n>> Your Account Number is - " + "210201" + this.getAcc_number());
        System.out.println(">> Your Unique ID is      - "+account_number[0]);
        this.setRecords(acc_number, name, phone_number, address);
        this.loginRecords(this.getName(),this.getAcc_number());
    }
    public void customer_details(int unique_ID)
    {
        System.out.println(getRecords().get(unique_ID));
    }
}