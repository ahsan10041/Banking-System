package account;

import java.util.Scanner;

public class Main
{
    @SuppressWarnings("resource")
    public static char goBack()
    {
        System.out.println(">> Perform operations again? (y/n)");
        Scanner in_4 = new Scanner(System.in);
        char again = in_4.next().charAt(0);
        while(again!='n' && again!='y')
        {
            System.out.println("!!! Input only (y/n)");
            again = in_4.next().charAt(0);
        }
        //if(again == 'n')
        //System.exit(0);
        return again;
    }
    @SuppressWarnings("resource")
    public static char an_account(accountSystem acc)
    {

        char again = 'n';
        while(again == 'n')
        {
            Scanner in_2 = new Scanner(System.in);
            System.out.println(" ___");
            System.out.println("|_1_|----Register");
            System.out.println(" ___");
            System.out.println("|_2_|----Login");
            System.out.println(" ___");
            System.out.println("|_3_|----Delete");
            System.out.println(" ___");
            System.out.println("|_4_|----Main Menu");
            System.out.println(" ___");
            System.out.println("|_5_|----Exit");
            System.out.println("\n>> Enter Choice - ");
            int nextInt_1 = in_2.nextInt();
            switch(nextInt_1)
            {
                case 1:
                    acc.register();
                case 2:
                    System.out.println("\t _______________\n"
                            +"\t|     LOGIN     |\n"
                            +"\t|_______________|\n");

                    boolean login = acc.login();
                    while(login == false)
                    {
                        System.out.println("Do you want to Input again? (y/n)");
                        again = in_2.next().charAt(0);
                        if(again == 'n')
                            System.exit(0);
                        login = acc.login();
                    }
                    again = 'y';
                    while(again=='y')
                    {
                        System.out.println("*-------------------------------------------------------------------------------------*\n"
                                +"| 1)  Cash Deposit                         | 2)  Cash Withdrawl                       |\n"
                                +"|------------------------------------------|------------------------------------------|\n"
                                +"| 3)  Calculate Zakat/Tax                  | 4)  Calculate Interest(only for savings) |\n"
                                +"|------------------------------------------|------------------------------------------|\n"
                                +"| 5)  Check Balance                        | 6)  Transfer                             |\n"
                                +"|------------------------------------------|------------------------------------------|\n"
                                +"| 7)  Print Statement                      | 8)  All Deductions                       |\n"
                                +"|------------------------------------------|------------------------------------------|\n"
                                +"| 9)  Main menu                            | 10) Exit                                 |\n"
                                +"*-------------------------------------------------------------------------------------*\n");
                        System.out.println(">> Enter Choice - ");
                        int nextInt_2 = in_2.nextInt();
                        switch(nextInt_2)
                        {
                            case 1:
                                System.out.println("Amount to deposit - ");
                                double amount = in_2.nextInt();
                                acc.makeDeposit(amount);
                                again = goBack();
                                break;

                            case 2:
                                System.out.println("Amount to Withdrawl - ");
                                amount = in_2.nextInt();
                                acc.makeWithdrawl(amount);
                                again = goBack();
                                break;

                            case 3:
                                if(acc instanceof savingAccount)
                                {
                                    savingAccount s = (savingAccount) acc;
                                    s.calculateZakat();
                                }
                                else
                                {
                                    checkingAccount c = (checkingAccount) acc;
                                    c.calculateTax();
                                }
                                again = goBack();
                                break;

                            case 4:
                                if(acc instanceof savingAccount)
                                {
                                    savingAccount s = (savingAccount) acc;
                                    s.calculateInterest();
                                }
                                again = goBack();
                                break;

                            case 5:
                                acc.checkBalance();
                                again = goBack();
                                break;

                            case 6:
                                System.out.println("Amount you want to transfer - ");
                                double transfer_amount = in_2.nextDouble();
                                System.out.println("Enter 10 digits account number - ");
                                Scanner in_3 = new Scanner(System.in);
                                String acc_number = in_3.nextLine();
                                savingAccount v = new savingAccount();
                                acc.transferAmount(transfer_amount ,v, acc_number);
                                again = goBack();
                                break;

                            case 7:
                                acc.printStatment();
                                again = goBack();
                                break;

                            case 8:
                                acc.displayAlldeductions();
                                again = goBack();
                                break;

                            case 9:
                                again='n';
                                break;

                            case 10:
                                System.exit(0);
                                break;

                            default :
                                System.out.println("Wrong Input! Input Again");
                                nextInt_2 = in_2.nextInt();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Login into your Account to delete it -");
                    boolean sign_in = acc.login();
                    while(sign_in == false)
                    {
                        System.out.println("Do you want to Input again? (y/n)");
                        char input = in_2.next().charAt(0);
                        if(input == 'n')
                            System.exit(0);
                        login = acc.login();
                    }
                    System.out.println("Unique ID of the account again - ");
                    int unique_ID = in_2.nextInt();
                    acc.removeAccount(unique_ID);
                    break;

                case 4:
                    return 'y';

                case 5:
                    System.exit(0);
                    break;

                default :
                    System.out.println("Wrong Input! Input Again");
                    nextInt_1 = in_2.nextInt();
            }
        }
        return 'y';
    }
    @SuppressWarnings("resource")
    public static void main(String[] args)
    {
        Scanner in_1 = new Scanner(System.in);
        char again = 'y';
        while(again=='y')
        {
            int choice;
            System.out.println(" ___");
            System.out.println("|_1_|----Savings");
            System.out.println(" ___");
            System.out.println("|_2_|----Checkings");
            System.out.println(" ___");
            System.out.println("|_3_|----Exit");
            System.out.println("\n>> Enter Choice - ");
            choice = in_1.nextInt();
            switch(choice)
            {
                case 1:
                    account.savingAccount s = new account.savingAccount();
                    again = an_account(s);
                    break;
                case 2:
                    account.checkingAccount c = new account.checkingAccount();
                    again = an_account(c);
                case 3:
                    System.exit(0);
            }
        }
    }
}
