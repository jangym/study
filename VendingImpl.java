package vending;

import java.util.ArrayList;
import java.util.List;

public class VendingImpl implements Vending {
    private List<VendingVO> list = new ArrayList<VendingVO>();

    @Override
    public void addStock(VendingVO vo) {  // 같은 이름이면 재고 추가
        for (VendingVO item : list) {
            if (item.getName().equals(vo.getName())) {
                item.setStock(item.getStock() + vo.getStock());
                return;
            }
        }
    }

    @Override
    public List<VendingVO> viewItem() {
        return list;
    }

    @Override
    public void addItem(VendingVO vo) {
        list.add(vo);
    }

    @Override
    public VendingVO findByName(String name) {
        for (VendingVO vo : list) {
            if (vo.getName().equals(name)) {
                return vo;

            }
        }
        return null;
    }

    @Override
    public boolean removeItem(String name) {
        for (VendingVO vo : list) {
            if (vo.getName().equals(name)) {
                list.remove(vo);
                return true;
            }
        }
        return false;

    }


    @Override
    public boolean updatePrice(String name, int newPrice) {  // 가격 수정
        for (VendingVO vo : list) {
            if (vo.getName().equals(name)) {
                vo.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<VendingVO> findByAll() {
        return list;
    }

    @Override
    public boolean reduceStock(String name) {
        for (VendingVO vo : list) {
            if (vo.getName().equals(name) && vo.getStock() > 0) {
                vo.setStock(vo.getStock() - 1);
                return true;
            }
        }
        return false;
    }

	@Override
	public void addMoney(VendingVO vo, UserVO vo1) {
		vo1.setBalance(vo1.getBalance()+vo.getPrice());
		vo1.setSalestot(vo1.getSalestot() + vo.getPrice());
	}

}