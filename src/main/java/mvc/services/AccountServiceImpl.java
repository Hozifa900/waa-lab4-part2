package mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mvc.data.AccountRepository;
import mvc.domain.Account;
import mvc.domain.AccountCommand;

@Service
public class AccountServiceImpl {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount(Long accountnumber) {
        return accountRepository.getAccount(accountnumber);
    }

    public void deleteAccount(Long accountnumber) {
        accountRepository.deleteAccount(accountnumber);
    }

    public Account handlePost(AccountCommand accountCommand) {
        String operation = accountCommand.getOperation();
        Long accountNumber = accountCommand.getAccountNumber();
        String accountHolder = accountCommand.getAccountHolder();

        if (operation.equals("create")) {
            // create account
            if (accountNumber != 0 && accountHolder != null) {
                return accountRepository.addAccount(accountCommand);
            }
        }
        if (operation.equals("deposit")) {
            return accountRepository.addDeposit(accountCommand);
        }
        if (operation.equals("withdraw")) {
            return accountRepository.addWithdraw(accountCommand);
        }
        // create anonymous account
        return new Account();

    }
}
