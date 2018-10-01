package com.example.demo.procesor;

import com.example.demo.dao.AccountDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.TickerDao;
import com.example.demo.domain.*;
import com.example.demo.exception.InvalidItemException;
import org.springframework.batch.item.ItemProcessor;

public class CustomerLookupItemProcessor implements ItemProcessor<Object, Object> {
    private CustomerDao customerDao;
    private TickerDao tickerDao;
    private AccountDao accountDao;

    public Object process(Object curItem) throws Exception {
        if(curItem instanceof Customer) {
            doCustomerUpdate((Customer) curItem);
        } else if(curItem instanceof Transaction){
            doTransactionUpdate((Transaction) curItem);
        } else {
            try {
                throw new InvalidItemException("An invalid item was received: " + curItem);
            } catch (InvalidItemException e) {
                e.printStackTrace();
            }
        }
        return curItem;
    }
    private void doTransactionUpdate(Transaction curItem) {
        updateTicker(curItem);
        updateAccount(curItem);
        curItem.setType(TransactionType.STOCK);
    }
    private void updateAccount(Transaction curItem) {
        Account account = accountDao.findAccountByNumber(curItem.getAccountNumber());
        curItem.setAccountId(account.getId());
    }
    private void updateTicker(Transaction curItem) {
        Ticker ticker = tickerDao.findTickerBySymbol(curItem.getTicker());
        if(ticker == null) {
            Ticker newTicker = new Ticker();
            newTicker.setTicker(curItem.getTicker());
            tickerDao.saveTicker(newTicker);
            ticker = tickerDao.findTickerBySymbol(curItem.getTicker());
        }
        curItem.setTickerId(ticker.getId());
    }
    private void doCustomerUpdate(Customer curCustomer) {
        Customer storedCustomer = customerDao.findCustomerByTaxId(curCustomer.getTaxId());
        Account account =
                accountDao.findAccountByNumber(curCustomer.getAccount().getAccountNumber());
        curCustomer.setId(storedCustomer.getId());
        curCustomer.setAccount(account);
    }
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    public void setTickerDao(TickerDao tickerDao) {
        this.tickerDao = tickerDao;
    }
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
