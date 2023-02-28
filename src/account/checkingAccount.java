package account;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class checkingAccount extends account.accountSystem{

    private double tax = 0;
    private HashMap<String,String> deduction;

    public checkingAccount()
    {
        // TODO Auto-generated constructor stub
        this.tax = 0;
        this.deduction = new HashMap<String,String>();
    }
    public void checkBalance()
    {
        System.out.println("Current Balance : "+getBalance());
    }
    public double getTax()
    {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = this.getBalance()*0.15;
    }
    public void makeDeposit(double deposit_amount)
    {
        // TODO Auto-generated method stub
        this.setBalance(getBalance()+deposit_amount);
        System.out.println("Depositing...");
        this.deduction.put(this.printDateTime()+ " Recieved Amount  = ", "PKR " + deposit_amount);
        System.out.println("\n\t*--NOTIFICATION----------------------*");
        String message = "\tPKR "+deposit_amount+" deposit"
                +" from MARKAZ "+this.getAddress()+"\n\tin your "
                + "A/C xxxxxx"+this.getAcc_number()
                +"\n\ton "+this.printDateTime()+" Bal: PKR "+this.getBalance();
        System.out.println(message);
        System.out.println("\t*------------------------------------*\n");
    }
    public void makeWithdrawl(double withdrawl_amount)
    {
        // TODO Auto-generated method stub
        this.setTransactionAmount(this.getTransactionAmount()+withdrawl_amount);
        if(withdrawl_amount<getBalance()+5000)
            this.deduction.put(this.printDateTime()+ " Withdrawl Amount = ", "PKR " + withdrawl_amount);
        this.setTransactionAmount(this.getTransactionAmount()+withdrawl_amount);
        if(withdrawl_amount<getBalance())
        {
            if(this.getBalance()==0)
            {
                this.setBalance(5000);
            }
            this.setBalance(getBalance()-withdrawl_amount);
            System.out.println("Processing...");
            System.out.println("\n\t*--NOTIFICATION----------------------*");
            String message = "\tPKR "+withdrawl_amount+" cash withdrawn "
                    +"\n\tfrom MARKAZ "+this.getAddress()+" from "
                    + "\n\tA/C xxxxxx"+this.getAcc_number()
                    +"\n\ton "+this.printDateTime()+" Bal: PKR "+this.getBalance();
            System.out.println(message);
            System.out.println("\t*------------------------------------*");
        }
    }
    public void calculateTax()
    {
        if(this.getBalance()>=20000)
        {
            this.setTax(this.getBalance()*(17/100));
            this.setBalance(this.getBalance()-this.getTax());
            this.setTransactionAmount(this.getTransactionAmount()+this.getTax());
            this.deduction.put(this.printDateTime()+" Tax Amount       = ", "PKR "+this.getTax());
            System.out.println("Calculating Tax...");
            System.out.println("\n\t*--NOTIFICATION----------------------*");
            String message = "\tPKR "+this.getTax()+" is debited as"
                    +"TAX AMOUNT from your A/C xxxxxx"+this.getAcc_number()
                    +"\n\tof "+this.getAddress()
                    +" on "+this.printDateTime()+"\n\tBal: PKR "+this.getBalance();
            System.out.println(message);
            System.out.println("\t*------------------------------------*\n");
        }
        else
            System.out.println("*--NOTE-----Not applicable to Calculate Tax");
    }
    public void printStatment()
    {
        /* Account number
         * Date/Time
         * Transaction Amount
         * Remaining Balance
         */
        customer_details(this.getAcc_number());
        System.out.println("Date/Time          : " + this.printDateTime());
        System.out.println("Transaction Amount : " + this.getTransactionAmount());
        System.out.println("Tax Amount         : " + this.getTax());
        System.out.println("Remaining Balance  : " + this.getBalance());

    }
    public void transferAmount(double transfer_amount,accountSystem acc,String acc_number)
    {
        // TODO transferring amount to another account
        int id_check = Integer.parseInt(acc_number.substring(acc_number.length() - 4));
        acc.customer_accounts(id_check);
        if(acc.getRecords().containsKey(id_check))
        {
            if(this.getBalance()<transfer_amount)
            {
                System.out.println("\tNot enough Balance!!!");
            }
            else
            {
                this.setBalance(getBalance()-transfer_amount);
                this.setTransactionAmount(this.getTransactionAmount()+transfer_amount);
                this.deduction.put(this.printDateTime()+" Transfer Amount   = ",
                        "PKR "+transfer_amount+" sent to "+acc.getName());
                acc.setBalance(getBalance()+transfer_amount);
                System.out.println("Sending...");
                System.out.println("\n\t*----NOTIFICATION----------------------------*");
                String message = "\tPKR "+transfer_amount+" sent to "+acc.getName()+"-"
                        +acc.getPhone_number()+"\n\tfrom your A/C xxxxxx"
                        +this.getAcc_number()+" of BR "+this.getAddress()
                        +"\n\ton "+this.printDateTime()+" Bal: PKR "+this.getBalance();
                System.out.println(message);
                System.out.println("\t*--------------------------------------------*");
            }
        }
        else
            System.out.println("\tAccount Not Found");
    }
    @Override
    public void displayAlldeductions()
    {
        Set<Map.Entry<String, String>> deductions = this.deduction.entrySet();
        System.out.println("\n*----NOTIFICATION---------------------------------------------------*");
        for (Map.Entry<String, String> d : deductions)
        {
            System.out.println("\t"+d.getKey()+d.getValue());

        }
        System.out.println("*-------------------------------------------------------------------*");
    }

}