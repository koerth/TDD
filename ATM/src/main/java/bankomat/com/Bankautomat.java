package bankomat.com;

public class Bankautomat {

    //Variables, nullified 
    CardReader cardReader;
    Bank bank;
    User currentUser;
    String activeCard;

    //Cardreader
    Bankautomat (CardReader cardReader, Bank bank){
        this.cardReader = cardReader;
        this.bank = bank;
    }

    //Checks if card is inserted
    public String cardInserted() {
        if (activeCard == null)
            getCardID();

        if(bank.checkCardID(activeCard)){
            currentUser = bank.getUser(activeCard);
            return "Welcome " + currentUser.getName() + ", enjoy your stay!";
        }
        return "The card is blocked.";
    }

    //Checks if pin is entered, handles
    public String pinEntered() {
        if (activeCard == null)
            getCardID();

        int pin = cardReader.enterPIN();
        if (bank.pinApproved(activeCard, pin)){
            return "You are now logged in";
        } else {
            int attemptsRemaining = bank.pinAttemptsRemaining(activeCard);
            if(attemptsRemaining == 0)
                return "Incorrect PIN, your card has now been blocked.";

            return "Incorrect PIN, you have " + attemptsRemaining + " attempts remaining";
        }
    }

    //Checks the amount
    public String checkAmount() {
        if (activeCard == null)
            getCardID();

        int amount = bank.getAmount(activeCard);

        return "You currently have $" + amount + " on your account.";
    }

    //Function for depositing
    public void depositMoney() {
        if (activeCard == null)
            getCardID();

        int amount = cardReader.getAmount();

        if(amount > 0)
            bank.depositMoneyCardNumber(activeCard, amount);
    }

    //Function for withdrawing
    public String withdrawMoney() {
        if (activeCard == null)
            getCardID();

        int amount = cardReader.getAmount();
        int amountInBank = bank.getAmount(activeCard);

        if(amountInBank > amount){
            bank.withdrawMoneyCardNumber(activeCard, amount);
            return "Success";
        }

        return "There was not enough money in the bank account";
    }

    //Logout
    public void logout(){
        cardReader.dispenseCard();
    }

    //Welcome to the bank-message
    public String printBankMessage(){
        return "Welcome to " + Bank.getBankName();
    }

    //Getting the card ID
    private void getCardID(){
        activeCard = cardReader.getID();
    }
}
