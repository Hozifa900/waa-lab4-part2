package mvc.data;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import mvc.domain.Account;
import mvc.domain.AccountCommand;

@Repository
public class AccountRepository {
    private Map<Long, Account> accounts = new HashMap<Long, Account>();

    public AccountRepository() {
        accounts.put(123L, new Account(123, "James Brown"));
        accounts.put(456L, new Account(456, "Mary Jones"));
    }

    public Account getAccount(Long accountnumber) {
        Account account = accounts.get(accountnumber);
        return account;
    }

    public void deleteAccount(Long accountnumber) {
        Account account = accounts.get(accountnumber);
        accounts.remove(accountnumber);
    }

    public Account addAccount(AccountCommand accountCommand) {
        Account account = new Account(accountCommand.getAccountNumber(), accountCommand.getAccountHolder());
        accounts.put(account.getAccountnumber(), account);
        return account;
    }

    public Account addDeposit(AccountCommand accountCommand) {
        Account account = accounts.get(accountCommand.getAccountNumber());
        account.deposit(accountCommand.getAmount());
        return account;
    }

    public Account addWithdraw(AccountCommand accountCommand) {
        Account account = accounts.get(accountCommand.getAccountNumber());
        account.withdraw(accountCommand.getAmount());
        return account;
    }

}
