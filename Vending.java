package vending;

import java.util.List;

public interface Vending {
    public List<VendingVO> findByAll();

    public void addStock(VendingVO vo);

    public List<VendingVO> viewItem();

    public void addItem(VendingVO vo);

    public VendingVO findByName(String name);

    public boolean removeItem(String name);

    public boolean updatePrice(String name, int newPrice);

    public boolean reduceStock(String name);
    
    public void addMoney(VendingVO vo,UserVO vo1);
}