package company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ATMServiceTest {
    private Bank bank;
    private BankAccount bankAccount;
    private BankDatabase bankDatabase;

    BankAccount acc1 = new BankAccount(0001, 1234, false, 10000.00, "Nordea", false);

    @BeforeEach
    void setup() {
        // bankAccount = mock(BankAccount.class);
        bankDatabase = mock(BankDatabase.class);
        bank = new Bank(bankDatabase);
    }

    // Checking balance
    @Test
    void should_ShowBalance_when_CheckBalance() {
        when(bankDatabase.getAccountByID(0001)).thenReturn(acc1);

        double expected = 10000.00;
        double actual = bank.checkBalance(0001);

        // Prints out results, comparing expected to actual results
        assertEquals(expected, actual);
    }

    // Checking withdrawl
    @Test
    void should_Return9900_when_Withdraw100() {
        when(bankDatabase.getAccountByID(0001)).thenReturn(acc1);

        bank.withdrawAmount(0001, 100.00);

        double expected = 9900.00;
        double actual = bank.checkBalance(0001);

        // Verifierar att den körs en gång
        verify(bank, times(1)).withdrawAmount(0001, expected);

        // Prints out results, comparing expected to actual results
        assertEquals(expected, actual);
    }

    // Checking adding
    @Test
    void should_Return11000_when_Add1000() {
        when(bankDatabase.getAccountByID(0001)).thenReturn(acc1);

        bank.executeLogin(0001, 1234);

        double addValue = 1000.00;

        bank.addAmount(0001, addValue);

        double expected = 11000.00;
        double actual = bank.checkBalance(0001);

        // Verifierar att den körs en gång
        verify(bank, times(1)).addAmount(0001, expected);

        // Prints out results, comparing expected to actual results
        assertEquals(expected, actual);
    }

    // Checking login
    @Test
    void should_isLoggedInBecomeTrue_when_CorrectPin() {
        when(bankDatabase.getAccountByID(0001)).thenReturn(acc1);

        bank.executeLogin(0001, 1234);

        // Prints out results, comparing expected to actual results
        assertTrue(acc1.isLoggedIn());
    }

    // Checking security
    @Test
    void should_isLockedBecomeTrue_when_WrongPin3Tries() {
        when(bankDatabase.getAccountByID(0001)).thenReturn(acc1);

        bank.executeLogin(0001, 1155);
        bank.executeLogin(0001, 1255);
        bank.executeLogin(0001, 1666);
        bank.executeLogin(0001, 1666);

        // Prints out results, comparing expected to actual results
        assertTrue(acc1.isLocked());
    }

    @Test
    void should_ReturnNameOfBank(){
        try {
            MockedStatic<Bank> mockedStaticConverter = mockStatic(Bank.class);

            mockedStaticConverter.when(() -> bankAccount.bankName()).thenReturn("Nordea");

            String expected = "Nordea";
            String actual = bankAccount.bankName();

            assertEquals(expected, actual);

            System.out.println(bankAccount.bankName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Updatering, tillägg av testcase
    // Printar ut två gånger, varav andra gången sker det en withdrawl före
    @Test
    void should_ReturnNewValue() {
        when(bankDatabase.getAccountByID(0001)).thenReturn(acc1);

        bank.withdrawAmount(0001, 100.00);

        double expectedRes = 9900.00;
        double actualRes = bank.checkBalance(0001);

        // Prints out results, comparing expected to actual results
        assertEquals(expectedRes, actualRes);
    }

    @Test
    void should_logIn() {
        when(bankDatabase.getAccountByID(0001)).thenReturn(acc1);

        bank.executeLogin(0001, 1234);

        verify(bankDatabase, times(1)).getAccountByID(0001);

        // Prints out results, comparing expected to actual results
        assertTrue(acc1.isLocked());
    }

    @Test
    void should_changeValues() {
        when(bankDatabase.getAccountByID(0001)).thenReturn(acc1);

        // Nya värden
        int newPin = 4321;
        double newBalance = 100000.00;
        String newBank = "TestBank";

        // Kör funktionen som sätter värdet i BankAccount för accountID
        bank.saveChanges(0001, newPin, newBalance, newBank);
        verify(bankDatabase, times(1)).getAccountByID(0001);
    }

}
