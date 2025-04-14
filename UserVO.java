package vending;

public class UserVO {
	private int balance = 50000; // 자판기 내부 잔액
    private int Salestot;
    private int addcash;
    private int paycard;
    

    public int getSalestot() {
        return Salestot;
    }

    public void setSalestot(int salestot) {
        Salestot = salestot;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private int insert_money; // 투입한돈

    public int getInsert_money() {
        return insert_money;
    }

    public void setInsert_money(int insert_money) {
        this.insert_money = insert_money;
    }

	public int getAddcash() {
		return addcash;
	}

	public void setAddcash(int addcash) {
		this.addcash = addcash;
	}

	public int getPaycard() {
		return paycard;
	}

	public void setPaycard(int paycard) {
		this.paycard = paycard;
	}
}
